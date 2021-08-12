package me.thecuddlybear.discordlink;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import me.thecuddlybear.discordlink.commands.SetChannelId;
import me.thecuddlybear.discordlink.commands.SetDiscordToken;
import me.thecuddlybear.discordlink.commands.SetWebhookUrl;
import me.thecuddlybear.discordlink.events.GuildMessageReceived;
import me.thecuddlybear.discordlink.events.onPlayerDeath;
import me.thecuddlybear.discordlink.events.onPlayerJoinLeave;
import me.thecuddlybear.discordlink.events.onPlayerMessage;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.theprogramsrc.supercoreapi.global.files.JsonConfig;

import javax.security.auth.login.LoginException;
import java.io.File;

public final class DiscordLink extends JavaPlugin {

    private static DiscordLink instance = null;
    private static WebhookClientBuilder webhookBuilder = null;
    private static WebhookClient webhookClient = null;
    private static JDA jda = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getCommand("webhook").setExecutor(new SetWebhookUrl());
        getCommand("dtoken").setExecutor(new SetDiscordToken());
        getCommand("channelid").setExecutor(new SetChannelId());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new onPlayerJoinLeave(), this);
        if(getConfig().getString("webhookURL").startsWith("https://discordapp.com/api/webhooks/")){
            getServer().getPluginManager().registerEvents(new onPlayerMessage(), this);
        }else {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(getConfig().get("webhookURL").toString().startsWith("https://discordapp.com/api/webhooks/")){
                        getServer().getPluginManager().registerEvents(new onPlayerMessage(), DiscordLink.getPlugin(DiscordLink.class));
                    }
                }
            }.runTaskTimer(this, 20L * 10L, 20L * 10L);
        }
        if(getConfig().getString("discordToken").length() == 59){
            try {
                jda = JDABuilder.createDefault(getConfig().getString("discordToken")).build();
            } catch (LoginException e) {
                e.printStackTrace();
            }
            jda.addEventListener(new GuildMessageReceived());
        }else{
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(getConfig().getString("discordToken").length() == 59 && jda == null){
                        try {
                            jda = JDABuilder.createDefault(getConfig().getString("discordToken")).build();
                        } catch (LoginException e) {
                            e.printStackTrace();
                        }
                        jda.addEventListener(new GuildMessageReceived());
                    }
                }
            }.runTaskTimer(this, 20L * 10L, 20L * 10L);
        }

    }

    public static DiscordLink getInstance(){
        return instance;
    }

    public static WebhookClientBuilder getWebhookBuilder(){
        return webhookBuilder;
    }

    public static WebhookClient getWebhookClient() {
        return webhookClient;
    }

    public static JDA getJDA(){
        return jda;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DiscordLink.getJDA().shutdownNow();
    }
}
