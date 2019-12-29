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
package de.erethon.broadcastxs.config;

import de.erethon.commons.config.Message;

/**
 * @author Daniel Saukel
 */
public enum BCMessage implements Message {

    CMD_EDIT("cmd.edit"),
    CMD_MAIN("cmd.main"),
    CMD_RELOAD("cmd.reload"),
    CMD_REMOVE("cmd.remove"),
    CMD_TOGGLE_DISABLED("cmd.toggle.disabled"),
    CMD_TOGGLE_ENABLED("cmd.toggle.enabled"),
    HELP_BROADCAST("help.broadcast"),
    HELP_ADD("help.add"),
    HELP_EDIT("help.edit"),
    HELP_RELOAD("help.reload"),
    HELP_REMOVE("help.remove"),
    HELP_TOGGLE("help.toggle"),
    ERROR_NO_SUCH_MESSAGE("error.noSuchMessage");

    private String path;

    BCMessage(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

}
