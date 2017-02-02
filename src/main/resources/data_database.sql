CREATE SCHEMA IF NOT EXISTS cv_sender;
USE cv_sender;

CREATE TABLE companies
(
  id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        VARCHAR(50) NOT NULL ,
  email       VARCHAR(30) NOT NULL ,
  phone       VARCHAR(15) NOT NULL,
  sent_date   DATETIME,
  times_sent  INT DEFAULT 0
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;

CREATE TABLE sending_email_history
(
  id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  company_id  BIGINT,
  sent_date   DATETIME,
  CONSTRAINT FOREIGN KEY (company_id)
    REFERENCES companies(id) ON DELETE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1;