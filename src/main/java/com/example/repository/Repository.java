package com.example.repository;


import com.example.domain.GetUser;
import com.example.domain.UserLogin;
import com.example.domain.UserSignUp;
import org.apache.catalina.User;
import org.springframework.beans.ExtendedBeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observer;

@Component
public class Repository implements IUser {

   @Autowired
    DataSource dataSource;


    public UserLogin getUserLogin(String Username, String Password) throws Exception {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT Username, Password FROM [dbo].[ForumUser] WHERE Username= ? AND Password= ?")) {
            ps.setString(1, Username);
            ps.setString(2, Password);

            try (ResultSet rs = ps.executeQuery()) {

                    if (!rs.next())
                        return null;
                    else
                        return rsUserLogin(rs);
            } catch (SQLException e) {
                throw new Exception(e);
            }
        }
    }
    @Override
    public void addUser(String Firstname, String Lastname, String Email, String Username, String Password) throws Exception {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[ForumUser](FirstName, LastName, Email, Username, Password)VALUES (?,?,?,?,?)", new String []{"Id"})) {
            ps.setString(1, Firstname);
            ps.setString(2, Lastname);
            ps.setString(3, Email);
            ps.setString(4, Username);
            ps.setString(5, Password);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    private UserLogin rsUserLogin(ResultSet rs) throws SQLException {
        return new UserLogin(
                rs.getString("Username"),
                rs.getString("Password")
        );
    }
    public UserSignUp rsUser(ResultSet rs) throws SQLException {
        return new UserSignUp(
                rs.getString("Firstname"),
                rs.getString("Lastname"),
                rs.getString("Mail"),
                rs.getString("Username"),
                rs.getString("Password")
        );
    }
}


