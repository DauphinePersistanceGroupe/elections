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
		} catch (IllegalArgumentException e) {
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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.UserService#findAll()
	 */
	@Override
	public List<User> findAll() throws MyExceptions {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
