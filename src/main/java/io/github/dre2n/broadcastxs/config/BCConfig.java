/*
 * Copyright (C) 2016-2017 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.broadcastxs.config;

import io.github.dre2n.commons.config.BRConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Daniel Saukel
 */
public class BCConfig extends BRConfig {

    public static final int CONFIG_VERSION = 4;

    private double interval = 120;
    private List<String> messages = new ArrayList<>();
    private String prefix = new String();
    private String header = new String();
    private String footer = new String();
    private double fadeIn = 1;
    private double show = 3;
    private double fadeOut = 1;
    private boolean saveToggle = false;
    private List<UUID> excludedPlayers = new ArrayList<>();

    public BCConfig(File file) {
        super(file, CONFIG_VERSION);

        if (initialize) {
            initialize();
        }
        load();
    }

    /**
     * @return
     * how often a message will be broadcasted
     */
    public long getInterval() {
        return (long) (interval * 20);
    }

    /**
     * @return
     * the messages to send
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * @return
     * a prefix that will be broadcasted before a message
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @return
     * a header that will be broadcasted before a message
     */
    public String getHeader() {
        return header;
    }

    /**
     * @return
     * a footer that will be broadcasted after a message
     */
    public String getFooter() {
        return footer;
    }

    /**
     * @return
     * the fade in time of a title
     */
    public int getFadeIn() {
        return (int) (fadeIn * 20);
    }

    /**
     * @return
     * the show time of a title
     */
    public int getShow() {
        return (int) (show * 20);
    }

    /**
     * @return
     * the fade out time of a title
     */
    public int getFadeOut() {
        return (int) (fadeOut * 20);
    }

    /**
     * @return
     * if toggling broadcasts on / off should be saved
     */
    public boolean getSaveToggle() {
        return saveToggle;
    }

    /**
     * @return
     * the UUIDs of the players that won't receive broadcast messages
     */
    public List<UUID> getExcludedPlayers() {
        return excludedPlayers;
    }

    @Override
    public void initialize() {
        if (!config.contains("interval")) {
            config.set("interval", interval);
        }

        if (!config.contains("messages")) {
            config.set("messages", messages);
        }

        if (!config.contains("prefix")) {
            config.set("prefix", prefix);
        }

        if (!config.contains("header")) {
            config.set("header", header);
        }

        if (!config.contains("footer")) {
            config.set("footer", footer);
        }

        if (!config.contains("fadeIn")) {
            config.set("fadeIn", fadeIn);
        }

        if (!config.contains("show")) {
            config.set("show", show);
        }

        if (!config.contains("fadeOut")) {
            config.set("fadeOut", fadeOut);
        }

        if (!config.contains("saveToggle")) {
            config.set("saveToggle", saveToggle);
        }

        if (!config.contains("excludedPlayers")) {
            config.set("excludedPlayers", excludedPlayers);
        }

        save();
    }

    @Override
    public void load() {
        if (config.contains("interval")) {
            interval = config.getDouble("interval");
        }

        if (config.contains("messages")) {
            messages = config.getStringList("messages");
        }

        if (config.contains("prefix")) {
            prefix = config.getString("prefix");
        }

        if (config.contains("header")) {
            header = config.getString("header");
        }

        if (config.contains("footer")) {
            footer = config.getString("footer");
        }

        if (config.contains("fadeIn")) {
            fadeIn = config.getDouble("fadeIn");
        }

        if (config.contains("show")) {
            show = config.getDouble("show");
        }

        if (config.contains("fadeOut")) {
            fadeOut = config.getDouble("fadeOut");
        }

        if (config.contains("saveToggle")) {
            saveToggle = config.getBoolean("saveToggle");
        }

        if (config.contains("excludedPlayers")) {
            for (String string : config.getStringList("excludedPlayers")) {
                excludedPlayers.add(UUID.fromString(string));
            }
        }
    }

    @Override
    public void save() {
        config = YamlConfiguration.loadConfiguration(file);
        if (saveToggle) {
            ArrayList<String> uuids = new ArrayList<>();
            for (UUID player : excludedPlayers) {
                uuids.add(player.toString());
            }
            config.set("excludedPlayers", uuids);
        }
        super.save();
    }

}
