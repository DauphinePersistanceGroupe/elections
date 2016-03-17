-- Database: elections

-- DROP DATABASE elections;

CREATE DATABASE elections
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'French_France.1252'
       LC_CTYPE = 'French_France.1252'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE elections
  IS 'Projet d''Élection du meilleur projet';
  
 -- Schema: elections

-- DROP SCHEMA elections;

CREATE SCHEMA elections
  AUTHORIZATION postgres;
  
-- Table: elections."PROJET"

-- DROP TABLE elections."PROJET";

CREATE TABLE elections."PROJET"
(
  id serial NOT NULL, -- Identifiant unique PK
  nom text NOT NULL, -- Nom du groupe
  description text, -- Description du groupe
  note integer default 0, -- Description du groupe
  CONSTRAINT "PROJET_pkey" PRIMARY KEY (id),
  CONSTRAINT "PROJET_nom_key" UNIQUE (nom)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE elections."PROJET"
  OWNER TO postgres;
COMMENT ON COLUMN elections."PROJET".id IS 'Identifiant unique PK';
COMMENT ON COLUMN elections."PROJET".nom IS 'Nom du groupe';
COMMENT ON COLUMN elections."PROJET".description IS 'Description du groupe';
COMMENT ON COLUMN elections."PROJET".note IS 'Score du groupe à l''election';


  
  -- Table: elections."PARTICIPANT"

DROP TABLE elections."USER";

CREATE TABLE elections."USER"
(
  id serial NOT NULL,
  nom text NOT NULL,
  prenom text NOT NULL,
  email text NOT NULL,
  pass text NOT NULL,
  role text NOT NULL,
  hasVoted boolean,
  CONSTRAINT "USER_pkey" PRIMARY KEY (id),
  CONSTRAINT "USER_email_key" UNIQUE (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE elections."USER"
  OWNER TO postgres;


-- Table: elections."GROUPE"

-- DROP TABLE elections."GROUPE";

CREATE TABLE elections."GROUPE"
(
  projet_id integer,
  user_id integer,
  CONSTRAINT "GROUPE_pkey" PRIMARY KEY (projet_id,user_id),
  CONSTRAINT "GROUPE_projet_id_fkey" FOREIGN KEY (projet_id)
      REFERENCES elections."PROJET" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT "GROUPE_user_id_fkey" FOREIGN KEY (user_id)
      REFERENCES elections."USER" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE elections."GROUPE"
  OWNER TO postgres;

