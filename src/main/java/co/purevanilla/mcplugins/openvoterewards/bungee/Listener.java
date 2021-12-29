package co.purevanilla.mcplugins.openvoterewards.bungee;

import co.purevanilla.mcplugins.openvoterewards.api.source.UnknownObjectException;
import com.imaginarycode.minecraft.redisbungee.events.PlayerChangedServerNetworkEvent;
import com.imaginarycode.minecraft.redisbungee.events.PlayerJoinedNetworkEvent;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.event.EventHandler;

import java.sql.SQLException;

import static co.purevanilla.mcplugins.openvoterewards.bungee.OpenVoteRewards.usesRedis;

public class Listener implements net.md_5.bungee.api.plugin.Listener {

    @EventHandler
    void onVote(VotifierEvent event) throws SQLException, UnknownObjectException {
        Vote vote = event.getVote();
        OpenVoteRewards.getAPI().registerVote(vote.getUsername(),vote.getServiceName(),false);
    }

    @EventHandler
    void onJoinRedis(PlayerJoinedNetworkEvent event){
        if(usesRedis){

        }
    }

    @EventHandler
    void onChangeRedis(PlayerChangedServerNetworkEvent event){
        if(usesRedis){

        }
    }

    @EventHandler
    void onJoinVanilla(PostLoginEvent event){
        if(!usesRedis){

        }
    }

    @EventHandler
    void onChangeVanilla(ServerConnectedEvent event){
        if(!usesRedis){

        }
    }



}
