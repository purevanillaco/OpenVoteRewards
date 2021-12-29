package co.purevanilla.mcplugins.openvoterewards.api.reward;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

public class ItemReward implements Reward {
    @Override
    public void process(Player player) throws NonProcessableException {

    }

    @Override
    public void process(ProxiedPlayer player) throws NonProcessableException {
        throw new NonProcessableException("item rewards can't be processed on the proxy");
    }

    @Override
    public String getPermission() {
        return null;
    }
}
