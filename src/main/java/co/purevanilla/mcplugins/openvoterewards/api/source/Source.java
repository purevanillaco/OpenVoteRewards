package co.purevanilla.mcplugins.openvoterewards.api.source;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Source {

    MysqlDataSource dataSourceA;
    MariaDbPoolDataSource dataSourceB;

    Source(String host, int port, String database, String user, String password, String type, int poolSize) throws SQLException {
        if(type.equalsIgnoreCase("mariadb")){
            this.dataSourceB=new MariaDbPoolDataSource();

            this.dataSourceB.setServerName(host);
            this.dataSourceB.setPortNumber(port);
            this.dataSourceB.setDatabaseName(database);
            this.dataSourceB.setUser(user);
            this.dataSourceB.setPassword(password);

            if(poolSize > 0) this.dataSourceB.setMaxPoolSize(poolSize);
        } else {
            this.dataSourceA=new MysqlConnectionPoolDataSource();

            this.dataSourceA.setServerName(host);
            this.dataSourceA.setPortNumber(port);
            this.dataSourceA.setDatabaseName(database);
            this.dataSourceA.setUser(user);
            this.dataSourceA.setPassword(password);
        }
    }

    public Connection getConnection() throws SQLException {
        if(this.dataSourceA!=null){
            return this.dataSourceA.getConnection();
        } else {
            return this.dataSourceB.getConnection();
        }
    }



}
