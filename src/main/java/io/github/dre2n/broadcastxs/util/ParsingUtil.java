/*
 * Copyright (C) 2016 Daniel Saukel
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
package io.github.dre2n.broadcastxs.util;

import io.github.dre2n.broadcastxs.BroadcastXS;
import io.github.dre2n.broadcastxs.config.BCConfig;
import io.github.dre2n.commons.util.messageutil.MessageUtil;

/**
 * @author Daniel Saukel
 */
public class ParsingUtil {

    static BCConfig config = BroadcastXS.getInstance().getBCConfig();

    /**
     * Parses and broadcasts a message.
     *
     * @param message
     */
    public static void parseAndBroadcast(String message) {
        if (message.startsWith("actionBar:")) {
            message = message.replaceFirst("actionBar:", new String());
            MessageUtil.broadcastActionBarMessage(message);
        } else if (message.startsWith("title:")) {
            String[] args = message.split("subtitle:");
            String title = args[0].replaceFirst("title:", new String());
            String subtitle = "";
            if (args.length == 2) {
                subtitle = args[1];
            }
            MessageUtil.broadcastTitleMessage(title, subtitle, config.getFadeIn(), config.getShow(), config.getFadeOut());
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

    static void parseAndBroadcastChat(String message, boolean prefix) {
        if (message.startsWith("centered:")) {
            message = message.replaceFirst("centered:", new String());
            String[] lines = message.split("<br>");
            for (String line : lines) {
                if (lines[0] == line && prefix) {
                    MessageUtil.broadcastCenteredMessage(config.getPrefix() + line);
                } else {
                    MessageUtil.broadcastCenteredMessage(line);
                }
            }

        } else {
            String[] lines = message.split("<br>");
            for (String line : lines) {
                if (lines[0] == line && prefix) {
                    MessageUtil.broadcastMessage(config.getPrefix() + line);
                } else {
                    MessageUtil.broadcastMessage(line);
                }
            }
        }
    }

}
