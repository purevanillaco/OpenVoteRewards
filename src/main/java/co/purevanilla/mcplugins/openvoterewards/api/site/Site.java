package co.purevanilla.mcplugins.openvoterewards.api.site;

import co.purevanilla.mcplugins.openvoterewards.api.site.siteCheck.SiteCheck;
import org.bukkit.configuration.ConfigurationSection;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Site {


    private @NonNull SiteCheck check;
    private @NonNull String name;
    private @NonNull String url;
    private @NonNull String serviceName;
    private @NonNull String message;
    private @NonNull String messageVoted;
    private @NonNull String messageReceived;

    Site(@NotNull String name,@NotNull String url,@NotNull String serviceName,@NotNull String message,@NotNull String messageVoted,@NotNull String messageReceived,@NotNull SiteCheck check){
        this.name=name;
        this.url=url;
        this.serviceName=serviceName;
        this.message=message;
        this.messageVoted=messageVoted;
        this.messageReceived=messageReceived;
        this.check=check;
    }

    public static Site fromConfig(ConfigurationSection configurationSection){
        return new Site(configurationSection.getString("name"),configurationSection.getString("url"),configurationSection.getString("service"), configurationSection.getString("messages.didntVote"),configurationSection.getString("messages.voted"),configurationSection.getString("messages.received"), SiteCheck.fromConfig(configurationSection.getConfigurationSection("check")));
    }

    public @NotNull String getMessage(){
        return this.message.replaceAll("%siteName%",this.getName()).replaceAll("%serviceName%",this.getServiceName()).replaceAll("%url%",this.getUrl());
    }

    public @NotNull String getMessageVoted() {
        return messageVoted;
    }

    public @NotNull String getMessageReceived() {
        return messageReceived;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getServiceName() {
        return serviceName;
    }

    public @NotNull String getUrl() {
        return url;
    }

    public boolean passesCheck(long lastVoteEpoch){
        return this.check.passes(lastVoteEpoch);
    }

}
