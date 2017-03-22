package com.example.repository;


import com.example.domain.Post;
import com.example.domain.Thread;
import com.example.domain.UserLogin;
import com.example.domain.UserSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[ForumUser](FirstName, LastName, Email, Username, Password)VALUES (?,?,?,?,?)", new String[]{"Id"})) {
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

    @Override
    public List<Thread> listThreads() throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT threadId, title FROM [dbo].[threads]")) {
            List<Thread> posts = new ArrayList<>();

            while (rs.next())
                posts.add(rsThread(rs));
            return posts;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Post> listPosts() throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT postId, text, threadId, userId FROM [dbo].[posts]")) {
            List<Post> posts = new ArrayList<>();

            while (rs.next())
                posts.add(rsPost(rs));
            return posts;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Thread getThread(long id) {
        return null;
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

    public Thread rsThread(ResultSet rs) throws SQLException {
        return new Thread(
                rs.getLong("threadId"),
                rs.getString("title")
        );
    }

    public Post rsPost(ResultSet rs) throws SQLException {
        return new Post (
                rs.getLong("postId"),
                rs.getString("text"),
                rs.getLong("threadId"),
                rs.getLong("userId")
        );
    }
}

