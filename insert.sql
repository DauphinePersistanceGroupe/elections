--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, version, group_id) VALUES (1, 'barou2000@hotmail.com', false, 'barou', 'azerty', 'gnepa', 'ADMIN', 0, NULL);
INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, version, group_id) VALUES (2, 'test@election.com', false, 'uerTest', 'azerty', 'test', 'ELECTEUR', 0, NULL);


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO project (id, description, nom, note) VALUES (1, 'Projet', 'Projet1', NULL);
INSERT INTO project (id, description, nom, note) VALUES (2, 'Projet2', 'Projet2', NULL);


--
-- Name: project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('project_id_seq', 2, true);


--
-- PostgreSQL database dump complete
--

