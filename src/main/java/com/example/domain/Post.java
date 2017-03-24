package com.example.domain;

import java.time.LocalDateTime;

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
}
