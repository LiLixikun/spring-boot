package com.example.demo1.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String menuName;

    private String link;

    private String menuIcon;

    private Integer pid=0;

    @Transient
    private List<Menu> children=new ArrayList<>();
}
