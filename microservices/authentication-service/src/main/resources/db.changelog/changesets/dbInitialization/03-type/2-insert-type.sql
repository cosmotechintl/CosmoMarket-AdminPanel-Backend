-- liquibase formatted sql
--changeset manjul.tamang:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM type
INSERT INTO type (name,description, version)
VALUES
    ('ADMIN','ADMIN',0),
    ('VENDOR','VENDOR',0);
