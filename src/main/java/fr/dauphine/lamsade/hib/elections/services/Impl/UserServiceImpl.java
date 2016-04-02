/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.User;
import fr.dauphine.lamsade.hib.elections.services.UserService;

/**
 * @author gnepa.rene.barou
 *
 */
@Stateless
@Remote(UserService.class)
public class UserServiceImpl implements UserService {

	@PersistenceContext(unitName = "electionsPU")
	EntityManager em;
	private CriteriaBuilder builder;

	@PostConstruct
	private void init() {
		builder = em.getCriteriaBuilder();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#findById(java.
	 * lang.Long)
	 */
	@Override
	public User findById(Long id) throws MyExceptions {
		try {
			return em.find(User.class, id);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#findByEmail(java
	 * .lang.String)
	 */
	@Override
	public User findByEmail(String email) throws MyExceptions {
		try {
			Query query = em
					.createQuery("SELECT u FROM User u WHERE u.email =:email");
			query.setParameter("email", email);
			return (User) query.getSingleResult();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#findByName(java
	 * .lang.String)
	 */
	@Override
	public List<User> findByName(String name) throws MyExceptions {
		try {
			CriteriaQuery<User> criteria = builder
					.createQuery(User.class);
			Root<User> user = criteria.from(User.class);

			criteria.select(user).where(
					builder.equal(user.get("nom"), name));
			return em.createQuery(criteria).getResultList();
		} catch (IllegalArgumentException | PersistenceException e1) {
			throw new MyExceptions(e1.getMessage(), e1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.UserService#findAll()
	 */
	@Override
	public List<User> findAll() throws MyExceptions {
		try {
			CriteriaQuery<User> criteria = builder
					.createQuery(User.class);
			Root<User> userRoot = criteria.from(User.class);

			criteria.select(userRoot).orderBy(
					builder.asc(userRoot.get("nom")));
			return em.createQuery(criteria).getResultList();
		} catch (IllegalArgumentException | PersistenceException e1) {
			throw new MyExceptions(e1.getMessage(), e1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#create(fr.dauphine
	 * .lamsade.hib.elections.domain.User)
	 */
	@Override
	public void create(User user) throws MyExceptions {
		try {
			em.persist(user);
			return;
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#delete(fr.dauphine
	 * .lamsade.hib.elections.domain.User)
	 */
	@Override
	public void delete(User user) throws MyExceptions {
		try {
			em.remove(user);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#update(fr.dauphine
	 * .lamsade.hib.elections.domain.User)
	 */
	@Override
	public void update(User user) throws MyExceptions {
		try {
			em.merge(user);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}

}
