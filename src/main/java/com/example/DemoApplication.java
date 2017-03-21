package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication  {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);}}
   /* @Override
    public void run(String... strings) throws Exception {

    }

    public int insertUser (String Firstname, String Lastname, String Mail, String Username, String Password ){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                        PreparedStatement ps = connection.prepareStatement("INSERT INTO [dbo].[User](Firstname, Lastname, Mail, Username, Password)VALUES (?,?,?,?,?)", new String[]{"Id"});
                        ps.setString(1, Firstname);
                        ps.setString(2, Lastname);
                        ps.setString(3, Mail);
                        ps.setString(4, Username);
                        ps.setString(5, Password);
                        return ps;
                    }
                    },
                            keyHolder);

                    return keyHolder.getKey().intValue();




    }
    }
*/

