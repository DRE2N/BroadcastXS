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
import de.erethon.broadcastxs.config.BCMessage;
import de.erethon.commons.chat.MessageUtil;
import de.erethon.commons.command.DRECommand;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class ReloadCommand extends DRECommand {

    private BroadcastXS plugin;

    public ReloadCommand(BroadcastXS plugin) {
        this.plugin = plugin;
        setMinArgs(0);
        setMaxArgs(0);
        setCommand("reload");
        setPermission("bxs.reload");
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        plugin.reloadMessageHandler();
        plugin.getBCConfig().saveExcludedPlayers();
        plugin.loadBCConfig();
        plugin.loadBCCommands();
        plugin.getBroadcastTask().cancel();
        plugin.startAsyncBroadcastTask();

        MessageUtil.sendPluginTag(sender, plugin);
        MessageUtil.sendMessage(sender, BCMessage.CMD_RELOAD.getMessage());
    }

}
