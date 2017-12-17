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
package io.github.dre2n.broadcastxs.command;

import io.github.dre2n.broadcastxs.BroadcastXS;
import io.github.dre2n.broadcastxs.config.BCConfig;
import io.github.dre2n.broadcastxs.config.BCMessage;
import io.github.dre2n.commons.chat.MessageUtil;
import io.github.dre2n.commons.command.DRECommand;
import io.github.dre2n.commons.misc.NumberUtil;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class AddCommand extends DRECommand {

    BCConfig config = BroadcastXS.getInstance().getBCConfig();

    public AddCommand() {
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
