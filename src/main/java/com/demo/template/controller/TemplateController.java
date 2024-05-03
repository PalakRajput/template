package com.demo.template.controller;

import com.demo.template.entity.Template;
import com.demo.template.entity.TemplateColumn;
import com.demo.template.entity.TemplateRow;
import com.demo.template.repository.TemplateRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/template")
@Slf4j
public class TemplateController {

    @Autowired
    private TemplateRepository templateRepository;
    @Value("${spring.application.name}")
    String name;

    @GetMapping
    public ResponseEntity<Object> getAllTemplates() {
        log.info("Fetch all templates.");
        log.info("Reading values from app settings: {}", name);
        List<Template> templates = templateRepository.findAll();
        if (templates.isEmpty()) {
            log.error("No templates found.");
            return ResponseEntity.status(404).body(Collections.emptyList());
        }
        log.info("Found {} templates", templates.size());
        return ResponseEntity.status(200).body(templates);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
        //To save all children in one call otherwise the foreign key is null because only parent is aware of child but child is not aware of parent
        for (TemplateRow row : template.getData()) {
            row.setTemplate(template);
            for (TemplateColumn column : row.getColumnInfo()) {
                column.setRow(row);
            }
        }
        log.info("Saving templates.");
        Template entity = templateRepository.save(template);
        return ResponseEntity.status(201).body(entity);
    }
}
