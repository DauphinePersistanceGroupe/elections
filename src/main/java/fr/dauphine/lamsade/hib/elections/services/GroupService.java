package fr.dauphine.lamsade.hib.elections.services;

/**
 * 
 */


import java.util.List;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Group;

/**
 * @author yosra.helal
 *
 */
public interface GroupService {
	Group findById(Long id) throws MyExceptions;

	List<Group> findByName(String name) throws MyExceptions;

	List<Group> findAll() throws MyExceptions;

	void create(Group group) throws MyExceptions;

	void delete(Group group) throws MyExceptions;

	void update(Group group) throws MyExceptions;

}
