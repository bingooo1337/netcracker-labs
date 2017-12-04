-- noinspection SqlNoDataSourceInspectionForFile
DROP TABLE Params;
DROP TABLE Objects;
DROP TABLE Attributes;
DROP TABLE Object_types;

CREATE TABLE Object_types (
  object_type_id INTEGER       NOT NULL,
  name           VARCHAR2(100) NOT NULL,
  PRIMARY KEY (object_type_id)
);

CREATE TABLE Objects (
  object_id      INTEGER       NOT NULL,
  name           VARCHAR2(100) NULL,
  object_type_id INTEGER       NOT NULL,
  PRIMARY KEY (object_id)
);

CREATE TABLE Attributes (
  attr_id        INTEGER      NOT NULL,
  name           VARCHAR2(20) NOT NULL,
  object_type_id INTEGER      NOT NULL,
  PRIMARY KEY (attr_id)
);

CREATE TABLE Params (
  text_value   VARCHAR2(250) NULL,
  number_value INTEGER       NULL,
  date_value   DATE          NULL,
  attr_id      INTEGER       NOT NULL,
  object_id    INTEGER       NOT NULL
);

CREATE TABLE References (
  attr_id        INTEGER NOT NULL,
  from_object_id INTEGER NOT NULL,
  to_object_id   INTEGER NOT NULL
);

ALTER TABLE Objects
  ADD ( FOREIGN KEY (object_type_id)
REFERENCES Object_types );

ALTER TABLE Attributes
  ADD ( FOREIGN KEY (object_type_id)
REFERENCES Object_types );

ALTER TABLE Params
  ADD ( FOREIGN KEY (object_id)
REFERENCES Objects );

ALTER TABLE Params
  ADD ( FOREIGN KEY (attr_id)
REFERENCES Attributes );

ALTER TABLE References
  ADD ( FOREIGN KEY (from_object_id)
REFERENCES Objects );

ALTER TABLE References
  ADD ( FOREIGN KEY (to_object_id)
REFERENCES Objects );

ALTER TABLE References
  ADD ( FOREIGN KEY (ATTR_ID)
REFERENCES Attributes );