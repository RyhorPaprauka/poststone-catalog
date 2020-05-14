CREATE TABLE product
(
    id        UUID PRIMARY KEY,
    type      VARCHAR(50)  NOT NULL,
    price     NUMERIC      NOT NULL,
    icon      VARCHAR(256) NOT NULL,
    height    FLOAT8       NOT NULL,
    width     FLOAT8       NOT NULL,
    depth     FLOAT8       NOT NULL,
    thickness FLOAT8
);

CREATE TABLE description
(
    id          UUID PRIMARY KEY,
    product_id  UUID        NOT NULL REFERENCES product (id),
    locale      VARCHAR(5)  NOT NULL,
    title       VARCHAR(50) NOT NULL,
    description TEXT        NOT NULL,
    UNIQUE (product_id, locale)
);

CREATE TABLE material
(
    id   UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE image
(
    product_id UUID         NOT NULL REFERENCES product (id),
    url        VARCHAR(256) NOT NULL UNIQUE,
    PRIMARY KEY (product_id, url)
);

CREATE TABLE product_material
(
    product_id  UUID NOT NULL REFERENCES product (id),
    material_id UUID NOT NULL REFERENCES material (id),
    PRIMARY KEY (product_id, material_id)
);

