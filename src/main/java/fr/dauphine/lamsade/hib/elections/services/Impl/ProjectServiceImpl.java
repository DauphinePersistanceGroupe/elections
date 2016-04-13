/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;


import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Project;
import fr.dauphine.lamsade.hib.elections.services.ProjectService;

/**
 * @author omar.trabelsi
 *
 */
@Stateless
@Remote(ProjectService.class)
public class ProjectServiceImpl implements ProjectService {

	@PersistenceContext(unitName = "electionsPU")
	EntityManager em;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.ProjectService#findById(java.
	 * lang.Long)
	 */
	@Override
	public Project findById(Long id) throws MyExceptions {
		try {
			return em.find(Project.class, id);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.ProjectService#findByName(java
	 * .lang.String)
	 */
	@Override
	public List<Project> findByName(String name) throws MyExceptions {
		try {
			Query query = em
					.createQuery("SELECT p FROM Project p WHERE p.nom = :name");
			query.setParameter("name", name);
			return  (List<Project>) query.getSingleResult();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjectService#findAll()
	 */
	@Override
	public List<Project> findAll() throws MyExceptions {
		try {
			Query query = em
					.createQuery("SELECT p FROM Project p order by p.note desc ");
			return  (List<Project>) query.getResultList();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.ProjectService#create(fr.dauphine
	 * .lamsade.hib.elections.domain.Project)
	 */
	@Override
	public void create(Project project) throws MyExceptions {
		try {
			em.persist(project);;
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.ProjectService#delete(fr.dauphine
	 * .lamsade.hib.elections.domain.Project)
	 */
	@Override
	public void delete(Project project) throws MyExceptions {
		try {
			 em.remove(project);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.ProjectService#update(fr.dauphine
	 * .lamsade.hib.elections.domain.Project)
	 */
	@Override
	public void update(Project project) throws MyExceptions {
		try {
			 em.merge(project);
		} catch (IllegalArgumentException e) {
			throw new MyExceptions(e.getMessage(), e);
		}


	}


}
