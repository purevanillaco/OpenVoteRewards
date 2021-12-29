package co.purevanilla.mcplugins.openvoterewards.api.reward;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

public class MessageReward implements Reward{
    @Override
    public void process(Player player) {

    }

    @Override
    public void process(ProxiedPlayer player) {

    }

    @Override
    public String getPermission() {
        return null;
    }
}
