package co.purevanilla.mcplugins.openvoterewards.api.site.check;

import org.bukkit.configuration.ConfigurationSection;

import java.util.TimeZone;

public abstract class SiteCheck {

    public enum CheckType {
        LAST_XH,        // user can vote if he last voted >= x hours ago
        ON_TIMEZONE     // user can vote if the time is greater than x on y timezone, or it didn't vote on the previous range
    }

    private CheckType type;

    SiteCheck(CheckType type){
        this.type=type;
    }

    public CheckType getCheckType(){
        return this.type;
    }

    public abstract boolean passes(long lastVoteEpoch);

    public static SiteCheck fromConfig(ConfigurationSection section){
        if(section.getString("type").equalsIgnoreCase("afterCooldown")){
            return new LastXh((short) section.getInt("hh"),(short) section.getInt("mm"),(short) section.getInt("ss"));
        } else {
            // see https://docs.oracle.com/javase/7/docs/api/java/util/TimeZone.html#getTimeZone(java.lang.String)
            return new AfterXh((short) section.getInt("hh"),(short) section.getInt("mm"),(short) section.getInt("ss"), TimeZone.getTimeZone(section.getString("timezone")));
        }
    }

}
