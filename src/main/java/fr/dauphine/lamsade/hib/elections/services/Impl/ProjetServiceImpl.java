/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.dauphine.lamsade.hib.elections.Exception.Exceptions;
import fr.dauphine.lamsade.hib.elections.domain.Projet;
import fr.dauphine.lamsade.hib.elections.services.ProjetService;
import fr.dauphine.lamsade.hib.elections.utils.SQLConstantes;

/**
 * @author omar.trabelsi
 *
 */
public class ProjetServiceImpl implements ProjetService{
	
	
	@PersistenceContext
    private EntityManager em;

	public Projet findById(Long id) throws Exceptions{
		Projet projet = em.find(Projet.class, id);
		return projet;
		
	};

	public Projet findByName(String name) throws Exceptions{
		Projet projet = em.find(Projet.class, name);
		return projet;
	};

	List<Projet> findAll() throws Exceptions;

	void create(Projet projet) throws Exceptions;

	void delete(Projet projet) throws Exceptions;

	void update(Projet projet) throws Exceptions;


}
