package com.thenextelement.qmcore.common.utils.handlers;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class FileHandler {

    public static File configFolder;
    public static File mcDirectory;

    public static void init(FMLPreInitializationEvent event) {
        configFolder = event.getModConfigurationDirectory();
        mcDirectory = configFolder.getParentFile();
    }
}
