/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services;

import java.util.List;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Person;


/**
 * @author gnepa.rene.barou
 *
 */
public interface UserService {
	
	Person findById(Long id) throws MyExceptions;

	Person findByEmail(String email) throws MyExceptions;

	List<Person> findByName(String name) throws MyExceptions;

	List<Person> findAll() throws MyExceptions;

	void create(Person person) throws MyExceptions;

	void delete(Person person) throws MyExceptions;

	void update(Person person) throws MyExceptions;
	
	int count();

}
