package com.syntech.contactcrud.api;

import com.syntech.contactcrud.model.Contact;
import com.syntech.contactcrud.repository.ContactRepository;
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

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactRestApi {

    @Inject
    private ContactRepository contactRepository;

    @GET
    public Response getAllContacts() {
        try {
            List<Contact> contacts = contactRepository.findAll();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Contact contact : contacts) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                        .add("id", contact.getId())
                        .add("name", contact.getName())
                        .add("subject", contact.getSubject())
                        .add("email", contact.getEmail())
                        .add("message", contact.getMessage());
                arrayBuilder.add(objectBuilder);
            }
            JsonValue jsonResult = arrayBuilder.build();
            return RestResponse.responseBuilder("true", "200", "Contacts retrieved successfully", jsonResult);
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @GET
    @Path("/{id}")
    public Response getContactById(@PathParam("id") Long id) {
        try {
            Contact contact = contactRepository.findById(id);
            if (contact != null) {
                JsonObject jsonResult = Json.createObjectBuilder()
                        .add("id", contact.getId())
                        .add("name", contact.getName())
                        .add("subject", contact.getSubject())
                        .add("email", contact.getEmail())
                        .add("message", contact.getMessage())
                        .build();
                return RestResponse.responseBuilder("true", "200", "Contact found", jsonResult);
            } else {
                return RestResponse.responseBuilder("false", "404", "Contact not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @POST
    public Response createContact(Contact contact) {
        try {
            contactRepository.save(contact);
            JsonObject jsonResult = Json.createObjectBuilder()
                    .add("id", contact.getId())
                    .add("name", contact.getName())
                    .add("subject", contact.getSubject())
                    .add("email", contact.getEmail())
                    .add("message", contact.getMessage())
                    .build();
            return RestResponse.responseBuilder("true", "201", "Contact created successfully", jsonResult);
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "400", "Failed to create contact", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") Long id) {
        try {
            Contact contact = contactRepository.findById(id);
            if (contact != null) {
                contactRepository.delete(id);
                return RestResponse.responseBuilder("true", "204", "Contact deleted successfully", JsonValue.EMPTY_JSON_OBJECT);
            } else {
                return RestResponse.responseBuilder("false", "404", "Contact not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") Long id, Contact contact) {
        try {
            Contact existingContact = contactRepository.findById(id);
            if (existingContact != null) {
                existingContact.setName(contact.getName());
                existingContact.setEmail(contact.getEmail());
                existingContact.setSubject(contact.getSubject());

                existingContact.setMessage(contact.getMessage());
                contactRepository.update(existingContact);
                JsonObject jsonResult = Json.createObjectBuilder()
                        .add("id", existingContact.getId())
                        .add("name", existingContact.getName())
                        .add("email", existingContact.getEmail())
                        .add("subject", contact.getSubject())
                        .add("message", existingContact.getMessage())
                        .build();
                return RestResponse.responseBuilder("true", "200", "Contact updated successfully", jsonResult);
            } else {
                return RestResponse.responseBuilder("false", "404", "Contact not found", JsonValue.EMPTY_JSON_OBJECT);
            }
        } catch (Exception e) {
            return RestResponse.responseBuilder("false", "500", "An error occurred", Json.createObjectBuilder().add("error", e.getMessage()).build());
        }
    }
}
