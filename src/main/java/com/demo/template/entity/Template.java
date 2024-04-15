package com.demo.template.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TEMPLATE")
public class Template implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID templateId;
    private String title;
    private String subTitle;
    private String name;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
    private List<TemplateRow> data;

}
