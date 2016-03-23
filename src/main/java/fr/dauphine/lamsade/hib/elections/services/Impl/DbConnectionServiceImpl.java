/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.dauphine.lamsade.hib.elections.Exception.Exceptions;
import fr.dauphine.lamsade.hib.elections.services.DbConnectionService;

/**
 * @author Rene.BAROU
 * 
 */
public class DbConnectionServiceImpl implements DbConnectionService {

	private static Logger log = Logger.getLogger(DbConnectionServiceImpl.class
			.getCanonicalName());
	private static volatile DbConnectionServiceImpl instance = null;

	private Connection conn;

	private final String DB_URL = "jdbc:postgresql://localhost/elections";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "postgres";

	private DbConnectionServiceImpl() {
		Connection con;

		try {

			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

		} catch (SQLException e) {
			con = null;
			log.log(Level.SEVERE, e.getMessage(), e.getCause());

		}
		this.conn = con;
	}

	public final static DbConnectionServiceImpl getInstance() {
		if (DbConnectionServiceImpl.instance == null) {
			synchronized (DbConnectionServiceImpl.class) {
				if (DbConnectionServiceImpl.instance == null) {
					DbConnectionServiceImpl.instance = new DbConnectionServiceImpl();
				}
			}
		}
		return DbConnectionServiceImpl.instance;
	}

	public Connection getConnection() throws Exceptions {
		if (null == this.conn) {
			throw new Exceptions("Connection database Failed! " + DB_URL);
		}
		return this.conn;
	}

	public void closeConnection() {
		if (null != this.conn) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage(), e.getCause());
			}
		}

	}

}
