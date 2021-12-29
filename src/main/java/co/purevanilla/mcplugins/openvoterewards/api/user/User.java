package co.purevanilla.mcplugins.openvoterewards.api.user;

import co.purevanilla.mcplugins.openvoterewards.api.site.Site;
import co.purevanilla.mcplugins.openvoterewards.api.source.DbObject;
import co.purevanilla.mcplugins.openvoterewards.api.source.UnknownObjectException;
import co.purevanilla.mcplugins.openvoterewards.api.vote.Vote;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class User extends DbObject {

    @Nullable
    private UUID uuid;
    @Nullable
    private String username;

    public User(@Nullable UUID uuid, @Nullable String username){
        this.uuid=uuid;
        this.username=username;
    }

    public String getUsername() throws SQLException, UnknownObjectException {
        if(this.username==null){
            if(this.uuid==null) throw new UnknownObjectException("missing component");
            Statement statement = getSource().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username FROM ovr_users WHERE `uuid`=\""+this.uuid.toString()+"\"");
            if(resultSet.next()){
                this.username=resultSet.getString("username");
            } else {
                throw new UnknownObjectException("unknown user");
            }
        }
        return this.username;
    }

    public UUID getUuid() throws UnknownObjectException, SQLException {
        if(this.uuid==null){
            if(this.username==null) throw new UnknownObjectException("missing component");
            Statement statement = getSource().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT uuid FROM ovr_users WHERE `username`=\""+this.username+"\"");
            if(resultSet.next()){
                this.uuid=UUID.fromString(resultSet.getString("uuid"));
            } else {
                throw new UnknownObjectException("unknown user");
            }
        }
        return this.uuid;
    }

    public @Nullable Vote getLastVote(Site site) throws SQLException, UnknownObjectException {
        Statement statement = getSource().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id,`timestamp`,service,fake,processed FROM ovr_votes WHERE `service`=\""+site.getServiceName()+"\" AND `voter`=\""+this.getUuid().toString()+"\" ORDER BY `timestamp` DESC LIMIT 1");
        if(resultSet.next()){
            HashMap<String, Long> processed = new Gson().fromJson(
                    resultSet.getString("processed"), new TypeToken<HashMap<String, Long>>() {}.getType()
            );
            return new Vote(resultSet.getLong("id"),resultSet.getBoolean("fake"),resultSet.getLong("timestamp"),site.getServiceName(), processed);
        }
        return null;
    }

    public boolean canVote(Site site) throws SQLException, UnknownObjectException {
        Vote lastVote = this.getLastVote(site);
        return lastVote == null || site.passesCheck(lastVote.getEpoch());
    }

    public List<Site> getRemaining(List<Site> sites) throws SQLException, UnknownObjectException {
        List<Site> remainingSites = new ArrayList<>();
        for (Site site:sites) {
            if(this.canVote(site)) remainingSites.add(site);
        }
        return remainingSites;
    }

}
