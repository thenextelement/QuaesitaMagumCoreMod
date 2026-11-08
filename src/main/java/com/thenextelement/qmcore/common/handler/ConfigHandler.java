package com.thenextelement.qmcore.common.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.thenextelement.qmcore.common.utils.LogHelper;

public class ConfigHandler {

    public static Configuration config;

    // GENERAL

    public static boolean disableLog;

    public static void init(File confFile) {
        if (config == null) {
            config = new Configuration(confFile);
            config.load();
            syncConfig();
        }
    }

    public static void syncConfig() {

        try {

            craftingStationShiftClickToGrid = config.get(
                "general",
                "craftingStationShiftClickToGrid",
                true,
                "Shift-click items from the player or attached inventory into the crafting station grid first. When false, move items between the player and attached inventory, or into the grid if no inventory is attached.")
                .getBoolean(true);

            disableLog = config.get(
                Configuration.CATEGORY_GENERAL,
                "Disable Log",
                false,
                "If you are having issued with console spam that you cant fix setting this to true will disable all log output from Draconic Evolution (Not recommended)")
                .getBoolean(false);

            if (disableLog) {
                disableLog = false;
                LogHelper.warn("###########################################");
                LogHelper.warn("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                LogHelper.warn("WARNING Quaesita Magum Log is Disabled!");
                LogHelper.warn("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                LogHelper.warn("###########################################");
                disableLog = true;
            }
        } catch (Exception e) {
            LogHelper.error("Unable to load Config");
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) config.save();
        }

    }

    public static boolean craftingStationShiftClickToGrid;

}
