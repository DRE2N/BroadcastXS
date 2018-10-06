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
package de.erethon.broadcastxs.task;

import de.erethon.broadcastxs.util.ParsingUtil;
import java.util.List;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Daniel Saukel
 */
public class AsyncBroadcastTask extends BukkitRunnable {

    private List<String> messages;
    private int index;

    public AsyncBroadcastTask(List<String> messages) {
        this.messages = messages;
        this.index = -1;
    }

    @Override
    public void run() {
        if (messages == null || messages.isEmpty()) {
            cancel();
            return;
        }

        index++;

        if (index >= messages.size()) {
            index = 0;
        }

        String message = messages.get(index);
        ParsingUtil.parseAndBroadcast(message);
    }

}
