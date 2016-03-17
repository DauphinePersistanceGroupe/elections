package fr.dauphine.lamsade.hib.elections.election;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

//test 
public class App {

	private static Logger log = Logger.getLogger(App.class.getCanonicalName());

	public static void main(String[] args) {

		Driver driver = null;

		try {

			driver = DriverManager.getDriver("jdbc:h2:~/test");

			log.info("driver pilote version number = " + " " + driver.getMajorVersion() + "."
					+ driver.getMinorVersion());

		} catch (SQLException e) {

			log.info("Connection Failed! Check output console");
			return;

		}
	}
}