package com.syntech.contactcrud.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User implements Serializable {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 20, message = "name should be at least 2 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name should be a valid string")
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Email cannot be empty")
    private String maritalStatus;

    private String spouseName;

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

    public User(Long id, String name, String maritalStatus, String spouseName) {
        this.id = id;
        this.name = name;
        this.maritalStatus = maritalStatus;
        this.spouseName = spouseName;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.maritalStatus);
        hash = 71 * hash + Objects.hashCode(this.spouseName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.maritalStatus, other.maritalStatus)) {
            return false;
        }
        if (!Objects.equals(this.spouseName, other.spouseName)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    

}
