package com.grassminevn.filesync;

import org.bukkit.plugin.java.JavaPlugin;

public class FileSync extends JavaPlugin {
    public static FileSync getInstance() {
/* 11 */     return plugin;
/*    */   }
    private static FileSync plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        reloadSettings();
        getCommand("sync").setExecutor(new CommandHandler());
    }

    @Override
    public void onDisable() {
    }

    public static void reloadSettings() {
        plugin.reloadConfig();
    }
}