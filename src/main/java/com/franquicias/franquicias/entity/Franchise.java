package com.franquicias.franquicias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table(name = "franchises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Franchise {
    @Id
    private Long id;
    private String name;
    private List<Branch> branches = new ArrayList<>();
}