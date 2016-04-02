/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Projet;
import fr.dauphine.lamsade.hib.elections.services.ProjetService;

/**
 * @author omar.trabelsi
 *
 */

public class ProjetServiceImpl implements ProjetService {

	@PersistenceContext(unitName = "electionsPU")
	EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.ProjetService#findById(java.
	 * lang.Long)
	 */
	
	public  Projet findById(Integer id) throws MyExceptions {
		try {
			return em.find(Projet.class, id);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.ProjetService#findByName(java
	 * .lang.String)
	 */
	@Override
	public List<Projet> findByName(String name) throws MyExceptions {
		try {
			Query query = em.createQuery("SELECT p FROM Projet p WHERE p.nom = :name");
			query.setParameter("name", name);
			return (List<Projet>) query.getSingleResult();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#findAll()
	 */
	@Override
	public List<Projet> findAll() throws MyExceptions {
		try {
			Query query = em.createQuery("SELECT p FROM Projet p ");
			return (List<Projet>) query.getSingleResult();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#create(fr.
	 * dauphine .lamsade.hib.elections.domain.Projet)
	 */
	@Override
	public void create(Projet projet) throws MyExceptions {
		try {
			em.persist(projet);
			;
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#delete(fr.
	 * dauphine .lamsade.hib.elections.domain.Projet)
	 */
	@Override
	public void delete(Projet projet) throws MyExceptions {
		try {
			em.remove(projet);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#update(fr.
	 * dauphine .lamsade.hib.elections.domain.Projet)
	 */
	@Override
	public void update(Projet projet) throws MyExceptions {
		try {
			em.merge(projet);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}
}
