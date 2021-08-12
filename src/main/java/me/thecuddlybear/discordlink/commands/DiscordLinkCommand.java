package me.thecuddlybear.discordlink.commands;

import me.thecuddlybear.discordlink.DiscordLink;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordLinkCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(args.length == 0){
                player.sendMessage(ChatColor.DARK_PURPLE + "DiscordLink" + ChatColor.GRAY + " version " + ChatColor.DARK_PURPLE + "1.0-beta" + ChatColor.GRAY + ". Subcommands:");
                player.sendMessage(ChatColor.DARK_PURPLE + "/dlink" + " webhook <token>" + ChatColor.GRAY + " - lists all enchantments");
                player.sendMessage(ChatColor.DARK_PURPLE + "/dlink" + " token <token>" + ChatColor.GRAY + " - displays this page");
                player.sendMessage(ChatColor.DARK_PURPLE + "/dlink" + " channelid <id>" + ChatColor.GRAY + " - checks enchantments on main hand");
                player.sendMessage(ChatColor.GRAY + "More commands will be added soon\u2122!");
            }else{
                String subCmd = args[0];
                switch(subCmd){
                    case "webhook":
                        if(player.hasPermission("discordlink.webhook")){
                            if(args.length == 1){
                                player.sendMessage(ChatColor.DARK_PURPLE + "BearEnchantment » " + ChatColor.GRAY + "You need provide a valid Discord webhook URL!");
                                break;
                            }else if(!args[0].startsWith("https://discordapp.com/api/webhooks/")){
                                player.sendMessage(ChatColor.DARK_PURPLE + "BearEnchantment » " + ChatColor.GRAY + "You need provide a valid Discord webhook URL!");
                                break;
                            }else{
                                DiscordLink.getInstance().getConfig().set("webhookURL", args[0]);
                                DiscordLink.getInstance().saveConfig();
                                DiscordLink.getInstance().reloadConfig();
                                player.sendMessage(ChatColor.DARK_PURPLE + "BearEnchantment » " + ChatColor.GRAY + "Succesfully changed the webhook URL!");
                                break;
                            }
                        }else{
                            player.sendMessage(ChatColor.DARK_PURPLE + "DiscordLink » " + ChatColor.GRAY + "Invalid permission.");
                            break;
                        }
                    case "token":
                        if(player.hasPermission("discordlink.token")){

                        }else{
                            player.sendMessage(ChatColor.DARK_PURPLE + "DiscordLink » " + ChatColor.GRAY + "Invalid permission.");
                            break;
                        }
                        break;
                    case "channelid":
                        if(player.hasPermission("discordlink.channelid")){
                            player.sendMessage(ChatColor.DARK_PURPLE + "DiscordLink » " + ChatColor.GRAY + "Invalid permission.");
                            break;
                        }
                        break;
                }
            }

        }

        return false;
    }
}
