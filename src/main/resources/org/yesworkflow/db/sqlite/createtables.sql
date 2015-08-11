
/******************************************* SQLite Notes ************************************************* 

- Primary key columns are of type 'INTEGER' so that they are aliases for the builtin 64-bit 'rowid' column.
  See http://www.sqlite.org/lang_createtable.html#rowid
  
- TEXT columns that generally would be of type 'char' or 'varchar' are given the type 'TEXT' to reflect
  the dynamic storage of data values in SQLite. See http://www.sqlite.org/datatype3.html
  
**************************************************************************************************/

CREATE TABLE source_file (
    id              INTEGER         NOT NULL        PRIMARY KEY,
    path            TEXT            NOT NULL
);

CREATE TABLE annotation (
    id              INTEGER         NOT NULL		PRIMARY KEY,
    source_file_id  INTEGER         NULL            REFERENCES source_file(id),
    qualifies       INTEGER         NULL            REFERENCES annotation(id),
    line_number     INTEGER         NULL,
    tag             TEXT            NOT NULL,
    keyword         TEXT            NOT NULL,
    value           TEXT            NULL,
    description     TEXT            NULL
);

CREATE TABLE program (
    id              INTEGER         NOT NULL        PRIMARY KEY,
    parent_id       INTEGER         NULL            REFERENCES program(id),
    begin_id        INTEGER         NULL            REFERENCES annotation(id),
    end_id          INTEGER         NULL            REFERENCES annotation(id),
    name            TEXT            NOT NULL,
    qualified_name  TEXT            NOT NULL,
    is_workflow     BOOLEAN         NOT NULL,
    is_function     BOOLEAN         NOT NULL
);

CREATE TABLE data (
    id              INTEGER         NOT NULL        PRIMARY KEY,
    name            TEXT            NOT NULL,
    qualified_name  TEXT            NOT NULL
);

CREATE TABLE port (
    id              INTEGER         NOT NULL        PRIMARY KEY,
    annotation_id   INTEGER         NULL            REFERENCES annotation(id),
    program_id      INTEGER         NOT NULL        REFERENCES program(id),
    data_id         INTEGER         NOT NULL        REFERENCES data(id),
    name            TEXT            NOT NULL,
    qualified_name  TEXT            NOT NULL,
    alias           TEXT            NULL,
    uri_template    TEXT            NULL,
    is_inport       BOOLEAN         NOT NULL,
    is_outport      BOOLEAN         NOT NULL
);

CREATE TABLE channel (
    id              INTEGER         NOT NULL        PRIMARY KEY,
    data_id         INTEGER         NOT NULL        REFERENCES data(id),
    out_port_id     INTEGER         NOT NULL        REFERENCES port(id),
    in_port_id      INTEGER         NOT NULL        REFERENCES port(id),
    is_inflow       BOOLEAN         NOT NULL,
    is_outflow      BOOLEAN         NOT NULL
);

CREATE TABLE uri_variable (
    id              INTEGER         NOT NULL        PRIMARY KEY,
    port_id         INTEGER         NOT NULL        REFERENCES port(id),
    name            TEXT            NOT NULL
);

CREATE TABLE resource (
    id              INTEGER         NOT NULL        PRIMARY KEY,
    uri             TEXT            NOT NULL
);

CREATE TABLE data_resource (
    data_id         INTEGER         NOT NULL        REFERENCES data(id),
    resource_id     INTEGER         NOT NULL        REFERENCES resource(id)
);

CREATE TABLE uri_variable_value (
    resource_id     INTEGER         NOT NULL        REFERENCES rsource(id),
    uri_variable_id INTEGER         NOT NULL        REFERENCES uri_variable(id),
    value           TEXT            NOT NULL
);