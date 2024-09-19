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
    private Long id;
    private int age;
    private double decimals;
    private double currency;
    private double preffix;
    private double suffix;
    private int button;

    public Number() {
    }

    public Number(Long id, int age, double decimals, double currency, double preffix, double suffix, int button) {
        this.id = id;
        this.age = age;
        this.decimals = decimals;
        this.currency = currency;
        this.preffix = preffix;
        this.suffix = suffix;
        this.button = button;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + this.age;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.decimals) ^ (Double.doubleToLongBits(this.decimals) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.currency) ^ (Double.doubleToLongBits(this.currency) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.preffix) ^ (Double.doubleToLongBits(this.preffix) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.suffix) ^ (Double.doubleToLongBits(this.suffix) >>> 32));
        hash = 79 * hash + this.button;
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
        if (this.age != other.age) {
            return false;
        }
        if (Double.doubleToLongBits(this.decimals) != Double.doubleToLongBits(other.decimals)) {
            return false;
        }
        if (Double.doubleToLongBits(this.currency) != Double.doubleToLongBits(other.currency)) {
            return false;
        }
        if (Double.doubleToLongBits(this.preffix) != Double.doubleToLongBits(other.preffix)) {
            return false;
        }
        if (Double.doubleToLongBits(this.suffix) != Double.doubleToLongBits(other.suffix)) {
            return false;
        }
        if (this.button != other.button) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    

    

    @Override
    public String toString() {
        return "Number{" + "id=" + id + ", age=" + age + ", decimals=" + decimals + ", currency=" + currency + ", preffix=" + preffix + ", suffix=" + suffix + ", button=" + button + '}';
    }
    
    

}
