package com.syntech.contactcrud.api;

import com.syntech.contactcrud.model.User;
import com.syntech.contactcrud.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestApi {

    @Inject
    private UserRepository userRepository;

    @GET
    public Response getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (User user : users) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                        .add("id", user.getId())
                        .add("name", user.getName())
                        .add("maritalStatus", user.getMaritalStatus())
                        .add("spouseName", user.getSpouseName());
                arrayBuilder.add(objectBuilder);
            }
            JsonValue jsonResult = arrayBuilder.build();
            return RestResponse.responseBuilder("true", "200", "Users retrieved successfully", jsonResult);
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        try {
            User user = userRepository.findById(id);
            if (user != null) {
                JsonObject jsonResult = Json.createObjectBuilder()
                        .add("id", user.getId())
                        .add("name", user.getName())
                        .add("maritalStatus", user.getMaritalStatus())
                        .add("spouseName", user.getSpouseName())
                        .build();
                return RestResponse.responseBuilder("true", "200", "User found", jsonResult);
            } else {
                return RestResponse.responseBuilder("false", "404", "User not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @POST
    public Response createUser(User user) {
        try {
            userRepository.save(user);
            JsonObject jsonResult = Json.createObjectBuilder()
                    .add("id", user.getId())
                    .add("name", user.getName())
                    .add("maritalStatus", user.getMaritalStatus())
                    .add("spouseName", user.getSpouseName())
                    .build();
            return RestResponse.responseBuilder("true", "201", "User created successfully", jsonResult);
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "400", "Failed to create user", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        try {
            User user = userRepository.findById(id);
            if (user != null) {
                userRepository.delete(id);
                return RestResponse.responseBuilder("true", "204", "User deleted successfully", JsonValue.EMPTY_JSON_OBJECT);
            } else {
                return RestResponse.responseBuilder("false", "404", "User not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, User user) {
        try {
            User existingUser = userRepository.findById(id);
            if (existingUser != null) {
                existingUser.setName(user.getName());
                existingUser.setMaritalStatus(user.getMaritalStatus());
                existingUser.setSpouseName(user.getSpouseName());

                userRepository.update(existingUser);
                JsonObject jsonResult = Json.createObjectBuilder()
                        .add("id", existingUser.getId())
                        .add("name", existingUser.getName())
                        .add("maritalStatus", user.getMaritalStatus())
                        .add("spouseName", user.getSpouseName())
                        .build();
                return RestResponse.responseBuilder("true", "200", "User updated successfully", jsonResult);
            } else {
                return RestResponse.responseBuilder("false", "404", "User not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }
}
