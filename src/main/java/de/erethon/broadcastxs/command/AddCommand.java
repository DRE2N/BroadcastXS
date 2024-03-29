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

import de.erethon.bedrock.chat.MessageUtil;
import de.erethon.bedrock.command.ECommand;
import de.erethon.bedrock.misc.NumberUtil;
import de.erethon.broadcastxs.BroadcastXS;
import de.erethon.broadcastxs.config.BCConfig;
import de.erethon.broadcastxs.config.BCMessage;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class AddCommand extends ECommand {

    private BCConfig config;

    public AddCommand(BroadcastXS plugin) {
        config = plugin.getBCConfig();
        setMinArgs(-1);
        setMaxArgs(-1);
        setCommand("add");
        setPermission("bxs.edit");
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        if (args.length == 1) {
            MessageUtil.sendMessage(sender, BCMessage.HELP_ADD.getMessage());
            return;
        }
        int index = NumberUtil.parseInt(args[1], -1);
        if (config.getMessages().size() < index) {
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
        config.addMessage(index, message);
        MessageUtil.sendMessage(sender, BCMessage.CMD_EDIT.getMessage(String.valueOf(index), message));
    }

}
