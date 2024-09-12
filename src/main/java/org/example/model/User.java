package org.example.model;

public class User {
    private Long id;
    private String name;
    private String email;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        System.out.println("User model getId");
        return id;
    }

    public String getName() {
        System.out.println("User model getId");
        return name;
    }
}
