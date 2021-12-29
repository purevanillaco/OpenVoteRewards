package co.purevanilla.mcplugins.openvoterewards.api.reward;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RewardGroup {

    List<Reward> rewards;
    @Nullable String permission = null;
    @Nullable public String getPermission(){
        return this.permission;
    }

    RewardGroup(ConfigurationSection configurationSection) throws NonProcessableException {
        this.rewards=new ArrayList<>();
        List<String> keys = configurationSection.getKeys(false).stream().toList();
        for (String key:keys) {
            if(!key.equalsIgnoreCase("permission") && !key.equalsIgnoreCase("percent")) this.rewards.add(Reward.fromConfig(configurationSection.getConfigurationSection(key), key));
        }
        if(keys.contains("permission")){
            this.permission=configurationSection.getString("permission");
        }
    }

}
