-- SCHEMA: public
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

CREATE TABLE IF NOT EXISTS public.tb_user (
  pk_id BIGINT NOT NULL
    CONSTRAINT tb_user_pkey
    PRIMARY KEY,
  name VARCHAR(255)
);

-- SCHEMA: tenant_01
CREATE SCHEMA tenant_01;

CREATE SEQUENCE IF NOT EXISTS tenant_01.hibernate_sequence;

CREATE TABLE IF NOT EXISTS tenant_01.tb_user (
  pk_id BIGINT NOT NULL
    CONSTRAINT tb_user_pkey
    PRIMARY KEY,
  name VARCHAR(255)
);

-- SCHEMA: tenant_02
CREATE SCHEMA tenant_02;

CREATE SEQUENCE IF NOT EXISTS tenant_02.hibernate_sequence;

CREATE TABLE IF NOT EXISTS tenant_02.tb_user (
  pk_id BIGINT NOT NULL
    CONSTRAINT tb_user_pkey
    PRIMARY KEY,
  name VARCHAR(255)
);
