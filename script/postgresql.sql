-- Postgresql 9.6

CREATE SEQUENCE public.category_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.category_seq
    OWNER TO postgres;

CREATE SEQUENCE public.meeting_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.meeting_seq
    OWNER TO postgres;

CREATE SEQUENCE public.student_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.student_seq
    OWNER TO postgres;

CREATE SEQUENCE public.product_type_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.product_type_seq
    OWNER TO postgres;

CREATE SEQUENCE public.product_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.product_seq
    OWNER TO postgres;

CREATE SEQUENCE public.employee_seq
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

ALTER SEQUENCE public.employee_seq
OWNER TO postgres;

CREATE SEQUENCE public.company_seq
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

ALTER SEQUENCE public.company_seq
OWNER TO postgres;

CREATE SEQUENCE public.country_seq
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

ALTER SEQUENCE public.country_seq
OWNER TO postgres;

CREATE SEQUENCE public.sq_entity
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.sq_entity
    OWNER TO postgres;

CREATE SEQUENCE public.sq_person
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.sq_person
    OWNER TO postgres;

CREATE SEQUENCE public.sq_person3
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.sq_person3
    OWNER TO postgres;

CREATE SEQUENCE public.sq_employee
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.sq_employee
    OWNER TO postgres;

CREATE SEQUENCE public.sq_employee3
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.sq_employee3
    OWNER TO postgres;

CREATE SEQUENCE public.sq_owner
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.sq_owner
    OWNER TO postgres;

CREATE SEQUENCE public.sq_owner2
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER TABLE public.employee ALTER COLUMN emp_id SET DEFAULT nextval('employee_emp_id_seq'::regclass);

ALTER SEQUENCE public.sq_owner2
    OWNER TO postgres;



-- Table: public.employee

-- DROP TABLE public.employee;

CREATE TABLE public.employee
(
  emp_id integer NOT NULL DEFAULT nextval('employee_seq'::regclass),
  emp_name character varying(28) COLLATE pg_catalog."default" NOT NULL,
  emp_role character varying(28) COLLATE pg_catalog."default" NOT NULL,
  sys_creation timestamp with time zone NOT NULL,
  CONSTRAINT employee_pkey PRIMARY KEY (emp_id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

DROP TABLE public.employee;

ALTER TABLE public.employee
  OWNER to postgres;



-- Table: public.company

-- DROP TABLE public.company;

CREATE TABLE public.company
(
  com_id integer NOT NULL DEFAULT nextval('company_seq'::regclass),
  com_name character varying(56) COLLATE pg_catalog."default" NOT NULL,
  com_country character varying(56) COLLATE pg_catalog."default" NOT NULL,
  com_email character varying(128) COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT company_pkey PRIMARY KEY (com_id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.company
  OWNER to postgres;



-- Table: public.country

-- DROP TABLE public.country;

CREATE TABLE public.country
(
  cou_id integer NOT NULL DEFAULT nextval('country_seq'::regclass),
  cou_code character varying(10) COLLATE pg_catalog."default" NOT NULL,
  cou_name character varying(128) COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT country_pkey PRIMARY KEY (cou_id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.country
  OWNER to postgres;



-- Table: public.entity

-- DROP TABLE public.entity;

CREATE TABLE public.entity
(
    ent_id integer NOT NULL,
    ent_first_name character varying(50) COLLATE pg_catalog."default",
    ent_last_name character varying(50) COLLATE pg_catalog."default",
    ent_joining_date timestamp without time zone,
    ent_department_name character varying(100) COLLATE pg_catalog."default",
    ent_discriminator character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT entity_pkey PRIMARY KEY (ent_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.ENTITY
    OWNER to postgres;



-- Table: public.person

-- DROP TABLE public.person;

CREATE TABLE public.person
(
    per_person_id integer NOT NULL,
    per_first_name character varying(50) COLLATE pg_catalog."default",
    per_last_name character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT person_pkey PRIMARY KEY (per_person_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.person
    OWNER to postgres;



-- Table: public.person3

-- DROP TABLE public.person3;

CREATE TABLE public.person3
(
    per_person_id integer NOT NULL,
    per_first_name character varying(50) COLLATE pg_catalog."default",
    per_last_name character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT person_pkey3 PRIMARY KEY (per_person_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.person3
    OWNER to postgres;



-- Table: public.employee

-- DROP TABLE public.employee;

CREATE TABLE public.employee
(
    per_person_id integer NOT NULL,
    per_first_name character varying(50) COLLATE pg_catalog."default",
    per_last_name character varying(50) COLLATE pg_catalog."default",
    joining_date timestamp without time zone,
	  department_name character varying(50),
    CONSTRAINT employee_pkey PRIMARY KEY (per_person_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.employee
    OWNER to postgres;



-- Table: public.employee3

-- DROP TABLE public.employee3;

CREATE TABLE public.employee3
(
    per_person_id integer NOT NULL,
    joining_date timestamp without time zone,
	  department_name character varying(50),
    CONSTRAINT employee_pkey3 PRIMARY KEY (per_person_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.employee3
    OWNER to postgres;



-- Table: public.owner

-- DROP TABLE public.owner;

CREATE TABLE public.owner
(
    per_person_id integer NOT NULL,
    per_first_name character varying(50) COLLATE pg_catalog."default",
    per_last_name character varying(50) COLLATE pg_catalog."default",
    stocks integer,
	  partnership_stake integer,
    CONSTRAINT owner_pkey PRIMARY KEY (per_person_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.owner
    OWNER to postgres;



-- Table: public.owner2

-- DROP TABLE public.owner2;

CREATE TABLE public.owner2
(
    per_person_id integer NOT NULL,
    stocks integer,
	  partnership_stake integer,
    CONSTRAINT owner_pkey2 PRIMARY KEY (per_person_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.owner2
    OWNER to postgres;



-- Table: public.product

-- DROP TABLE public.product;

CREATE TABLE public.product
(
    pro_id integer NOT NULL,
    pro_name character varying(48) COLLATE pg_catalog."default" NOT NULL,
    pro_description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    pro_price integer NOT NULL,
    prot_id integer NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (pro_id),
    CONSTRAINT fk_to_type FOREIGN KEY (prot_id)
        REFERENCES public.product_type (prot_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.product
    OWNER to postgres;



-- Table: public.product_detail

-- DROP TABLE public.product_detail;

CREATE TABLE public.product_detail
(
    pro_id integer NOT NULL,
    prod_tax integer NOT NULL,
    prod_in time with time zone NOT NULL,
    prod_out time with time zone,
    prod_observation character varying (200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT product_detail_pkey PRIMARY KEY (pro_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.product_detail
    OWNER to postgres;



-- Table: public.product_type

-- DROP TABLE public.product_type;

CREATE TABLE public.product_type
(
    prot_id integer NOT NULL,
    prot_name character varying(48) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT product_type_pkey PRIMARY KEY (prot_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.product_type
    OWNER to postgres;



-- Table: public.student

-- DROP TABLE public.student;

CREATE TABLE public.student
(
    st_id integer NOT NULL,
    st_name character varying(48) COLLATE pg_catalog."default" NOT NULL,
    st_last_name character varying(48) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (st_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.student
    OWNER to postgres;



-- Table: public.meeting

-- DROP TABLE public.meeting;

CREATE TABLE public.meeting
(
    mit_id integer NOT NULL,
    mit_subject character varying(128) COLLATE pg_catalog."default" NOT NULL,
    mit_date time with time zone NOT NULL,
    CONSTRAINT meeting_pkey PRIMARY KEY (mit_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.meeting
    OWNER to postgres;



-- Table: public.student_meeting

-- DROP TABLE public.student_meeting;

CREATE TABLE public.student_meeting
(
    mit_id integer NOT NULL,
    st_id integer NOT NULL,
    CONSTRAINT student_meeting_pkey PRIMARY KEY (mit_id, st_id),
    CONSTRAINT mit_fk FOREIGN KEY (mit_id)
        REFERENCES public.meeting (mit_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT mit_fk2 FOREIGN KEY (st_id)
        REFERENCES public.student (st_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.student_meeting
    OWNER to postgres;



-- Table: public.category

-- DROP TABLE public.category;

CREATE TABLE public.category
(
    cat_id integer NOT NULL,
    cat_name character varying(48) COLLATE pg_catalog."default" NOT NULL,
    cat_description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    cat_parent integer,
    CONSTRAINT category_pkey PRIMARY KEY (cat_id),
    CONSTRAINT fkcat FOREIGN KEY (cat_parent)
        REFERENCES public.category (cat_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.category
    OWNER to postgres;