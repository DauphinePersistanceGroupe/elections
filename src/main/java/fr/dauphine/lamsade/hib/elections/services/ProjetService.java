/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services;

import java.util.List;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Projet;

/**
 * @author omar.trabelsi
 *
 */
public interface ProjetService {
	Projet findById(Long id) throws MyExceptions;

	List<Projet> findByName(String name) throws MyExceptions;

	List<Projet> findAll() throws MyExceptions;

	void create(Projet projet) throws MyExceptions;

	void delete(Projet projet) throws MyExceptions;

	void update(Projet projet) throws MyExceptions;

}
