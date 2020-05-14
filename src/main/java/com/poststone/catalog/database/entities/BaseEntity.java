package com.poststone.catalog.database.entities;

import java.io.Serializable;

public interface BaseEntity<PK extends Serializable> {

    PK getId();
}
