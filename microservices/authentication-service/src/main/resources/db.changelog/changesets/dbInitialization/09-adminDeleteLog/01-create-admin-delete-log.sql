-- liquibase formatted sql
--changeset kiran.khanal:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `admin_delete_log`
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NOT NULL,
    remarks     TEXT                  NOT NULL,
    admin       BIGINT                NOT NULL,
    deleted_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_admin_block_log PRIMARY KEY (id)
);