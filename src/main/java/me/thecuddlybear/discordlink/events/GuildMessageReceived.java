package me.thecuddlybear.discordlink.events;

import me.thecuddlybear.discordlink.DiscordLink;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;


public class GuildMessageReceived extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(event.getMessage().getChannel().getId().equals(DiscordLink.getInstance().getConfig().getString("channelID")))
        if(!event.getAuthor().isBot()){
            String message = event.getMessage().getContentRaw();
            String userTag = event.getAuthor().getAsTag();

            DiscordLink.getPlugin(DiscordLink.class).getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[Discord] " + ChatColor.WHITE + userTag + " » " + message);
        }
    }
}
