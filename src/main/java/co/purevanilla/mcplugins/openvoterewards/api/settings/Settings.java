package co.purevanilla.mcplugins.openvoterewards.api.settings;

import co.purevanilla.mcplugins.openvoterewards.api.site.Site;
import co.purevanilla.mcplugins.openvoterewards.api.source.UnknownObjectException;
import co.purevanilla.mcplugins.openvoterewards.api.user.User;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;

import java.sql.SQLException;
import java.util.List;

public class Settings {

    // site list
    private final List<Site> siteList;

    // messages
    private final BaseComponent[] headerText;
    private final BaseComponent[] footerText;
    private final String voteReminder;

    Settings(List<Site> siteList, String headerText, String footerText, String voteReminder){
        this.siteList=siteList;
        this.headerText= ComponentSerializer.parse(headerText);
        this.footerText= ComponentSerializer.parse(footerText);
        this.voteReminder= footerText;
    }

    public BaseComponent[] getVoteReminder(User user) throws SQLException, UnknownObjectException {
        return ComponentSerializer.parse(this.voteReminder.replaceAll("%remaining%", String.valueOf(user.getRemaining(this.siteList).size())).replaceAll("%total%", String.valueOf(this.siteList.size())));
    }

    public BaseComponent[] getVoteReminder() {
        return ComponentSerializer.parse(this.voteReminder);
    }

    public BaseComponent[] getHeaderText() {
        return headerText;
    }

    public BaseComponent[] getFooterText() {
        return footerText;
    }

    public List<Site> getSiteList() {
        return siteList;
    }
}
