/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services;

import java.util.List;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.User;


/**
 * @author gnepa.rene.barou
 *
 */
public interface UserService {
	
	User findById(Long id) throws MyExceptions;

	User findByEmail(String email) throws MyExceptions;

	List<User> findByName(String name) throws MyExceptions;

	List<User> findAll() throws MyExceptions;

	void create(User user) throws MyExceptions;

	void delete(User user) throws MyExceptions;

	void update(User user) throws MyExceptions;

}
