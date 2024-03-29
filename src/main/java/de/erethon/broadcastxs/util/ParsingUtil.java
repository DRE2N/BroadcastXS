/*
 * Copyright (C) 2016-2019 Daniel Saukel
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
package de.erethon.broadcastxs.util;

import de.erethon.bedrock.chat.MessageUtil;
import de.erethon.broadcastxs.BroadcastXS;
import de.erethon.broadcastxs.config.BCConfig;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class ParsingUtil {

    /**
     * Parses and broadcasts a message.
     *
     * @param message
     */
    public static void parseAndBroadcast(String message) {
        BCConfig config = BroadcastXS.getInstance().getBCConfig();
        if (message.startsWith("actionBar:")) {
            message = message.replaceFirst("actionBar:", "");
            broadcastActionBarMessage(message);
        } else if (message.startsWith("title:")) {
            String[] args = message.split("subtitle:");
            String title = args[0].replaceFirst("title:", "");
            String subtitle = "";
            if (args.length == 2) {
                subtitle = args[1];
            }
            broadcastTitleMessage(title, subtitle, config.getFadeIn(), config.getShow(), config.getFadeOut());
        } else {
            if (!config.getHeader().isEmpty()) {
                parseAndBroadcastChat(config.getHeader(), false);
            }
            parseAndBroadcastChat(message, true);
            if (!config.getFooter().isEmpty()) {
                parseAndBroadcastChat(config.getFooter(), false);
            }
        }
    }

    private static void parseAndBroadcastChat(String message, boolean prefix) {
        BCConfig config = BroadcastXS.getInstance().getBCConfig();
        if (message.startsWith("centered:")) {
            message = message.replaceFirst("centered:", "");
            String[] lines = message.split("<br>");
            for (String line : lines) {
                if (lines[0] == line && prefix) {
                    broadcastCenteredMessage(config.getPrefix() + line);
                } else {
                    broadcastCenteredMessage(line);
                }
            }

        } else {
            String[] lines = message.split("<br>");
            for (String line : lines) {
                if (lines[0] == line && prefix) {
                    broadcastMessage(config.getPrefix() + line);
                } else {
                    broadcastMessage(line);
                }
            }
        }
    }

    public static void broadcastMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String send = BroadcastXS.getInstance().isPlaceholderAPIEnabled() ? PlaceholderAPI.setPlaceholders(player, message) : message;
            if (!BroadcastXS.getInstance().getBCConfig().getExcludedPlayers().contains(player.getUniqueId())) {
                MessageUtil.sendMessage(player, send);
            }
        }
    }

    public static void broadcastCenteredMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String send = BroadcastXS.getInstance().isPlaceholderAPIEnabled() ? PlaceholderAPI.setPlaceholders(player, message) : message;
            if (!BroadcastXS.getInstance().getBCConfig().getExcludedPlayers().contains(player.getUniqueId())) {
                MessageUtil.sendCenteredMessage(player, send);
            }
        }
    }

    public static void broadcastActionBarMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String send = BroadcastXS.getInstance().isPlaceholderAPIEnabled() ? PlaceholderAPI.setPlaceholders(player, message) : message;
            if (!BroadcastXS.getInstance().getBCConfig().getExcludedPlayers().contains(player.getUniqueId())) {
                MessageUtil.sendActionBarMessage(player, send);
            }
        }
    }

    public static void broadcastTitleMessage(String title, String subtitle, int fadeIn, int show, int fadeOut) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String sendTitle = BroadcastXS.getInstance().isPlaceholderAPIEnabled() ? PlaceholderAPI.setPlaceholders(player, title) : title;
            String sendSubtitle = BroadcastXS.getInstance().isPlaceholderAPIEnabled() ? PlaceholderAPI.setPlaceholders(player, subtitle) : subtitle;
            if (!BroadcastXS.getInstance().getBCConfig().getExcludedPlayers().contains(player.getUniqueId())) {
                MessageUtil.sendTitleMessage(player, sendTitle, sendSubtitle, fadeIn, show, fadeOut);
            }
        }
    }

}
