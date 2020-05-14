package com.poststone.catalog.database.entities.enam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {

    GRAVESTONE("Памятник"),
    FENCE("Ограда"),
    VASE("Ваза");

    private String value;
}
