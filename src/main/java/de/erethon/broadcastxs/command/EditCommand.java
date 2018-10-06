/*
 * Copyright (C) 2016-2018 Daniel Saukel
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
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.command.DRECommand;
import de.erethon.commons.misc.NumberUtil;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class EditCommand extends DRECommand {

    private BCConfig config;

    public EditCommand(BroadcastXS plugin) {
        config = plugin.getBCConfig();
        setMinArgs(-1);
        setMaxArgs(-1);
        setCommand("edit");
        setPermission("bxs.edit");
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        if (args.length == 1) {
            MessageUtil.sendMessage(sender, BCMessage.HELP_EDIT.getMessage());
            return;
        }
        int index = NumberUtil.parseInt(args[1], -1);
        if (index == -1 || config.getMessages().size() <= index) {
            MessageUtil.sendMessage(sender, BCMessage.ERROR_NO_SUCH_MESSAGE.getMessage(args[1]));
            return;
        }
        String message = new String();
        for (String arg : args) {
            if (args[0] != arg && args[1] != arg) {
                if (!message.isEmpty()) {
                    message += " ";
                }
                message += arg;
            }
        }
        config.setMessage(index, message);
        MessageUtil.sendMessage(sender, BCMessage.CMD_EDIT.getMessage(String.valueOf(index), message));
    }

}