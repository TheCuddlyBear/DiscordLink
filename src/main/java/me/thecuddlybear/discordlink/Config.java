package me.thecuddlybear.discordlink;

import io.github.thatsmusic99.configurationmaster.CMFile;
import org.bukkit.plugin.Plugin;

public class Config extends CMFile {

    public Config(Plugin plugin, String name) {
        super(DiscordLink.getPlugin(DiscordLink.class), "config.yml");
        load();
    }

    @Override
    public void loadDefaults() {
        addSection("Minecraft chat to Discord");
        addDefault("webhookURL", "");

        addSection("Discord chat to Minecraft");
        addDefault("discordToken", "");
        addDefault("channelID", "");
    }

}
