package com.eduardo.entity;

public class UserEntity {

    public Integer id;
    public String name;
    public Integer edad;

    public UserEntity(int id, String name, int edad) {
        this.id = id;
        this.name = name;
        this.edad = edad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

}
