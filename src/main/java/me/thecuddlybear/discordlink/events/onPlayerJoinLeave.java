package me.thecuddlybear.discordlink.events;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import me.thecuddlybear.discordlink.DiscordLink;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class onPlayerJoinLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String username = player.getDisplayName();
        String avatar = "https://minotar.net/avatar/" + player.getName();
        try(WebhookClient client = WebhookClient.withUrl(Objects.requireNonNull(DiscordLink.getInstance().getConfig().getString("webhookURL")))){
            WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
            messageBuilder.setUsername("Minecraft Server");
            messageBuilder.setAvatarUrl(avatar);
            messageBuilder.setContent(username + " has left the minecraft server.");
            client.send(messageBuilder.build());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String username = player.getDisplayName();
        String avatar = "https://minotar.net/avatar/" + player.getName();
        try(WebhookClient client = WebhookClient.withUrl(Objects.requireNonNull(DiscordLink.getInstance().getConfig().getString("webhookURL")))){
            WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
            messageBuilder.setUsername("Minecraft Server");
            messageBuilder.setAvatarUrl(avatar);
            messageBuilder.setContent(username + " has joined the minecraft server.");
            client.send(messageBuilder.build());
        }
    }

}
