package fr.dauphine.lamsade.hib.elections.services.Impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Groupe;
import fr.dauphine.lamsade.hib.elections.services.GroupeService;




/**
 * @author yosra.helal
 *
 *@reviewer gnepa.rene.barou
 */
@Stateless
@Remote(GroupeService.class)
public class GroupeServiceImpl implements GroupeService{

	@PersistenceContext(unitName = "electionsPU")
	EntityManager em;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupeService#findById(java.
	 * lang.Long)
	 */
	
	@Override
	public Groupe findById(Long id) throws MyExceptions {
		try {
			return em.find(Groupe.class, id);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupeService#findByName(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Groupe> findByName(String name) throws MyExceptions {
		try {
			Query query = em
					.createQuery("SELECT p FROM Groupe p WHERE p.nom = :name");
			query.setParameter("name", name);
			return  query.getResultList();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.GroupeService#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Groupe> findAll() throws MyExceptions {
		try {
			Query query = em
					.createQuery("SELECT p FROM Groupe p ");
			return  (List<Groupe>) query.getSingleResult();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupeService#create(fr.dauphine
	 * .lamsade.hib.elections.domain.Groupe)
	 */
	@Override
	public void create(Groupe groupe) throws MyExceptions {
		try {
			em.persist(groupe);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupeService#delete(fr.dauphine
	 * .lamsade.hib.elections.domain.Groupe)
	 */
	@Override
	public void delete(Groupe groupe) throws MyExceptions {
		try {
			 em.remove(groupe);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupeService#update(fr.dauphine
	 * .lamsade.hib.elections.domain.Groupe)
	 */
	@Override
	public void update(Groupe groupe) throws MyExceptions {
		try {
			 em.merge(groupe);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

}
