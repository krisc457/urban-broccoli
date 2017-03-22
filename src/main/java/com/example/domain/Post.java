package com.example.domain;

/**
 * Created by Administrator on 2017-03-22.
 */
public class Post {

    public long postId;
    public String text;
    public long threadId;
    public long userId;

    public Post(long postId, String text, long threadId, long userId) {
        this.postId = postId;
        this.text = text;
        this.threadId = threadId;
        this.userId = userId;
    }
}
