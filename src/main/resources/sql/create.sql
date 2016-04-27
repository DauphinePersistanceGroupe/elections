CREATE TABLE "PERSON"( id integer NOT NULL, nom text NOT NULL, prenom text NOT NULL, email text NOT NULL, pass text NOT NULL, role text NOT NULL, hasvoted boolean, version INT, CONSTRAINT "PERSON_pkey" PRIMARY KEY (id), CONSTRAINT "PERSON_email_key" UNIQUE (email));
CREATE TABLE "PROJECT"( id serial NOT NULL, nom text NOT NULL, description text NOT NULL, note integer NOT NULL, version INT, CONSTRAINT "PROJET_pkey" PRIMARY KEY (id));

CREATE TABLE "GROUPE"(id serial NOT NULL, nom text NOT NULL, projet_id integer NOT NULL, user_id integer NOT NULL, 
CONSTRAINT "GROUPE_pkey" PRIMARY KEY (id),
CONSTRAINT "GROUPE_projet_id_fkey" FOREIGN KEY (projet_id)
      REFERENCES "PROJECT" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "GROUPE_user_id_fkey" FOREIGN KEY (user_id)
      REFERENCES "USER" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "PROJECT_unique_key" UNIQUE (projet_id, user_id)
);