/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services;

import java.util.List;

import fr.dauphine.lamsade.hib.elections.Exception.Exceptions;
import fr.dauphine.lamsade.hib.elections.domain.User;


/**
 * @author gnepa.rene.barou
 *
 */
public interface UserService {
	
	User findById(Long id) throws Exceptions;

	User findByEmail(String email) throws Exceptions;

	List<User> findByName(String name) throws Exceptions;

	List<User> findAll() throws Exceptions;

	void create(User user) throws Exceptions;

	void delete(User user) throws Exceptions;

	void update(User user) throws Exceptions;

}
