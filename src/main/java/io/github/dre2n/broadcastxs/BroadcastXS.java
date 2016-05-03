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
package io.github.dre2n.broadcastxs;

import io.github.dre2n.broadcastxs.command.*;
import io.github.dre2n.broadcastxs.config.BCConfig;
import io.github.dre2n.broadcastxs.config.BCMessages;
import io.github.dre2n.broadcastxs.task.AsyncBroadcastTask;
import io.github.dre2n.commons.command.BRCommands;
import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.config.MessageConfig;
import io.github.dre2n.commons.javaplugin.BRPlugin;
import io.github.dre2n.commons.javaplugin.BRPluginSettings;
import java.io.File;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Daniel Saukel
 */
public class BroadcastXS extends BRPlugin {

    private static BroadcastXS instance;

    private BCConfig config;
    private MessageConfig messageConfig;
    private BukkitTask broadcastTask;

    public BroadcastXS() {
        settings = new BRPluginSettings(false, false, false, false, true, Internals.INDEPENDENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        instance = this;

        loadBCConfig();
        loadMessageConfig(new File(getDataFolder(), "lang.yml"));
        loadBCCommands();

        startAsyncBroadcastTask();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    /**
     * @return
     * the plugin instance
     */
    public static BroadcastXS getInstance() {
        return instance;
    }

    /**
     * @return
     * the config
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
     * @return
     * the loaded instance of MessageConfig
     */
    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    /**
     * load / reload a new instance of MessageConfig
     */
    public void loadMessageConfig(File file) {
        messageConfig = new MessageConfig(BCMessages.class, file);
    }

    /**
     * Load the commands
     */
    public void loadBCCommands() {
        setCommands(new BRCommands(
                "broadcastxs",
                this,
                new MainCommand(),
                new ReloadCommand()
        ));

        getCommands().register(this);
    }

    /**
     * @return
     * the AsyncBroadcastTask
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
