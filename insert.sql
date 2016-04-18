
--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, version, group_id) VALUES (1, 'barou2000@hotmail.com', false, 'barou', 'azerty', 'gnepa', 'ADMIN', 0, NULL);
INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, version, group_id) VALUES (2, 'test@election.com', false, 'userTest', 'azerty', 'test', 'ELECTEUR', 0, NULL);

SELECT pg_catalog.setval('person_id_seq', 2, true);
--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO project (id, description, nom, note) VALUES (1, 'Projet', 'Projet1', 0);
INSERT INTO project (id, description, nom, note) VALUES (2, 'Projet2', 'Projet2', 0);


--
-- Name: project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('project_id_seq', 2, true);


--
-- PostgreSQL database dump complete
--

