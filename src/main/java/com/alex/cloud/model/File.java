package com.alex.cloud.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    private byte[] bytes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public File() {
    }

    public File(String name, byte[] bytes, User user) {
        this.name = name;
        this.bytes = bytes;
        this.user = user;
    }

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

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
