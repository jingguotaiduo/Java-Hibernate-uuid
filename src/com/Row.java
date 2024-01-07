package com;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Row {
    //设置主键
    @Id //配置uuid，原本jpa是不支持uuid的，但借用hibernate的方法能够实现。
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator") // uuid
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}