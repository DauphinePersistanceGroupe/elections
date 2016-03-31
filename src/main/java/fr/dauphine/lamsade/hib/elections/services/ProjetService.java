/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services;

import java.util.List;

import fr.dauphine.lamsade.hib.elections.Exception.Exceptions;
import fr.dauphine.lamsade.hib.elections.domain.Projet;

/**
 * @author omar.trabelsi
 *
 */
public interface ProjetService {
	Projet findById(Long id) throws Exceptions;

	Projet findByName(String name) throws Exceptions;

	List<Projet> findAll() throws Exceptions;

	void create(Projet projet) throws Exceptions;

	void delete(Projet projet) throws Exceptions;

	void update(Projet projet) throws Exceptions;

}
