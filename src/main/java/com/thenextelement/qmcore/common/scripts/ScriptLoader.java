package com.thenextelement.qmcore.common.scripts;

import java.util.ArrayList;
import java.util.List;

import com.thenextelement.qmcore.api.enums.Mods;
import com.thenextelement.qmcore.common.utils.LogHelper;

/**
 * Runs the pack's scripts once every mod has registered its recipes (FMLLoadCompleteEvent). Ported from
 * NewHorizonsCoreMod.
 * <p>
 * Order: every script's {@code removeRecipes()}, then the buffered removals are applied, then every script's
 * {@code loadRecipes()}. A script whose dependencies are missing is skipped; one that throws is logged and the rest
 * still run.
 */
public final class ScriptLoader {

    private ScriptLoader() {}

    private static List<IScriptLoader> getScripts() {
        List<IScriptLoader> scripts = new ArrayList<>();
        scripts.add(new ScriptOreDictionary()); // first, so later scripts see the new ore names
        scripts.add(new ScriptMinecraft());
        scripts.add(new ScriptMiscFixes.StructureLib());
        scripts.add(new ScriptMiscFixes.Hammerz());
        scripts.add(new ScriptMiscFixes.HarvestCraft());
        scripts.add(new ScriptMiscFixes.EtFuturum());
        scripts.add(new ScriptMiscFixes.Cotton());
        scripts.add(new ScriptMiscFixes.BloodMagic());
        scripts.add(new ScriptMiscFixes.Botania());
        scripts.add(new ScriptProjectRed());
        scripts.add(new ScriptForestry());
        scripts.add(new ScriptBinnie());

        // A script that references another mod's classes (not just its items by name) must only be constructed
        // when that mod is loaded, or class loading crashes the game. Put those behind a check:
        if (Mods.Thaumcraft.isModLoaded()) {
            scripts.add(new ScriptThaumcraft());
            scripts.add(new ScriptThaumcraft.ThaumicDarkness());
            scripts.add(new ScriptAspectsEtFuturum());
            scripts.add(new ScriptAspectsNecromancy());
            scripts.add(new ScriptAspectsReliquary());
        }
        return scripts;
    }

    public static void run() {
        final long start = System.nanoTime();
        List<IScriptLoader> loadable = new ArrayList<>();
        for (IScriptLoader script : getScripts()) {
            if (script.isScriptLoadable()) loadable.add(script);
            else LogHelper.info("Missing dependencies for the " + script.getScriptName() + " script; skipping it.");
        }

        List<String> errored = new ArrayList<>();
        for (IScriptLoader script : loadable) {
            try {
                script.removeRecipes();
            } catch (Exception e) {
                errored.add(script.getScriptName() + " (removals)");
                LogHelper.error("Error in the " + script.getScriptName() + " script's removals:");
                e.printStackTrace();
            }
        }
        RecipeRemover.flush();

        for (IScriptLoader script : loadable) {
            try {
                final long t = System.nanoTime();
                script.loadRecipes();
                LogHelper.info(
                    "Loaded the " + script.getScriptName()
                        + " script in "
                        + (System.nanoTime() - t) / 1_000_000
                        + " ms.");
            } catch (Exception e) {
                errored.add(script.getScriptName());
                LogHelper.error("Error in the " + script.getScriptName() + " script:");
                e.printStackTrace();
            }
        }

        LogHelper.info(
            "Script loader ran " + loadable.size() + " scripts in " + (System.nanoTime() - start) / 1_000_000 + " ms.");
        if (!errored.isEmpty()) LogHelper.error("These scripts threw errors (see above): " + errored);
    }
}
