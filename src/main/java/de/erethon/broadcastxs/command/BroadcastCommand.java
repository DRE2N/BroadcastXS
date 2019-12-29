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
package de.erethon.broadcastxs.command;

import de.erethon.broadcastxs.BroadcastXS;
import de.erethon.broadcastxs.config.BCConfig;
import de.erethon.broadcastxs.config.BCMessage;
import de.erethon.broadcastxs.util.ParsingUtil;
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.command.DRECommand;
import de.erethon.commons.misc.NumberUtil;
import java.util.List;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class BroadcastCommand extends DRECommand {

    private BCConfig config;

    public BroadcastCommand(BroadcastXS plugin) {
        config = plugin.getBCConfig();
        setMinArgs(-1);
        setMaxArgs(-1);
        setCommand("bc");
        setPermission("bxs.broadcast");
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        if (args.length == 1) {
            MessageUtil.sendMessage(sender, BCMessage.HELP_BROADCAST.getMessage());

        } else if (NumberUtil.parseInt(args[1], -1) != -1) {
            int index = NumberUtil.parseInt(args[1]);
            List<String> messages = config.getMessages();
            if (messages.size() > index) {
                String message = messages.get(index);
                ParsingUtil.parseAndBroadcast(message);
            } else {
                MessageUtil.sendMessage(sender, BCMessage.ERROR_NO_SUCH_MESSAGE.getMessage(args[1]));
            }

        } else {
            String message = new String();
            for (String arg : args) {
                if (args[0] != arg) {
                    message += arg + " ";
                }
            }
            ParsingUtil.parseAndBroadcast(message);
        }
    }

}
