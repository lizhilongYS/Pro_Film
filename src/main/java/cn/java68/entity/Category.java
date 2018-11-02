package cn.java68.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="t_category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
