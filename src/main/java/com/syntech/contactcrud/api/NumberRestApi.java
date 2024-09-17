package com.syntech.contactcrud.api;

import com.syntech.contactcrud.model.Number;
import com.syntech.contactcrud.repository.NumberRepository;
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

@Path("/numbers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NumberRestApi {

    @Inject
    private NumberRepository numberRepository;

    @GET
    public Response getAllNumbers() {
        try {
            List<Number> numbers = numberRepository.findAll();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Number number : numbers) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                        .add("id", number.getId())
                        .add("age", number.getAge())
                        .add("decimal", number.getDecimal())
                        .add("currency", number.getCurrency())
                        .add("prefix", number.getPrefix())
                        .add("suffix", number.getSuffix())
                        .add("button", number.getButton());
                arrayBuilder.add(objectBuilder);
            }
            JsonValue jsonResult = arrayBuilder.build();
            return RestResponse.responseBuilder("true", "200", "Numbers retrieved successfully", jsonResult);
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @GET
    @Path("/{id}")
    public Response getNumberById(@PathParam("id") Long id) {
        try {
            Number number = numberRepository.findById(id);
            if (number != null) {
                JsonObject jsonResult = Json.createObjectBuilder()
                        .add("id", number.getId())
                        .add("age", number.getAge())
                        .add("decimal", number.getDecimal())
                        .add("currency", number.getCurrency())
                        .add("prefix", number.getPrefix())
                        .add("suffix", number.getSuffix())
                        .add("button", number.getButton())
                        .build();
                return RestResponse.responseBuilder("true", "200", "Number found", jsonResult);
            } else {
                return RestResponse.responseBuilder("false", "404", "Number not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @POST
    public Response createNumber(Number number) {
        try {
            numberRepository.save(number);
            JsonObject jsonResult = Json.createObjectBuilder()
                    .add("id", number.getId())
                    .add("age", number.getAge())
                    .add("decimal", number.getDecimal())
                    .add("currency", number.getCurrency())
                    .add("prefix", number.getPrefix())
                    .add("suffix", number.getSuffix())
                    .add("button", number.getButton())
                    .build();
            return RestResponse.responseBuilder("true", "201", "Number created successfully", jsonResult);
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "400", "Failed to create number", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteNumber(@PathParam("id") Long id) {
        try {
            Number number = numberRepository.findById(id);
            if (number != null) {
                numberRepository.delete(id);
                return RestResponse.responseBuilder("true", "204", "Number deleted successfully", JsonValue.EMPTY_JSON_OBJECT);
            } else {
                return RestResponse.responseBuilder("false", "404", "Number not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateNumber(@PathParam("id") Long id, Number number) {
        try {
            Number existingNumber = numberRepository.findById(id);
            if (existingNumber != null) {
                existingNumber.setAge(number.getAge());
                existingNumber.setDecimal(number.getDecimal());
                existingNumber.setCurrency(number.getCurrency());
                existingNumber.setPrefix(number.getPrefix());
                existingNumber.setSuffix(number.getSuffix());
                existingNumber.setButton(number.getButton());

                numberRepository.update(existingNumber);
                JsonObject jsonResult = Json.createObjectBuilder()
                        .add("id", number.getId())
                        .add("age", number.getAge())
                        .add("decimal", number.getDecimal())
                        .add("currency", number.getCurrency())
                        .add("prefix",number.getPrefix())
                        .add("suffix",number.getSuffix())
                        .add("button",number.getButton())
                        .build();
                return RestResponse.responseBuilder("true", "200", "Number updated successfully", jsonResult);
            } else {
                return RestResponse.responseBuilder("false", "404", "Number not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }
}
