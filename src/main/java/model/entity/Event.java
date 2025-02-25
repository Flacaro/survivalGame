package model.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class Event implements Serializable {

    private String type;

    private String name;

    private String description;

}