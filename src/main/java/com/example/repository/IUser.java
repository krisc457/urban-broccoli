package com.example.repository;


import com.example.domain.Post;
import com.example.domain.Thread;

import java.util.List;

public interface IUser {
    void addUser(String Firstname, String Lastname, String Mail, String Username, String Password) throws Exception;
    List<Thread> listThreads() throws Exception;
    Thread getThread (long id) throws Exception;
    List<Post> listPosts () throws Exception;
    void addPost(String text, long postId) throws Exception;
}
