package com.snapci.microblog.views;

import com.snapci.microblog.core.User;
import com.yammer.dropwizard.views.View;

import java.util.List;

public class UserView extends View {
    private final List<User> users;


    public UserView(List<User> users) {
        super("users.mustache");
        this.users = users ;
    }

    public List<User> getUsers(){
        return users;
    }
}
