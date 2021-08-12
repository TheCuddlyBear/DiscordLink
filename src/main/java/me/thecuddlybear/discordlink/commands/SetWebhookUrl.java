package me.thecuddlybear.discordlink.commands;

import me.thecuddlybear.discordlink.DiscordLink;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

public class SetWebhookUrl implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("discordlink.changewebhook")){
                if(args.length == 0){
                    player.sendMessage("Please provide a discord webhook URL!");
                }else if(!args[0].startsWith("https://discordapp.com/api/webhooks/")){
                    player.sendMessage("Invalid webhook url!");
                }
                else{
                    // change config option
                    DiscordLink.getInstance().getConfig().set("webhookURL", args[0]);
                    player.sendMessage("Changed the webhook url to: " + args[0]);
                }
            }
        }else{
            DiscordLink.getInstance().getLogger().severe("This command can only be run by a player!");
        }

        return false;
    }
}
