package models;

import java.io.Serializable;

public abstract class Model implements Serializable {
    private long id;

    public Model() {
    }

    public Model(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    abstract public String toString();
}
