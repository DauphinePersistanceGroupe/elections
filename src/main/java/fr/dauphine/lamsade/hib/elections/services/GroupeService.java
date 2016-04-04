package fr.dauphine.lamsade.hib.elections.services;

import java.util.List;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Person;


/**
 * @author yosra.helal
 *
 */

public interface GroupeService {

	Groupe findById(Long id) throws MyExceptions;

	Groupe findByName(String name) throws MyExceptions;

	List<Groupe> findAll() throws MyExceptions;

	void create(Groupe groupe) throws MyExceptions;

	void delete(Groupe groupe) throws MyExceptions;

	void update(Groupe groupe) throws MyExceptions;
}
