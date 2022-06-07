package org.ali.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
    private int id;
    private String username;
    private String password;
}
