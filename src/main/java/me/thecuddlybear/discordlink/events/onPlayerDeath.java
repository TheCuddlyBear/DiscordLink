package me.thecuddlybear.discordlink.events;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import me.thecuddlybear.discordlink.DiscordLink;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if(event.getEntity() instanceof Player){
            String deathMessage = event.getDeathMessage();
            Player player = (Player) event.getEntity();
            String avatar = "https://minotar.net/avatar/" + player.getName();
            try(WebhookClient client = WebhookClient.withUrl(DiscordLink.getInstance().getConfig().getString("webhookURL"))){
                WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
                messageBuilder.setUsername("Minecraft Server");
                messageBuilder.setAvatarUrl(avatar);
                messageBuilder.setContent(deathMessage);
                client.send(messageBuilder.build());
            }
        }
    }

}
