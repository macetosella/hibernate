CREATE SEQUENCE public.generic_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- DROP TABLE public.product_type;

CREATE TABLE public.product_type (
    prot_id integer NOT NULL,
    prot_name varchar(48) NOT NULL,
    CONSTRAINT product_type_pkey PRIMARY KEY (prot_id)
);

-- DROP TABLE public.product;

CREATE TABLE public.product (
    pro_id integer NOT NULL,
    pro_name varchar(48) NOT NULL,
    pro_description varchar(200) NOT NULL,
    pro_price integer NOT NULL,
    prot_id integer NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (pro_id),
    CONSTRAINT fk_to_type FOREIGN KEY (prot_id) REFERENCES public.product_type (prot_id)
);

-- DROP TABLE public.product_detail;

CREATE TABLE public.product_detail (
    pro_id integer NOT NULL,
    prod_tax integer NOT NULL,
    prod_in time NOT NULL,
    prod_out time,
    prod_observation varchar(200) NOT NULL,
    CONSTRAINT product_detail_pkey PRIMARY KEY (pro_id)
);

