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
package io.github.dre2n.broadcastxs.command;

import io.github.dre2n.broadcastxs.BroadcastXS;
import io.github.dre2n.broadcastxs.config.BCMessages;
import io.github.dre2n.commons.command.BRCommand;
import static io.github.dre2n.commons.util.messageutil.FatLetters.*;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class MainCommand extends BRCommand {

    BroadcastXS plugin = BroadcastXS.getInstance();

    public MainCommand() {
        setMinArgs(0);
        setMaxArgs(0);
        setCommand("main");
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        MessageUtil.sendCenteredMessage(sender, "&4" + B[0] + "&f" + X[0] + S[0]);
        MessageUtil.sendCenteredMessage(sender, "&4" + B[1] + "&f" + X[1] + S[1]);
        MessageUtil.sendCenteredMessage(sender, "&4" + B[2] + "&f" + X[2] + S[2]);
        MessageUtil.sendCenteredMessage(sender, "&4" + B[3] + "&f" + X[3] + S[3]);
        MessageUtil.sendCenteredMessage(sender, "&4" + B[4] + "&f" + X[4] + S[4]);
        MessageUtil.sendCenteredMessage(sender, "&b&l####### " + BCMessages.CMD_MAIN.getMessage() + "&7 v" + plugin.getDescription().getVersion() + " &b&l#######");
        MessageUtil.sendCenteredMessage(sender, BCMessages.HELP_BROADCAST.getMessage());
        MessageUtil.sendCenteredMessage(sender, BCMessages.HELP_RELOAD.getMessage());
        MessageUtil.sendCenteredMessage(sender, "&7\u00a92016 Daniel Saukel; licensed under GPLv3.");
    }

}
