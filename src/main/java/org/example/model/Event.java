package org.example.model;


import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class Event implements Serializable {

    private String type;

    private String name;


    public abstract void updateQuantity(String name, int qnt);

    public abstract void setUp(String type, Mode mode);


}