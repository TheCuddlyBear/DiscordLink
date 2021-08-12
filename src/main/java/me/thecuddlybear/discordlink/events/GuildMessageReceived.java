package me.thecuddlybear.discordlink.events;

import me.thecuddlybear.discordlink.DiscordLink;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang.text.StrSubstitutor;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class GuildMessageReceived extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(event.getMessage().getChannel().getId().equals(DiscordLink.getInstance().getConfig().getString("channelID")))
        if(!event.getAuthor().isBot()){
            String message = event.getMessage().getContentRaw();
            String userTag = event.getAuthor().getAsTag();

            Map<String, String> values = new HashMap<String, String>();
            values.put("username", userTag);
            values.put("message", message);
            StrSubstitutor sub = new StrSubstitutor(values, "{", "}");
            String broadcastMessage = sub.replace(DiscordLink.getInstance().getConfig().getString("chatLayout"));

            DiscordLink.getPlugin(DiscordLink.class).getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', broadcastMessage));
        }
    }
}
