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
import io.github.dre2n.broadcastxs.config.BCMessage;
import io.github.dre2n.broadcastxs.util.ParsingUtil;
import io.github.dre2n.commons.command.BRCommand;
import io.github.dre2n.commons.util.NumberUtil;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import java.util.List;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class BroadcastCommand extends BRCommand {

    public BroadcastCommand() {
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
            List<String> messages = BroadcastXS.getInstance().getBCConfig().getMessages();
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
