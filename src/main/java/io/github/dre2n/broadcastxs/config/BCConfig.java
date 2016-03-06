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
package io.github.dre2n.broadcastxs.config;

import io.github.dre2n.commons.config.BRConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Saukel
 */
public class BCConfig extends BRConfig {

    public static final int CONFIG_VERSION = 1;

    private double interval = 120;
    private List<String> messages = new ArrayList<>();

    public BCConfig(File file) {
        super(file, CONFIG_VERSION);

        if (initialize) {
            initialize();
        }
        load();
    }

    /**
     * @return
     * how often a message will be broadcasted
     */
    public long getInterval() {
        return (long) (interval * 20);
    }

    /**
     * @return
     * the messages to send
     */
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public void initialize() {
        if (!config.contains("interval")) {
            config.set("interval", interval);
        }

        if (!config.contains("messages")) {
            config.set("messages", messages);
        }

        save();
    }

    @Override
    public void load() {
        if (config.contains("interval")) {
            interval = config.getDouble("interval");
        }

        if (config.contains("messages")) {
            messages = config.getStringList("messages");
        }
    }

}
