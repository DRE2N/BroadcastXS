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
package io.github.dre2n.broadcastxs.task;

import io.github.dre2n.broadcastxs.BroadcastXS;
import io.github.dre2n.broadcastxs.config.BCConfig;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import java.util.List;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Daniel Saukel
 */
public class AsyncBroadcastTask extends BukkitRunnable {

    BCConfig config;

    private List<String> messages;
    private int index;

    public AsyncBroadcastTask(List<String> messages) {
        this.messages = messages;
        this.index = -1;
        config = BroadcastXS.getInstance().getBCConfig();
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
        if (message.startsWith("actionBar:")) {
            message = message.replaceFirst("actionBar:", "");
            MessageUtil.broadcastActionBarMessage(message);
        } else if (message.startsWith("centered:")) {
            message = message.replaceFirst("centered:", "");
            String[] lines = message.split("<br>");
            for (String line : lines) {
                MessageUtil.broadcastCenteredMessage(line);
            }
        } else if (message.startsWith("title:")) {
            String[] args = message.split("subtitle:");
            String title = args[0].replaceFirst("title:", "");
            String subtitle = "";
            if (args.length == 2) {
                subtitle = args[1];
            }
            MessageUtil.broadcastTitleMessage(title, subtitle, config.getFadeIn(), config.getShow(), config.getFadeOut());
        } else {
            String[] lines = message.split("<br>");
            for (String line : lines) {
                MessageUtil.broadcastMessage(line);
            }
        }
    }

}
