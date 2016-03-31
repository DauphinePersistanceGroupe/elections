/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services;

import java.sql.Connection;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;

/**
 * @author Rene.BAROU
 *
 */
public interface DbConnectionService {

	 Connection getConnection() throws MyExceptions;
	 void closeConnection() throws MyExceptions;
}
