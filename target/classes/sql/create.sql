CREATE TABLE "USER"( id serial NOT NULL, nom text NOT NULL, prenom text NOT NULL, email text NOT NULL, pass text NOT NULL, role text NOT NULL, hasvoted boolean, version INT, CONSTRAINT "USER_pkey" PRIMARY KEY (id), CONSTRAINT "USER_email_key" UNIQUE (email));
CREATE TABLE "PROJET"( id serial NOT NULL, nom text NOT NULL, description text NOT NULL, note integer NOT NULL, version INT, CONSTRAINT "PROJET_pkey" PRIMARY KEY (id));