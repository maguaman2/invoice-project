CREATE TABLE IF NOT EXISTS product(
    id SERIAL,
    description VARCHAR (100) NOT NULL,
    brand VARCHAR (100),
    stock INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS details(
    id SERIAL,
    product_id INT NOT NULL,
    invoice_id INT NOT NULL,
    quantity INT,
    UNIQUE (product_id,invoice_id),
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (invoice_id) REFERENCES invoice (id)
);