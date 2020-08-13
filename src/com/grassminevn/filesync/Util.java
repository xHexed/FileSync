package com.grassminevn.filesync;

import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Util {
    public static void sync(final ConfigurationSection section) {
        if (section.contains("from")) {
            try {
                final Path from = new File(section.getString("from")).toPath();
                for (final String fileName : section.getStringList("to")) {
                    Files.copy(from, new File(fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
            catch (final FileNotFoundException e) {
                FileSync.getInstance().getLogger().severe("Cannot find file: " + e.toString());
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }
        if (section.contains("run")) {
            final ConfigurationSection root = section.getRoot();
            for (final String s : section.getStringList("run")) {
                sync(root.getConfigurationSection(s));
            }
        }
        if (section.contains("delete")) {
            for (final String s : section.getStringList("delete")) {
                try {
                    Files.deleteIfExists(new File(s).toPath());
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}