/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services;

import java.sql.Connection;

import fr.dauphine.lamsade.hib.elections.Exception.Exceptions;

/**
 * @author Rene.BAROU
 *
 */
public interface DbConnectionService {

	 Connection getConnection() throws Exceptions;
	 void closeConnection() throws Exceptions;
}
