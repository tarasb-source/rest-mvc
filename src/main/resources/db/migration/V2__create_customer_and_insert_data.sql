
CREATE TABLE IF NOT EXISTS customer
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    firstname VARCHAR(255) NULL,
    lastname  VARCHAR(255) NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);
