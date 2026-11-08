package com.thenextelement.qmcore.coremod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.thenextelement.qmcore.common.lib.References;

import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * Loading plugin that registers early mixins (ones that must apply before Minecraft itself starts, such as the window
 * title) and reads the pack's display settings. Modelled on NewHorizonsCoreMod's DreamCoreMod.
 * <p>
 * Settings live in {@code config/QuaesitaMagumCore.properties}, which is read before the game window opens, so the
 * pack version can be bumped without rebuilding the mod.
 */
@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.Name("QMLoadingPlugin")
public class QMLoadingPlugin implements IFMLLoadingPlugin, IEarlyMixinLoader {

    public static final Logger LOG = LogManager.getLogger("QMLoadingPlugin");

    private static final String CONFIG_FILE_NAME = "QuaesitaMagumCore.properties";
    private static final String KEY_PACK_NAME = "displayedModpackName";
    private static final String KEY_PACK_VERSION = "displayedModpackVersion";
    private static final String KEY_DISABLED_MODS = "forceDisabledMods";
    private static final String KEY_UNIFICATION = "enableOreUnification";

    /** ProjectRed's tech modules. Core, Exploration and Illumination are kept. */
    private static final String DEFAULT_DISABLED_MODS = String.join(
        ",",
        "ProjRed|Transmission",
        "ProjRed|Integration",
        "ProjRed|Fabrication",
        "ProjRed|Expansion",
        "ProjRed|Transportation",
        "ProjRed|Compatibility");

    public static String displayedModpackName = References.MODPACK_NAME;
    public static String displayedModpackVersion = References.MODPACK_VERSION;
    /** Mod IDs removed from FML's mod list before construction, so every other mod sees them as not installed. */
    public static Set<String> forceDisabledMods = Collections.emptySet();
    public static boolean enableOreUnification = true;

    @Override
    public void injectData(Map<String, Object> data) {
        File mcLocation = (File) data.get("mcLocation");
        if (mcLocation == null) return;

        File configDir = new File(mcLocation, "config");
        // noinspection ResultOfMethodCallIgnored
        configDir.mkdirs();
        File configFile = new File(configDir, CONFIG_FILE_NAME);

        Properties props = new Properties();
        props.setProperty(KEY_PACK_NAME, References.MODPACK_NAME);
        props.setProperty(KEY_PACK_VERSION, References.MODPACK_VERSION);
        props.setProperty(KEY_DISABLED_MODS, DEFAULT_DISABLED_MODS);
        props.setProperty(KEY_UNIFICATION, "true");

        try (Reader r = new FileReader(configFile)) {
            props.load(r);
        } catch (FileNotFoundException ignored) {
            // first launch: the defaults are written below
        } catch (IOException e) {
            LOG.warn("Can't read {}. Proceeding with defaults!", CONFIG_FILE_NAME, e);
        }

        displayedModpackName = props.getProperty(KEY_PACK_NAME, References.MODPACK_NAME)
            .trim();
        displayedModpackVersion = props.getProperty(KEY_PACK_VERSION, References.MODPACK_VERSION)
            .trim();

        Set<String> disabled = new HashSet<>();
        for (String id : props.getProperty(KEY_DISABLED_MODS, "")
            .split(",")) {
            if (!id.trim()
                .isEmpty()) disabled.add(id.trim());
        }
        forceDisabledMods = Collections.unmodifiableSet(disabled);
        enableOreUnification = !"false".equalsIgnoreCase(
            props.getProperty(KEY_UNIFICATION, "true")
                .trim());

        // write back so new keys appear in existing files
        try (Writer w = new FileWriter(configFile)) {
            props.store(
                w,
                "Quaesita Magum core settings, read before the game window opens.\n"
                    + "displayedModpackName and displayedModpackVersion form the window title.\n"
                    + "forceDisabledMods: comma-separated mod IDs that FML will treat as not installed.\n"
                    + "enableOreUnification: one item per metal/gem/dust (see common/unification).");
        } catch (IOException e) {
            LOG.warn("Can't write {}. Changes may not have been saved!", CONFIG_FILE_NAME, e);
        }
    }

    /** The window title, e.g. "Quaesita Magum 2.7.x". */
    public static String getWindowTitle() {
        return displayedModpackVersion.isEmpty() ? displayedModpackName
            : displayedModpackName + " " + displayedModpackVersion;
    }

    @Override
    public String getMixinConfig() {
        return "mixins.qmcore.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        final List<String> mixins = new ArrayList<>(Arrays.asList("MixinJarDiscoverer_ForceDisableMods"));
        if (enableOreUnification) mixins.add("MixinOreDictionary_Unification");
        if (FMLLaunchHandler.side()
            .isClient()) {
            mixins.add("MixinMinecraft_PackIcon");
        }
        return mixins;
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
