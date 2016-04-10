/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import fr.dauphine.lamsade.hib.elections.domain.Person;
import fr.dauphine.lamsade.hib.elections.services.UserService;

/**
 * @author gnepa.rene.barou
 *
 */
@Stateless
@Remote(UserService.class)
public class UserServiceImpl implements UserService {
	
	private static Logger log = Logger.getLogger(UserServiceImpl.class
			.getCanonicalName());
	@PersistenceContext
	EntityManager em;
	private CriteriaBuilder builder;

	@PostConstruct
	private void init() {
		builder = em.getCriteriaBuilder();
	}
	@Override
	public Person findById(Long id) throws MyExceptions {
		try {
			return em.find(Person.class, id);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	@Override
	public Person findByEmail(String email) throws MyExceptions {
		try {
			Query query = em
					.createQuery("SELECT u FROM Person u WHERE u.email =:email");
			query.setParameter("email", email);
			return (Person) query.getSingleResult();
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	@Override
	public List<Person> findByName(String name) throws MyExceptions {
		try {
			CriteriaQuery<Person> criteria = builder
					.createQuery(Person.class);
			Root<Person> person = criteria.from(Person.class);

			criteria.select(person).where(
					builder.equal(person.get("nom"), name));
			return em.createQuery(criteria).getResultList();
		} catch (IllegalArgumentException | PersistenceException e1) {
			throw new MyExceptions(e1.getMessage(), e1);
		}
	}

	@Override
	public List<Person> findAll() throws MyExceptions {
		try {
			CriteriaQuery<Person> criteria = builder
					.createQuery(Person.class);
			Root<Person> userRoot = criteria.from(Person.class);

			criteria.select(userRoot).orderBy(
					builder.asc(userRoot.get("nom")));
			return em.createQuery(criteria).getResultList();
		} catch (IllegalArgumentException | PersistenceException e1) {
			throw new MyExceptions(e1.getMessage(), e1);
		}
	}

	@Override
	public void create(Person person) throws MyExceptions {
		try {
			em.persist(person);
			return;
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}
	}

	@Override
	public void delete(Person person) throws MyExceptions {
		try {
			Person toDelete=em.getReference(Person.class, person.getId());
			em.remove(toDelete);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}

	@Override
	public void update(Person person) throws MyExceptions {
		try {
			em.merge(person);
		} catch (IllegalArgumentException | PersistenceException e) {
			throw new MyExceptions(e.getMessage(), e);
		}

	}
	@Override
	public int count(){
		int count=0;
		try {
			count=findAll().size();
		} catch (MyExceptions e) {
			count=0;
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		return count;
	}

}
