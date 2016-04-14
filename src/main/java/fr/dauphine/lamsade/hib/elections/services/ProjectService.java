/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services;

import java.util.List;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Project;

/**
 * @author omar.trabelsi
 *
 */
public interface ProjectService {
	Project findById(Long id) throws MyExceptions;

	List<Project> findByName(String name) throws MyExceptions;

	List<Project> findAll() throws MyExceptions;

	void create(Project project) throws MyExceptions;

	void delete(Project project) throws MyExceptions;

	void update(Project project) throws MyExceptions;
	
	void vote(String name, String note, String email) throws MyExceptions;

}
