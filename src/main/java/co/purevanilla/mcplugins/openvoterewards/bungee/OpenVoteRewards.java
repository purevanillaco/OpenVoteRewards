package co.purevanilla.mcplugins.openvoterewards.bungee;

import co.purevanilla.mcplugins.openvoterewards.api.OpenVoteRewardsAPI;
import net.md_5.bungee.api.plugin.Plugin;

public class OpenVoteRewards extends Plugin {

    static OpenVoteRewardsAPI API;
    static boolean usesRedis;

    public static OpenVoteRewardsAPI getAPI(){
        return OpenVoteRewards.API;
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}
