/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Person;
import fr.dauphine.lamsade.hib.elections.services.UserService;

/**
 * @author gnepa.rene.barou
 *
 */
public class UserServiceJDBCImplTest {
private UserService userService;
private String email="UserTest@gmail.com";
private String name="UserTest";


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		userService=new UserServiceJDBCImpl();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.UserServiceJDBCImpl#create(fr.dauphine.lamsade.hib.elections.domain.Person)}.
	 * @throws MyExceptions 
	 */
	@Test
	public void testCreate() throws MyExceptions {
//		Person userT= new Person();
//		userT.setNom("UserTest");
//		userT.setPrenom("rene");
//		userT.setEmail(email);
//		userT.setPass("UserTest");
//		userT.setRole("participant");
//		userT.setHasVoted(false);
//		userService.create(userT);
	}

	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.UserServiceJDBCImpl#findById(java.lang.Long)}.
	 * @throws MyExceptions 
	 */
	@Test
	public void testFindById() throws MyExceptions {
		Person person=userService.findById(1L);
		assertNotNull(person);
	}

	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.UserServiceJDBCImpl#findByEmail(java.lang.String)}.
	 * @throws MyExceptions 
	 */
	@Test
	public void testFindByEmail() throws MyExceptions {
		Person person=userService.findByEmail(email);
		assertNotNull(person);
	}

	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.UserServiceJDBCImpl#findByName(java.lang.String)}.
	 * @throws MyExceptions 
	 */
	@Test
	public void testFindByName() throws MyExceptions {
		List<Person> persons=userService.findByName(name);
		assertNotNull(persons);
		assertTrue(persons.size()>0);
	}

	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.UserServiceJDBCImpl#findAll()}.
	 * @throws MyExceptions 
	 */
	@Test
	public void testFindAll() throws MyExceptions {
		List<Person> persons=userService.findAll();
		assertNotNull(persons);
		assertTrue(persons.size()>1);
	}


	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.UserServiceJDBCImpl#update(fr.dauphine.lamsade.hib.elections.domain.Person)}.
	 * @throws MyExceptions 
	 */
	@Test
	public void testUpdate() throws MyExceptions {
		Person person=userService.findByEmail(email);
		person.setPrenom("Update");
		userService.update(person);
		person=userService.findByEmail(email);
		assertTrue(person.getPrenom().equals("Update"));
	}
	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.UserServiceJDBCImpl#delete(fr.dauphine.lamsade.hib.elections.domain.Person)}.
	 * @throws MyExceptions 
	 */
	@Test
	public void testDelete() throws MyExceptions {
		Person userT= new Person();
		userT.setNom("UserTest");
		userT.setPrenom("rene");
		userT.setEmail("UserJuniTest@gmail.com");
		userT.setPasswrd("UserTest");
		userT.setRole("participant");
		userT.setHasvoted(false);
		userService.create(userT);
		
		Person person=userService.findByEmail("UserJuniTest@gmail.com");
		userService.delete(person);
		person=userService.findByEmail("UserJuniTest@gmail.com");
		assertNull(person);
	}

}
