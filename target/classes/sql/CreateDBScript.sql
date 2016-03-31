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

  -- Table: elections."USER"

-- DROP TABLE elections."USER";

CREATE TABLE elections."USER"
(
  id serial NOT NULL,
  nom text NOT NULL,
  prenom text NOT NULL,
  email text NOT NULL,
  pass text NOT NULL,
  role text NOT NULL,
  hasvoted boolean,
  CONSTRAINT "USER_pkey" PRIMARY KEY (id),
  CONSTRAINT "USER_email_key" UNIQUE (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE elections."USER"
  OWNER TO postgres;

  
-- Table: elections."PROJET"

-- DROP TABLE elections."PROJET";

CREATE TABLE elections."PROJET"
(
  id serial NOT NULL, -- Identifiant unique PK
  nom text NOT NULL, -- Nom du groupe
  description text, -- Description du groupe
  note integer DEFAULT 0, -- Score du groupe à l'election
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



-- Table: elections."GROUPE"

-- DROP TABLE elections."GROUPE";

CREATE TABLE elections."GROUPE"
(
  id serial NOT NULL, -- PRimary key
  nom text NOT NULL,
  projet_id integer NOT NULL,
  user_id integer NOT NULL,
  CONSTRAINT "GROUPE_pkey" PRIMARY KEY (id),
  CONSTRAINT "GROUPE_projet_id_fkey" FOREIGN KEY (projet_id)
      REFERENCES elections."PROJET" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "GROUPE_user_id_fkey" FOREIGN KEY (user_id)
      REFERENCES elections."USER" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "PROJET_unique_key" UNIQUE (projet_id, user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE elections."GROUPE"
  OWNER TO postgres;
COMMENT ON COLUMN elections."GROUPE".id IS 'PRimary key';

 
  INSERT INTO elections."USER"(nom, prenom, email, pass, role,hasvoted)
    VALUES ('barou', 'rene', 'barou2000@gmail.com', 'barou', 'admin',false);

  INSERT INTO elections."USER"( nom, prenom, email, pass, role,hasvoted)
    VALUES ('UserTest', 'rene', 'UserTest@gmail.com', 'UserTest', 'participant',false);