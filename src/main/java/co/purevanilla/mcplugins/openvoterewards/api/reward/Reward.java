package co.purevanilla.mcplugins.openvoterewards.api.reward;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public interface Reward {

    @Nullable String permission = null;

    void process(Player player) throws NonProcessableException;
    void process(ProxiedPlayer player) throws NonProcessableException;
    @Nullable String getPermission();

}
