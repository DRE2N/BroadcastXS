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
package io.github.dre2n.broadcastxs.config;

import io.github.dre2n.broadcastxs.BroadcastXS;
import io.github.dre2n.commons.config.Message;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Daniel Saukel
 */
public enum BCMessage implements Message {

    CMD_MAIN("cmd.main", "&7Welcome to &4Broadcast&fXS"),
    CMD_RELOAD("cmd.reload", "&6Reload complete."),
    CMD_TOGGLE_DISABLED("cmd.toggle.disabled", "&6You disabled broadcast messages."),
    CMD_TOGGLE_ENABLED("cmd.toggle.enabled", "&6You enabled broadcast messages."),
    HELP_BROADCAST("help.broadcast", "&eUse &b/bxs bc [index|text]&e to send a message from config or a custom one."),
    HELP_RELOAD("help.reload", "&eUse &b/bxs reload&e to reload the configuration."),
    HELP_TOGGLE("help.toggle", "&eUse &b/bxs toggle&e to enable/disable broadcasts."),
    ERROR_NO_SUCH_MESSAGE("error.noSuch.message", "&4The message &6&v1 &4does not exist.");

    private String identifier;
    private String message;

    BCMessage(String identifier, String message) {
        this.identifier = identifier;
        this.message = message;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessage(String... args) {
        return BroadcastXS.getInstance().getMessageConfig().getMessage(this, args);
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /* Statics */
    /**
     * @param identifer
     * the identifer to set
     */
    public static Message getByIdentifier(String identifier) {
        for (Message message : values()) {
            if (message.getIdentifier().equals(identifier)) {
                return message;
            }
        }

        return null;
    }

    /**
     * @return a FileConfiguration containing all messages
     */
    public static FileConfiguration toConfig() {
        FileConfiguration config = new YamlConfiguration();
        for (Message message : values()) {
            config.set(message.getIdentifier(), message.getMessage());
        }

        return config;
    }

}
