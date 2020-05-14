package com.poststone.catalog.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parent;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Table(name = "image")
public class Image {

    @Parent
    private Product product;

    @Column(name = "url", nullable = false, unique = true)
    private String url;
}
