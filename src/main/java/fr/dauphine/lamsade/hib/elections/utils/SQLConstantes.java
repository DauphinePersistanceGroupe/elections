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
	public static final String USER_CREATE_SQL = "INSERT INTO elections.\"USER\"( nom, prenom, email, pass, role,hasvoted) VALUES (?, ?, ?, ?, ?,?)";
	public static final String USER_DELETE_SQL = "DELETE FROM elections.\"USER\" WHERE ID = ?";
	public static final String USER_UPDATE_SQL = "UPDATE elections.\"USER\" SET nom=?, prenom=?, email=?, pass=?, role=?, hasvoted=? WHERE id=?;";
	public static final String USER_FINDALL_SQL = "SELECT id, nom, prenom, email, pass, role,hasvoted FROM elections.\"USER\" ORDER BY NOM DESC";
	public static final String USER_FINDBYID_SQL = "SELECT id, nom, prenom, email, pass, role,hasvoted FROM elections.\"USER\" WHERE ID = ?";
	public static final String USER_FINDBYEMAIL_SQL = "SELECT id, nom, prenom, email, pass, role,hasvoted FROM elections.\"USER\" WHERE EMAIL = ?";
	public static final String USER_FINDBYNAME_SQL = "SELECT id, nom, prenom, email, pass, role,hasvoted FROM elections.\"USER\" WHERE NOM = ?";
}
