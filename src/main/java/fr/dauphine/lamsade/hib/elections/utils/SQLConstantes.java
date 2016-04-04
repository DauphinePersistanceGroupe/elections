/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.utils;

/**
 * @author Rene.BAROU
 *
 */
public class SQLConstantes {
	public final static String DB_URL = "jdbc:postgresql://localhost/elections";
	public final static String DB_USER = "postgres";
	public final static String DB_PASS = "postgres";
	
	// Table Person
	public static final String USER_CREATE_SQL = "INSERT INTO elections.\"USER\"( nom, prenom, email, pass, role,hasvoted) VALUES (?, ?, ?, ?, ?,?)";
	public static final String USER_DELETE_SQL = "DELETE FROM elections.\"USER\" WHERE ID = ?";
	public static final String USER_UPDATE_SQL = "UPDATE elections.\"USER\" SET nom=?, prenom=?, email=?, pass=?, role=?, hasvoted=? WHERE id=?;";
	public static final String USER_FINDALL_SQL = "SELECT id, nom, prenom, email, pass, role,hasvoted FROM elections.\"USER\" ORDER BY NOM DESC";
	public static final String USER_FINDBYID_SQL = "SELECT id, nom, prenom, email, pass, role,hasvoted FROM elections.\"USER\" WHERE ID = ?";
	public static final String USER_FINDBYEMAIL_SQL = "SELECT id, nom, prenom, email, pass, role,hasvoted FROM elections.\"USER\" WHERE EMAIL = ?";
	public static final String USER_FINDBYNAME_SQL = "SELECT id, nom, prenom, email, pass, role,hasvoted FROM elections.\"USER\" WHERE NOM = ?";

	//Table Projet
	public static final String PROJET_CREATE_SQL = "INSERT INTO elections.\"PROJET\"( nom, description, note) VALUES (?, ?, ?)";
	public static final String PROJET_DELETE_SQL = "DELETE FROM elections.\"PROJET\" WHERE ID = ?";
	public static final String PROJET_UPDATE_SQL = "UPDATE elections.\"PROJET\" SET nom=?, description=?, note=? WHERE id=?;";
	public static final String PROJET_FINDALL_SQL = "SELECT id, nom, description, note FROM elections.\"PROJET\" ORDER BY NOM DESC";
	public static final String PROJET_FINDBYID_SQL = "SELECT id, nom, description, note FROM elections.\"PROJET\" WHERE ID = ?";
	public static final String PROJET_FINDBYNAME_SQL = "SELECT id, nom, description, note FROM elections.\"PROJET\" WHERE NOM = ?";

}
