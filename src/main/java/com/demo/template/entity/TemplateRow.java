package com.demo.template.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TEMPLATE_ROW")
public class TemplateRow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID rowId;

    @ManyToOne
    @JoinColumn(name="TEMPLATE_ID")
    @JsonIgnore
    private Template template;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    private List<TemplateColumn> columnInfo;
}
