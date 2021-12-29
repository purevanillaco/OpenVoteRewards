package co.purevanilla.mcplugins.openvoterewards.api.site.siteCheck;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.TimeZone;

public class AfterXh extends SiteCheck {

    private short hh;
    private short mm;
    private short ss;
    private TimeZone timeZone;

    public AfterXh(short hh, short mm, short ss, TimeZone timeZone){
        super(CheckType.ON_TIMEZONE);
        this.hh=hh;
        this.mm=mm;
        this.ss=ss;
        this.timeZone=timeZone;
    }

    public short getHh() {
        return hh;
    }

    public short getMm() {
        return mm;
    }

    public short getSs() {
        return ss;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    @Override
    public boolean passes(long lastVoteEpoch) {

        ZoneId zoneId = ZoneId.of(getTimeZone().getID());

        OffsetDateTime nowOnTimezone = OffsetDateTime.now(zoneId);
        int hh = nowOnTimezone.get(ChronoField.HOUR_OF_DAY);
        int mm = nowOnTimezone.get(ChronoField.MINUTE_OF_HOUR);
        int ss = nowOnTimezone.get(ChronoField.SECOND_OF_MINUTE);
        int dd = nowOnTimezone.get(ChronoField.DAY_OF_YEAR);

        Instant lastVoteInstant = Instant.ofEpochSecond(lastVoteEpoch);
        OffsetDateTime previousInTimezone = OffsetDateTime.ofInstant(lastVoteInstant,zoneId);
        int prevDd = previousInTimezone.get(ChronoField.DAY_OF_YEAR);

        // first comp field: check that we are past the expected hour of timezone
        // seco. comp field: check that we are past the previous expected hour of timezone
        // - note prevDd > dd && dd == 1 is a special case for change of year, day 1 will be < than prev day
        //   on the rest of the days, the previous day should always be smaller than the next time section
        return (hh>=getHh()&&mm>=getMm()&&ss>=getSs()) && (prevDd > dd && dd == 1 || prevDd < dd);
    }
}
