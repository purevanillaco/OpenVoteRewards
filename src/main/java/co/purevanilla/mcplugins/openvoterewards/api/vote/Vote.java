package co.purevanilla.mcplugins.openvoterewards.api.vote;

import co.purevanilla.mcplugins.openvoterewards.api.source.DbObject;
import co.purevanilla.mcplugins.openvoterewards.api.source.IllegalActionException;
import co.purevanilla.mcplugins.openvoterewards.api.source.UnknownObjectException;
import org.json.simple.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class Vote extends DbObject {

    private long id;
    private boolean fake;
    private long epoch;
    private String service;
    private HashMap<String,Long> processed;

    public Vote(long id, boolean fake, long epoch, String service, HashMap<String,Long> processed){
        this.id=id;
        this.fake=fake;
        this.epoch=epoch;
        this.service=service;
        this.processed=processed;
    }

    public boolean isProcessed(String serverName){
        return processed.containsKey(serverName);
    }

    public void process(String serverName) throws IllegalActionException, SQLException, UnknownObjectException {
        if(this.isProcessed(serverName)) throw new IllegalActionException("this vote was already processed");
        long processTime=System.currentTimeMillis();

        JSONObject json = new JSONObject(processed);
        PreparedStatement statement = getSource().getConnection().prepareStatement("UPDATE `ovr_votes` SET `processed`=? WHERE `id`=?");
        statement.setString(1,json.toJSONString());
        statement.setLong(2,this.id);
        int res = statement.executeUpdate();
        if(res>0){
            this.processed.put(serverName,processTime);
        } else {
            throw new UnknownObjectException("unknown vote");
        }
    }

    public long getEpoch() {
        return epoch;
    }
}
