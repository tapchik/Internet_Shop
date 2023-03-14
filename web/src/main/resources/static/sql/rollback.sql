DROP TABLE IF EXISTS orders;

CREATE TABLE orders
(
    id integer NOT NULL,
    timestemp character varying(128),
    city character varying(128),
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

INSERT INTO orders
VALUES
(1, 'Аккумулятор', 'cvf'),
(2, 'Боинг-747', 'dfg'),
(3, 'Велосипед', 'fghgf');