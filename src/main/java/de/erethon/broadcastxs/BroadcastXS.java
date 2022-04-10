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
package de.erethon.broadcastxs;

import de.erethon.bedrock.command.ECommandCache;
import de.erethon.bedrock.plugin.EPlugin;
import de.erethon.bedrock.plugin.EPluginSettings;
import de.erethon.broadcastxs.command.AddCommand;
import de.erethon.broadcastxs.command.BroadcastCommand;
import de.erethon.broadcastxs.command.EditCommand;
import de.erethon.broadcastxs.command.MainCommand;
import de.erethon.broadcastxs.command.ReloadCommand;
import de.erethon.broadcastxs.command.RemoveCommand;
import de.erethon.broadcastxs.command.ToggleCommand;
import de.erethon.broadcastxs.config.BCConfig;
import de.erethon.broadcastxs.task.AsyncBroadcastTask;
import java.io.File;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Daniel Saukel
 */
public class BroadcastXS extends EPlugin {

    private BCConfig config;
    private BukkitTask broadcastTask;

    public BroadcastXS() {
        settings = EPluginSettings.builder()
                .metrics(true)
                .spigotMCResourceId(19765)
                .build();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        loadBCConfig();
        loadBCCommands();

        startAsyncBroadcastTask();
    }

    @Override
    public void onDisable() {
        config.save();
    }

    /**
     * @return the plugin instance
     */
    public static BroadcastXS getInstance() {
        return (BroadcastXS) EPlugin.getInstance();
    }

    /**
     * @return the config
     */
    public BCConfig getBCConfig() {
        return config;
    }

    /**
     * Load / reload a new instance of BCConfig
     */
    public void loadBCConfig() {
        config = new BCConfig(this, new File(getDataFolder(), "config.yml"));
    }

    /**
     * Load the commands
     */
    public void loadBCCommands() {
        setCommandCache(new ECommandCache("broadcastxs", this,
                new AddCommand(this),
                new BroadcastCommand(this),
                new EditCommand(this),
                new MainCommand(this),
                new ReloadCommand(this),
                new RemoveCommand(this),
                new ToggleCommand(this)
        ));

        getCommandCache().register(this);
    }

    /**
     * @return the AsyncBroadcastTask
     */
    public BukkitTask getBroadcastTask() {
        return broadcastTask;
    }

    /**
     * Start a new AsyncBroadcastTask
     */
    public void startAsyncBroadcastTask() {
        broadcastTask = new AsyncBroadcastTask(config.getMessages()).runTaskTimerAsynchronously(this, 0, config.getInterval());
    }

}
