package me.thecuddlybear.discordlink.commands;

import me.thecuddlybear.discordlink.DiscordLink;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetChannelId implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("discordlink.setchannelid")){
                if(args.length == 0){
                    player.sendMessage("You need to provide a discord channel ID!");
                }else {
                    DiscordLink.getInstance().getConfig().set("channelID", args[0]);
                    DiscordLink.getInstance().saveConfig();
                    DiscordLink.getInstance().reloadConfig();
                    player.sendMessage("Changed the discord channel ID!");
                }
            }else{
                player.sendMessage("You do not have permission to perform this command!");
            }
        }

        return false;
    }
}
