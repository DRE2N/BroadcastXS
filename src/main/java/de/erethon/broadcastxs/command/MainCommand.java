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
import de.erethon.broadcastxs.BroadcastXS;
import de.erethon.broadcastxs.config.BCMessage;
import org.bukkit.command.CommandSender;

import static de.erethon.bedrock.chat.FatLetter.*;

/**
 * @author Daniel Saukel
 */
public class MainCommand extends ECommand {

    private BroadcastXS plugin;

    public MainCommand(BroadcastXS plugin) {
        this.plugin = plugin;
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
        MessageUtil.sendCenteredMessage(sender, "&b&l####### " + BCMessage.CMD_MAIN.getMessage() + "&7 v" + plugin.getDescription().getVersion() + " &b&l#######");
        MessageUtil.sendCenteredMessage(sender, BCMessage.HELP_ADD.getMessage());
        MessageUtil.sendCenteredMessage(sender, BCMessage.HELP_EDIT.getMessage());
        MessageUtil.sendCenteredMessage(sender, BCMessage.HELP_REMOVE.getMessage());
        MessageUtil.sendCenteredMessage(sender, BCMessage.HELP_BROADCAST.getMessage());
        MessageUtil.sendCenteredMessage(sender, BCMessage.HELP_TOGGLE.getMessage());
        MessageUtil.sendCenteredMessage(sender, BCMessage.HELP_RELOAD.getMessage());
        MessageUtil.sendCenteredMessage(sender, "&7\u00a92016-2019 Daniel Saukel; licensed under GPLv3.");
    }

}
