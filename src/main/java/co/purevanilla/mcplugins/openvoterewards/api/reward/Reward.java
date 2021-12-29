package co.purevanilla.mcplugins.openvoterewards.api.reward;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public interface Reward {


    void process(Player player) throws NonProcessableException;
    void process(ProxiedPlayer player) throws NonProcessableException;
    static Reward fromConfig(ConfigurationSection section, String key) throws NonProcessableException {
        if(key.equalsIgnoreCase("chance")){
            return new ChanceReward(section);
        } else if(key.equalsIgnoreCase("items")){
            return new ItemReward(section);
        } else if(key.equalsIgnoreCase("commands")){
            return new CommandReward(section);
        } else if(key.equalsIgnoreCase("messages")){
            return new MessageReward(section);
        } else if(key.equalsIgnoreCase("money")){
            return new MoneyReward(section);
        } else {
            throw new NonProcessableException("unknown reward type '"+key+"'");
        }
    }

}
