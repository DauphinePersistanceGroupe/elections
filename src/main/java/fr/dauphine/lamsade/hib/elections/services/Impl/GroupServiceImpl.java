package fr.dauphine.lamsade.hib.elections.services.Impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Group;
import fr.dauphine.lamsade.hib.elections.services.GroupService;




/**
 * @author yosra.helal
 *
 *@reviewer gnepa.rene.barou
 */
@Stateless
@Remote(GroupService.class)
public class GroupServiceImpl implements GroupService{

	@PersistenceContext(unitName = "electionsPU")
	EntityManager em;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupService#findById(java.
	 * lang.Long)
	 */
	
	@Override
	public Group findById(Long id) throws MyExceptions {
		try {
			return em.find(Group.class, id);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupService#findByName(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> findByName(String name) throws MyExceptions {
		try {
			Query query = em
					.createQuery("SELECT p FROM Group p WHERE p.nom = :name");
			query.setParameter("name", name);
			return  query.getResultList();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.GroupService#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> findAll() throws MyExceptions {
		try {
			Query query = em
					.createQuery("SELECT p FROM Group p ");
			return  query.getResultList();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupService#create(fr.dauphine
	 * .lamsade.hib.elections.domain.Groupe)
	 */
	@Override
	public void create(Group group) throws MyExceptions {
		try {
			em.persist(group);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupService#delete(fr.dauphine
	 * .lamsade.hib.elections.domain.Groupe)
	 */
	@Override
	public void delete(Group group) throws MyExceptions {
		try {
			Group groupToDelete=em.getReference(Group.class, group.getId());
			 em.remove(groupToDelete);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.GroupService#update(fr.dauphine
	 * .lamsade.hib.elections.domain.Groupe)
	 */
	@Override
	public void update(Group group) throws MyExceptions {
		try {
			 em.merge(group);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

}
