CREATE SCHEMA IF NOT EXISTS cv_sender;
USE cv_sender;

CREATE TABLE companies
(
  id        BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name      VARCHAR(50) NOT NULL ,
  email     VARCHAR(30) NOT NULL ,
  phone     VARCHAR(15) NOT NULL,
  sent_date DATE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;