package com.thenextelement.qmcore.common.tech;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.thenextelement.qmcore.coremod.QMLoadingPlugin;

/**
 * The RF tech that QMCoreMod switches off in Forestry and Binnie's Mods, shared by the late mixins
 * ({@code mixin.mixins.late.forestry}, {@code mixin.mixins.late.binnie}) and {@code ScriptForestry} /
 * {@code ScriptBinnie}.
 * <p>
 * Forestry's Factory, Energy and Mail modules are off in the pack's {@code forestry/modules.cfg}. Inside the modules
 * that stay on, these are the blocks that take RF: the Analyzer (Core), the Alveary Fan and Heater (Apiculture) and the
 * Farm Gearbox (Farming, which leaves the Multifarm without power). Binnie's RF machines are the packages that build a
 * {@code ComponentPowerReceptor}: every Genetics machine, the Extra Bees Stimulator and Transmission and the Extra
 * Trees Lumbermill. Binnie's own {@code powered} flag is not used, because it is wrong for the Transmission.
 * <p>
 * Nothing is unregistered: Binnie looks Forestry items up by name and throws if one is missing, and removing IDs would
 * break saves. The blocks stay, but they take no power, do no work and are left out of the creative tabs and NEI.
 */
public final class DisabledTech {

    private DisabledTech() {}

    /** {@code forestry.core.blocks.BlockCoreType.ANALYZER}. */
    public static final int FORESTRY_CORE_ANALYZER = 0;
    /** {@code forestry.apiculture.blocks.BlockAlveary.Type.FAN}. */
    public static final int FORESTRY_ALVEARY_FAN = 3;
    /** {@code forestry.apiculture.blocks.BlockAlveary.Type.HEATER}. */
    public static final int FORESTRY_ALVEARY_HEATER = 4;
    /** {@code forestry.farming.blocks.BlockFarm}: meta 2 is the TileFarmGearbox. */
    public static final int FORESTRY_FARM_GEARBOX = 2;

    /** {@code binnie.extrabees.apiary.machine.AlvearyMachine}: Stimulator. */
    public static final int EXTRABEES_ALVEARY_STIMULATOR = 4;
    /** {@code binnie.extrabees.apiary.machine.AlvearyMachine}: Transmission. */
    public static final int EXTRABEES_ALVEARY_TRANSMISSION = 6;
    /** {@code binnie.extratrees.machines.ExtraTreeMachine}: Lumbermill. */
    public static final int EXTRATREES_LUMBERMILL = 0;

    /** Binnie machine packages that take RF (each one creates a ComponentPowerReceptor). */
    public static final Set<String> BINNIE_RF_PACKAGES = Collections.unmodifiableSet(
        new HashSet<>(
            Arrays.asList(
                "binnie.genetics.machine.acclimatiser.AcclimatiserPackage",
                "binnie.genetics.machine.analyser.AnalyserPackage",
                "binnie.genetics.machine.genepool.GenepoolPackage",
                "binnie.genetics.machine.incubator.IncubatorPackage",
                "binnie.genetics.machine.inoculator.InoculatorPackage",
                "binnie.genetics.machine.isolator.IsolatorPackage",
                "binnie.genetics.machine.polymeriser.PolymeriserPackage",
                "binnie.genetics.machine.sequencer.SequencerPackage",
                "binnie.genetics.machine.splicer.SplicerPackage",
                "binnie.extrabees.apiary.machine.stimulator.StimulatorAlvearyPackage",
                "binnie.extrabees.apiary.machine.transmission.TransmissionAlvearyPackage",
                "binnie.extratrees.machines.lumbermill.LumbermillPackage")));

    /**
     * Binnie machine packages left out of the creative tabs. The RF machines plus the Genetics Laboratory, which takes
     * no power but only serves Genetics' removed items (and is crafted from the removed Laboratory Casing).
     */
    public static final Set<String> BINNIE_HIDDEN_PACKAGES;

    static {
        Set<String> hidden = new HashSet<>(BINNIE_RF_PACKAGES);
        hidden.add("binnie.genetics.machine.lab.LabMachinePackage");
        BINNIE_HIDDEN_PACKAGES = Collections.unmodifiableSet(hidden);
    }

    /** Set by {@code disableRFTech} in QuaesitaMagumCore.properties. */
    public static boolean isEnabled() {
        return QMLoadingPlugin.disableRFTech;
    }
}
