package co.purevanilla.mcplugins.openvoterewards.api.reward;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.*;

public class ChanceReward implements Reward {

    HashMap<Float, RewardGroup> weightedRewards;

    ChanceReward(ConfigurationSection configurationSection) throws NonProcessableException {
        weightedRewards = new HashMap<>();
        List<String> keys = configurationSection.getKeys(false).stream().toList();
        for (String key:keys) {
            float odds = -1;
            if(configurationSection.contains(key+".percent")){
                odds = (float) (configurationSection.getDouble(key+".percent") / (float) 100);
            }
            weightedRewards.put(odds,new RewardGroup(Objects.requireNonNull(configurationSection.getConfigurationSection(key))));
        }
    }


    public @Nullable RewardGroup getRandomReward(){
        if(!weightedRewards.isEmpty()){

            Random random = new Random();
            float randomRes = random.nextFloat();

            // 10% 10% 20% results in
            // 0.1 >= reward 1
            // 0.1+0.1=0.2 >= reward 2
            // 0.1+0.1+0.2=0.4 >= reward 3
            // we roll the dice...
            // we get 0.3
            // is 0.1 >= 0.3? no
            // is 0.2 >= 0.3? no
            // is 0.4 >= 0.3? yes!

            float cumulativeKey = 0;

            for(Map.Entry<Float, RewardGroup> entry : weightedRewards.entrySet()) {
                Float key = entry.getKey();
                RewardGroup value = entry.getValue();
                cumulativeKey+=key;
                if(cumulativeKey>=randomRes) {
                    return value;
                }
            }

            // no reward was found, but we can return the default one
            // if there is one
            if(weightedRewards.containsKey((float) -1)){
                return weightedRewards.get((float) -1);
            }

        }

        // couldn't get a random reward
        return null;
    }

    @Override
    public void process(Player player) {

    }

    @Override
    public void process(ProxiedPlayer player) {

    }
}
