package org.example.dao;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Slf4j
public class DataSourceFactory {

    @Value("${db.name}")
    private String dbName;
    @Value("${db.user.name}")
    private String userName;
    @Value("${db.user.pass}")
    private String userPassword;
    @Value("${db.host}")
    private String host;
    @Value("${db.port}")
    private int port;


    public DataSource getOracleDataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setDatabaseName(dbName);
        ds.setUser(userName);
        ds.setPassword(userPassword);
        ds.setPortNumbers(new int[]{port});
        ds.setServerNames(new String[] {host});
        return ds;
    }
}
