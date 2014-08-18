package com.snapci.microblog.views;


import com.snapci.microblog.core.User;
import com.yammer.dropwizard.views.View;

public class NewBlogView extends View {
    private final User user;

    public NewBlogView(User user) {
        super("new_blog.mustache");
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
