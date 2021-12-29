package co.purevanilla.mcplugins.openvoterewards.api;

import co.purevanilla.mcplugins.openvoterewards.api.settings.Settings;
import co.purevanilla.mcplugins.openvoterewards.api.site.Site;
import co.purevanilla.mcplugins.openvoterewards.api.source.DbObject;
import co.purevanilla.mcplugins.openvoterewards.api.source.UnknownObjectException;
import co.purevanilla.mcplugins.openvoterewards.api.user.User;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpenVoteRewardsAPI extends DbObject {

    Settings settings;

    OpenVoteRewardsAPI(Settings settings){
        this.settings=settings;
    }

    /**
     * Returns a list of messages to send when doing /vote
     */
    public List<BaseComponent[]> getVoteMessage(){
        List<BaseComponent[]> messages = new ArrayList<>();
        messages.add(settings.getHeaderText());
        for (Site site:settings.getSiteList()) {
            messages.add(ComponentSerializer.parse(site.getMessage()));
        }
        messages.add(settings.getFooterText());
        return messages;
    }

    public void registerVote(String username, String serviceName, boolean fake) throws SQLException, UnknownObjectException {
        User user = new User(null,username);
        Statement statement = getSource().getConnection().createStatement();
        statement.execute("INSERT INTO ovr_votes(`voter`,`serviceName`,`fake`,`processed`) VALUES(\""+user.getUuid().toString()+"\",\""+serviceName+"\",\""+ String.valueOf(fake ? 1 : 0) +"\",\"{}\")");
        // TODO, try process
    }

}
