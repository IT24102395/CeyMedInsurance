package com.healthinsurancemanagementsystem.CeyMedInsurance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "article_categories")
public class ArticleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleCategoryId;

    private String categoryName;

    // Getters and Setters
}
