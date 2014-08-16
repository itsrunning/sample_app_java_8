package com.snapci.microblog.views;

import com.snapci.microblog.core.MicroBlog;

import java.util.List;

import com.snapci.microblog.core.User;
import com.yammer.dropwizard.views.View;

public class BlogsView extends View {
    private List<MicroBlog> microBlogs;
    private User user;

    public BlogsView(List<MicroBlog> microBlogs, User user) {
        super("microblogs.mustache");
        this.microBlogs = microBlogs;
        this.user = user;
    }

    public List<MicroBlog> getMicroBlogs(){
        return microBlogs;
    }
    public User getUser(){
        return user;
    }
}
