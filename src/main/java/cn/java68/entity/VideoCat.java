package cn.java68.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class VideoCat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String imageName;

    @Lob
    @Column(columnDefinition="TEXT")
    private String catDesc;

    private Integer categoryId;
}
