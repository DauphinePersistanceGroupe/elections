/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.controller;

import java.sql.*;

import fr.dauphine.lamsade.hib.elections.domain.Projet;

/**
 * @author omar.trabelsi
 *
 */
public class ProjetController {

	static Projet projet = new Projet();

	public static void main(String[] args) {
		// get connection
		Connection connection = projet.getConnection();
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		// get all projects
		try {
			projet.getAllProjects(connection);

		} catch (SQLException e) {
			System.out.println("Failed to get all projects!");
			e.printStackTrace();
		}
		// Add a test project
		try {
			projet.addProject(connection, "test3", "test3");
		} catch (SQLException e) {
			System.out.println("Failed to add the project!");
			e.printStackTrace();
		}

		// delete a project
		try {
			projet.deleteProject(connection, 11);
		} catch (SQLException e) {
			System.out.println("Failed to delete the project!");
			e.printStackTrace();
		}

		// update a project description
		try {
			projet.updateProject(connection, 6, "test6 ");
		} catch (SQLException e) {
			System.out.println("Failed to update the project!");
			e.printStackTrace();
		}

	}

}
