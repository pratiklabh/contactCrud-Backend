package com.syntech.contactcrud.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    public Contact() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 20, message = "name should be at least 2 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name should be a valid string")
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @Column(nullable = false, unique = true)
    @Email(message = "Email must be a valid email address")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Column(nullable = false, unique = true)
    private String subject;

    @Column(nullable = false, unique = true)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Contact(String name, String email, String subject,String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    //For proper display in the API
    @Override
    public String toString() {
        return "Customer{"
                + "id=" + id
                + ", name=" + name
                + ", email=" + email
                + ", subject=" + subject
                + ", message=" + message
                + '}';
    }

}
