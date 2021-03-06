package com.snapci.microblog.resources;

import com.google.common.base.Optional;
import com.snapci.microblog.core.ErrorResponse;
import com.snapci.microblog.core.User;
import com.snapci.microblog.jdbi.UserDAO;
import com.snapci.microblog.views.NewUserView;
import com.snapci.microblog.views.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static javax.ws.rs.core.Response.*;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO dao;

    public UserResource(UserDAO dao) {
        this.dao = dao;
    }

    final static Logger logger = LoggerFactory.getLogger(UserResource.class);


    @POST
    public Response create(@FormParam("name") String name) {
        logger.info("got here");
        logger.info(name);
        User user = new User(name);
        try {
            dao.create(user);
            logger.info("created");
        } catch (Exception e) {
            return ErrorResponse.fromException(e).build();
        }
        URI location = UriBuilder.fromPath(user.getName().toLowerCase()).build();
        return Response.created(location).build();
    }

    @GET
    public Response show(@QueryParam("name") Optional<String> name) {
        User user = dao.findByName(name.or("1"));
        if (user == null) {
            return new ErrorResponse(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(user).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_HTML)
    public UserView showAllUsers() {
        return new UserView(dao.findAll());
    }


    @GET
    @Path("/new")
    @Produces(MediaType.TEXT_HTML)
    public NewUserView newUser() {
        return new NewUserView();

    }

}
