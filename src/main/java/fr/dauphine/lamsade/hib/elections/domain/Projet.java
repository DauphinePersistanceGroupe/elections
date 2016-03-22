/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.domain;

import java.sql.*;

/**
 * @author omar.trabelsi
 *
 */
public class Projet {

	public Connection getConnection() {

		Connection conn = null;

		try {

			conn = DriverManager.getConnection("jdbc:postgresql://localhost/elections", "postgres", "postgres");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}
		return conn;

	}

	public void getAllProjects(Connection conn) throws SQLException {
		Statement stmt = null;
		stmt = conn.createStatement();
		String sql = "SELECT * FROM elections.\"PROJET\" ";
		ResultSet result = stmt.executeQuery(sql);
		System.out.println("List of Projects:");
		while (result.next()) {
			System.out.println("ID : " + result.getString(1));
			System.out.println("Nom : " + result.getString(2));
			System.out.println("Description : " + result.getString(3));
			System.out.println("Vote : " + result.getString(4));
			System.out.println("**********************");
		}
		stmt.close();
	}

	public void addProject(Connection conn, String nom, String descr) throws SQLException {

		Statement stmt = null;
		stmt = conn.createStatement();
		String sql = "INSERT INTO elections.\"PROJET\" (nom, description) VALUES (?,?)";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, nom);
		st.setString(2, descr);
		st.executeUpdate();
		System.out.println("Project added successfully!");
		stmt.close();
	}

	public void deleteProject(Connection conn, int id) throws SQLException {
		Statement stmt = null;
		stmt = conn.createStatement();
		String sql = "DELETE FROM elections.\"PROJET\" WHERE ID = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, id);
		st.executeUpdate();
		System.out.println("Project deleted successfully!");
		stmt.close();

	}

	public void updateProject(Connection conn, int id, String desc) throws SQLException {
		Statement stmt = null;
		stmt = conn.createStatement();
		String sql = "UPDATE elections.\"PROJET\"  SET description = ? WHERE ID = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, desc);
		st.setInt(2, id);
		st.executeUpdate();
		System.out.println("Project updated successfully!");
		stmt.close();

	}

}
