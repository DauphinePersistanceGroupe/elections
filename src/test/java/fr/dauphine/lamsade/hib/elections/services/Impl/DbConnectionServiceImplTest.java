/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.services.Impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.dauphine.lamsade.hib.elections.Exception.Exceptions;
import fr.dauphine.lamsade.hib.elections.services.DbConnectionService;

/**
 * @author Rene.BAROU
 *
 */
public class DbConnectionServiceImplTest {
	private DbConnectionService dbService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dbService=DbConnectionServiceImpl.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.DbConnectionServiceImpl#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(dbService);
	}

	/**
	 * Test method for {@link fr.dauphine.lamsade.hib.elections.services.Impl.DbConnectionServiceImpl#getConnection()}.
	 */
	@Test
	public void testGetConnection() {
//		assertNotNull(dbService);
		try {
			Connection conn=dbService.getConnection();
			assertNotNull(conn);
			dbService.closeConnection();
			assertTrue(conn.isClosed());
		} catch (Exceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
