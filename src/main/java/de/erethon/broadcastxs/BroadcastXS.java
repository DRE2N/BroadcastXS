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
package de.erethon.broadcastxs;

import de.erethon.broadcastxs.command.AddCommand;
import de.erethon.broadcastxs.command.BroadcastCommand;
import de.erethon.broadcastxs.command.EditCommand;
import de.erethon.broadcastxs.command.MainCommand;
import de.erethon.broadcastxs.command.ReloadCommand;
import de.erethon.broadcastxs.command.RemoveCommand;
import de.erethon.broadcastxs.command.ToggleCommand;
import de.erethon.broadcastxs.config.BCConfig;
import de.erethon.broadcastxs.config.BCMessage;
import de.erethon.broadcastxs.task.AsyncBroadcastTask;
import de.erethon.commons.command.DRECommandCache;
import de.erethon.commons.config.MessageConfig;
import de.erethon.commons.javaplugin.DREPlugin;
import de.erethon.commons.javaplugin.DREPluginSettings;
import java.io.File;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Daniel Saukel
 */
public class BroadcastXS extends DREPlugin {

    private BCConfig config;
    private BukkitTask broadcastTask;

    public BroadcastXS() {
        settings = DREPluginSettings.builder()
                .metrics(true)
                .spigotMCResourceId(19765)
                .build();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        loadBCConfig();
        loadMessageConfig(new File(getDataFolder(), "lang.yml"));
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
        return (BroadcastXS) DREPlugin.getInstance();
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
        config = new BCConfig(new File(getDataFolder(), "config.yml"));
    }

    /**
     * load / reload a new instance of MessageConfig
     *
     * @param file the file to load from
     */
    public void loadMessageConfig(File file) {
        messageConfig = new MessageConfig(BCMessage.class, file);
    }

    /**
     * Load the commands
     */
    public void loadBCCommands() {
        setCommandCache(new DRECommandCache(
                "broadcastxs",
                this,
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
