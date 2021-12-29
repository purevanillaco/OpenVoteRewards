package co.purevanilla.mcplugins.openvoterewards.api.reward;

import co.purevanilla.mcplugins.openvoterewards.api.reward.quantity.QuantifiedItem;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class ItemReward implements Reward {

    List<QuantifiedItem> items;

    ItemReward(ConfigurationSection configurationSection){

    }

    @Override
    public void process(Player player) throws NonProcessableException {

    }

    @Override
    public void process(ProxiedPlayer player) throws NonProcessableException {
        throw new NonProcessableException("item rewards can't be processed on the proxy");
    }
}
