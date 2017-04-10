package com.epam.entity;

/**
 * Created by Hleb_Pus on 11/10/2015.
 */
public abstract class Entity {

    private long id;

    public Entity() {
    }

    public Entity(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
