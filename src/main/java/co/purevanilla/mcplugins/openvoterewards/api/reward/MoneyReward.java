package co.purevanilla.mcplugins.openvoterewards.api.reward;

import co.purevanilla.mcplugins.openvoterewards.api.reward.quantity.Quantity;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class MoneyReward implements Reward{

    Quantity quantity;

    MoneyReward(ConfigurationSection configurationSection) throws NonProcessableException {
        this.quantity=new Quantity(configurationSection);
    }

    @Override
    public void process(Player player) {

    }

    @Override
    public void process(ProxiedPlayer player) throws NonProcessableException {
        throw new NonProcessableException("item rewards can't be processed on the proxy");
    }
}
