
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


  INSERT INTO "USER"(nom, prenom, email, pass, role,hasvoted)
    VALUES ('barou', 'rene', 'barou2000@gmail.com', 'barou', 'admin',false);

  INSERT INTO "USER"( nom, prenom, email, pass, role,hasvoted)
    VALUES ('UserTest', 'rene', 'UserTest@gmail.com', 'UserTest', 'participant',false);