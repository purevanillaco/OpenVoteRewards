package co.purevanilla.mcplugins.openvoterewards.api.reward;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandReward implements Reward {

    List<String> commands;

    CommandReward(ConfigurationSection configurationSection){
        commands = configurationSection.getStringList("");
    }

    @Override
    public void process(Player player) {

    }

    @Override
    public void process(ProxiedPlayer player) {

    }
}
