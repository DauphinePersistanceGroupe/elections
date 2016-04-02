/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.services.DbConnectionService;
import fr.dauphine.lamsade.hib.elections.utils.SQLConstantes;

/**
 * @author Rene.BAROU
 * 
 */
//TODO be removed
@Deprecated
public class DbConnectionServiceImpl implements DbConnectionService {

	private static Logger log = Logger.getLogger(DbConnectionServiceImpl.class
			.getCanonicalName());
	private static volatile DbConnectionServiceImpl instance = null;

	private Connection conn;

	

	private DbConnectionServiceImpl() {
		Connection con;

		try {

			con = DriverManager.getConnection(SQLConstantes.DB_URL, SQLConstantes.DB_USER, SQLConstantes.DB_PASS);

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

	public Connection getConnection() throws MyExceptions {
		if (null == this.conn) {
			throw new MyExceptions("Connection database Failed! " + SQLConstantes.DB_URL);
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
