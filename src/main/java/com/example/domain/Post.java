package com.example.domain;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2017-03-22.
 */
public class Post {

    public long postId;
    public String text;
    public long threadId;
    public long userId;
    public String username;
    public final LocalDateTime entrydate;

    public Post(long postId, String text, long threadId, long userId, String username, LocalDateTime entrydate) {
        this.postId = postId;
        this.text = text;
        this.threadId = threadId;
        this.userId = userId;
        this.username = username;
        this.entrydate = entrydate;
    }
    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return entrydate.format(formatter);
    }
}
