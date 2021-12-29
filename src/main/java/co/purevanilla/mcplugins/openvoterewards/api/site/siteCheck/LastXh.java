package co.purevanilla.mcplugins.openvoterewards.api.site.siteCheck;

public class LastXh extends SiteCheck{

    private short hh;
    private short mm;
    private short ss;

    public LastXh(short hh, short mm, short ss){
        super(CheckType.LAST_XH);
        this.hh=hh;
        this.mm=mm;
        this.ss=ss;
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

    @Override
    public boolean passes(long lastVoteEpoch) {
        int delta = hh*3600+mm*60+ss;
        long current = System.currentTimeMillis();
        return lastVoteEpoch+delta<=current;
    }
}
