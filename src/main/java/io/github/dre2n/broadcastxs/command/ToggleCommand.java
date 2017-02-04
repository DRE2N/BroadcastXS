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
import io.github.dre2n.broadcastxs.config.BCMessages;
import io.github.dre2n.commons.command.BRCommand;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import java.util.UUID;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class ToggleCommand extends BRCommand {

    BCConfig config = BroadcastXS.getInstance().getBCConfig();

    public ToggleCommand() {
        setMinArgs(0);
        setMaxArgs(0);
        setCommand("toggle");
        setPermission("bxs.toggle");
        setPlayerCommand(true);
        setConsoleCommand(false);
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        UUID uuid = ((Player) sender).getUniqueId();
        boolean disabled = config.getExcludedPlayers().contains(uuid);
        if (disabled) {
            config.getExcludedPlayers().remove(uuid);
        } else {
            config.getExcludedPlayers().add(uuid);
        }
        MessageUtil.sendMessage(sender, disabled ? BCMessages.CMD_TOGGLE_ENABLED.getMessage() : BCMessages.CMD_TOGGLE_DISABLED.getMessage());
    }

}
