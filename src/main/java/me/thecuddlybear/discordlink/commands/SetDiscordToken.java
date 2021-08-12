package me.thecuddlybear.discordlink.commands;

import me.thecuddlybear.discordlink.DiscordLink;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetDiscordToken implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("discordlink.changetoken")){
                if(args.length == 0){
                    player.sendMessage("You need to provide a valid discord bot token!");
                }else if(args[0].length() == 59){
                    DiscordLink.getInstance().getConfig().set("discordToken", args[0]);
                    DiscordLink.getInstance().saveConfig();
                    DiscordLink.getInstance().reloadConfig();
                    player.sendMessage("Changed the discord bot token!");
                }
                }else{
                    player.sendMessage("You need to provide a valid discord bot token!");
                }
        }
        return false;
    }
}
