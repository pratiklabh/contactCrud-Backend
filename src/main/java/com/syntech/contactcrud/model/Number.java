package com.syntech.contactcrud.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="number")
public class Number implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private double decimals;
    private double currency;
    private double preffix;
    private double suffix;
    private int button;

    public Number() {
    }

    public Number(int id, int age, double decimals, double currency, double preffix, double suffix, int button) {
        this.id = id;
        this.age = age;
        this.decimals = decimals;
        this.currency = currency;
        this.preffix = preffix;
        this.suffix = suffix;
        this.button = button;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getDecimal() {
        return decimals;
    }

    public void setDecimal(double decimals) {
        this.decimals = decimals;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public double getPrefix() {
        return preffix;
    }

    public void setPrefix(double preffix) {
        this.preffix = preffix;
    }

    public double getSuffix() {
        return suffix;
    }

    public void setSuffix(double suffix) {
        this.suffix = suffix;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.age;
        hash = 47 * hash + Objects.hashCode(this.decimals);
        hash = 47 * hash + Objects.hashCode(this.currency);
        hash = 47 * hash + Objects.hashCode(this.preffix);
        hash = 47 * hash + Objects.hashCode(this.suffix);
        hash = 47 * hash + this.button;
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
        final Number other = (Number) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.button != other.button) {
            return false;
        }
        if (!Objects.equals(this.decimals, other.decimals)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.preffix, other.preffix)) {
            return false;
        }
        return Objects.equals(this.suffix, other.suffix);
    }

    

    @Override
    public String toString() {
        return "Number{" + "id=" + id + ", age=" + age + ", decimals=" + decimals + ", currency=" + currency + ", preffix=" + preffix + ", suffix=" + suffix + ", button=" + button + '}';
    }
    
    

}
