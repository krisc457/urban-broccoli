package com.example.repository;


import com.example.domain.Post;
import com.example.domain.Thread;
import com.example.domain.UserLogin;
import com.example.domain.UserSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Repository implements IUser {

    @Autowired
    DataSource dataSource;


    public UserLogin getUserLogin(String Username, String Password) throws Exception {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT Username, Password, userId FROM [dbo].[ForumUser] WHERE Username= ? AND Password= ?")) {
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
    public void addPost(String text, long threadId, long userId, java.sql.Date date) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[posts](text, threadId, userId, entrydate)VALUES (?,?,?,?)", new String[]{"Id"})) {
            ps.setString(1, text);
            ps.setLong(2, threadId);
            ps.setLong(3, userId);
            ps.setDate(4, date);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
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
    public List<Post> listPosts(long threadId) throws Exception {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT forumUser.[userId], posts.threadId, posts.text, [Username], [postId], posts.entrydate FROM [dbo].[forumUser] INNER JOIN posts on posts.[userid] = forumUser.userId WHERE threadid = ? ORDER BY posts.entrydate DESC")) {
            ps.setLong(1, threadId);

            try (ResultSet rs = ps.executeQuery()) {

                List<Post> posts = new ArrayList<>();

                while (rs.next())
                    posts.add(rsPost(rs));
                return posts;
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }
    }

    @Override
    public Thread getThread(long id) {
        return null;
    }

    private UserLogin rsUserLogin(ResultSet rs) throws SQLException {
        return new UserLogin(
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getLong("userId")
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
                rs.getLong("userId"),
                rs.getString("username"),
                rs.getTimestamp("entrydate").toLocalDateTime()
        );
    }
}

