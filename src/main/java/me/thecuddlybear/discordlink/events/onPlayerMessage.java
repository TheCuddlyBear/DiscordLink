package me.thecuddlybear.discordlink.events;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import me.thecuddlybear.discordlink.DiscordLink;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onPlayerMessage implements Listener {

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent evt){
        Player player = evt.getPlayer();
        String username = player.getName();
        String avatar = "https://minotar.net/avatar/" + username;
        String message = evt.getMessage();

        try(WebhookClient client = WebhookClient.withUrl(DiscordLink.getInstance().getConfig().getString("webhookURL"))){
            WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
            messageBuilder.setUsername(username);
            messageBuilder.setAvatarUrl(avatar);
            messageBuilder.setContent(message);
            client.send(messageBuilder.build());
        }
    }


}
