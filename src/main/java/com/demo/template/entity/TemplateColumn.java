package com.demo.template.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TEMPLATE_COLUMN")
public class TemplateColumn implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID columnId;

    @Enumerated(EnumType.STRING)
    private COLUMN_TYPE columnType;

    @JsonProperty("value")
    private String typeValue;


    @ManyToOne
    @JoinColumn(name="ROW_ID")
    @JsonIgnore
    private TemplateRow row;

}
