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
import io.github.dre2n.commons.command.BRCommand;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import java.io.File;
import org.bukkit.command.CommandSender;

/**
 * @author Daniel Saukel
 */
public class ReloadCommand extends BRCommand {

    BroadcastXS plugin = BroadcastXS.getInstance();

    public ReloadCommand() {
        setMinArgs(0);
        setMaxArgs(0);
        setCommand("reload");
        setPermission("bxs.reload");
        setPlayerCommand(true);
        setConsoleCommand(true);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        plugin.getBCConfig().saveExcludedPlayers();
        plugin.loadBCConfig();
        plugin.loadMessageConfig(new File(plugin.getDataFolder(), "lang.yml"));
        plugin.loadBCCommands();
        plugin.getBroadcastTask().cancel();
        plugin.startAsyncBroadcastTask();

        MessageUtil.sendPluginTag(sender, plugin);
        MessageUtil.sendMessage(sender, BCMessage.CMD_RELOAD.getMessage());
    }

}
