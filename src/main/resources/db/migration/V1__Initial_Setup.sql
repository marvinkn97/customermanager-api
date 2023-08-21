CREATE SEQUENCE customer_id_sequence;

CREATE TABLE tbl_customers(
id BIGINT DEFAULT nextval('customer_id_sequence') PRIMARY KEY,
name TEXT NOT NULL,
email TEXT UNIQUE NOT NULL,
mobile TEXT NOT NULL
);