package org.ali.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Emp implements Serializable {
    private int id;
    private int age;
    private String name;
    private char gender;
    private String tel_num;
}
