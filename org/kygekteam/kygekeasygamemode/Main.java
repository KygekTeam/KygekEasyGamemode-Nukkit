/*
 * Quickly change between gamemodes!
 * Copyright (C) 2020 KygekTeam
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package org.kygekteam.kygekeasygamemode;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase {

    private static final String PREFIX = TextFormat.GREEN + "[KygekEasyGamemode] ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName().toLowerCase()) {
            case "gmds":
                this.changeGamemode(sender, "gmds", args);
                break;
            case "gmdc":
                this.changeGamemode(sender, "gmdc", args);
                break;
            case "gmda":
                this.changeGamemode(sender, "gmda", args);
                break;
            case "gmdsp":
                this.changeGamemode(sender, "gmdsp", args);
        }
        return true;
    }

    private void changeGamemode(CommandSender sender, String cmd, String[] args) {
        if (!sender.hasPermission("kygekeasygmd." + cmd)) {
            sender.sendMessage(PREFIX + TextFormat.RED + "You do not have permission to use this command");
            return;
        }

        if (args.length < 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(PREFIX + TextFormat.WHITE + "Usage: /" + cmd + " <player>");
            } else {
                String gamemode = this.setGamemode((Player) sender, cmd);
                sender.sendMessage(PREFIX + TextFormat.YELLOW + "Successfully changed your gamemode to " + gamemode);
            }
            return;
        }

        Player player = this.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(PREFIX + TextFormat.RED + "Player was not found");
            return;
        }

        String gamemode = this.setGamemode(player, cmd);
        sender.sendMessage(PREFIX + TextFormat.YELLOW + "Successfully changed " + player.getName() + "'s gamemode to " + gamemode);
        player.sendMessage(PREFIX + TextFormat.YELLOW + "Your gamemode was changed to " + gamemode + " by " + sender.getName());
    }

    private String setGamemode(Player player, String cmd) {
        switch (cmd) {
            case "gmds":
                player.setGamemode(0);
                return "Survival";
            case "gmdc":
                player.setGamemode(1);
                return "Creative";
            case "gmda":
                player.setGamemode(2);
                return "Adventure";
            case "gmdsp":
                player.setGamemode(3);
                return "Spectator";
            default:
                return "";
        }
    }

}
