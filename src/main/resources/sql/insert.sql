
--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, isadmin, version, group_id) VALUES (1, 'admin@election.com', false, 'Admin', 'azerty', 'admin', 'ADMIN', true, 0, NULL);
INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, isadmin, version, group_id) VALUES (2, 'user1@election.com', false, 'user1', 'azerty', 'user1', 'ELECTEUR', false, 0, NULL);
INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, isadmin, version, group_id) VALUES (3, 'user2@election.com', false, 'user2', 'azerty', 'user2', 'ELECTEUR', false, 0, NULL);
INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, isadmin, version, group_id) VALUES (4, 'user3@election.com', false, 'user3', 'azerty', 'user3', 'ELECTEUR', false, 0, NULL);
INSERT INTO person (id, email, hasvoted, nom, passwrd, prenom, role, isadmin, version, group_id) VALUES (5, 'user4@election.com', false, 'user4', 'azerty', 'user4', 'ELECTEUR', false, 0, NULL);

SELECT pg_catalog.setval('person_id_seq', 5, true);
--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO project (id, description, nom, note) VALUES (1, 'Projet1', 'Projet1', 0);
INSERT INTO project (id, description, nom, note) VALUES (2, 'Projet2', 'Projet2', 0);
INSERT INTO project (id, description, nom, note) VALUES (3, 'Projet3', 'Projet3', 0);
INSERT INTO project (id, description, nom, note) VALUES (4, 'Projet4', 'Projet4', 0);
INSERT INTO project (id, description, nom, note) VALUES (5, 'Projet5', 'Projet5', 0);


--
-- Name: project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('project_id_seq', 5, true);


INSERT INTO groupe(id, description, nom, projet_id) VALUES (1, 'Groupe1', 'Group1', 1);
INSERT INTO groupe(id, description, nom, projet_id) VALUES (2, 'Groupe2', 'Group2', 2);
INSERT INTO groupe(id, description, nom, projet_id) VALUES (3, 'Groupe3', 'Group3', 3);
INSERT INTO groupe(id, description, nom, projet_id) VALUES (4, 'Groupe4', 'Group4', 4);
INSERT INTO groupe(id, description, nom, projet_id) VALUES (5, 'Groupe5', 'Group5', 5);

SELECT pg_catalog.setval('groupe_id_seq', 5, true);
