package model;


import jakarta.persistence.*;

@MappedSuperclass
public abstract class Event {


    private String type;

    private String name;

    private String description;

    public abstract void updateQuantity(String name, int qnt);

    public abstract void setUp(String type, Mode mode);
}