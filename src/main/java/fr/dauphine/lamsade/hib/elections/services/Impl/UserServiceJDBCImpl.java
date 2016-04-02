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

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Person;
import fr.dauphine.lamsade.hib.elections.services.UserService;
import fr.dauphine.lamsade.hib.elections.utils.SQLConstantes;

/**
 * @author Rene.BAROU
 *
 */
public class UserServiceJDBCImpl implements UserService {

	// private DbConnectionService dbService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#findById(java.
	 * lang.Long)
	 */
	public Person findById(Long id) throws MyExceptions {
		Connection conn = getConnection();
		Person person = new Person();
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_FINDBYID_SQL);
			pre.setLong(1, id);
			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				person.setId(rs.getLong("id"));
				person.setNom(rs.getString("nom"));
				person.setPrenom(rs.getString("prenom"));
				person.setEmail(rs.getString("email"));
				person.setPass(rs.getString("pass"));
				person.setRole(rs.getString("role"));
				person.setHasvoted(rs.getBoolean("hasvoted"));
			}
			// id, nom, prenom, email, pass, role,hasvoted
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
		return person;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#findByEmail(java
	 * .lang.String)
	 */
	public Person findByEmail(String email) throws MyExceptions {
		Connection conn = getConnection();
		Person person = null;
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_FINDBYEMAIL_SQL);
			pre.setString(1, email);
			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				person = new Person();
				person.setId(rs.getLong("id"));
				person.setNom(rs.getString("nom"));
				person.setPrenom(rs.getString("prenom"));
				person.setEmail(rs.getString("email"));
				person.setPass(rs.getString("pass"));
				person.setRole(rs.getString("role"));
				person.setHasvoted(rs.getBoolean("hasvoted"));
			}
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
		return person;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#findByName(java
	 * .lang.String)
	 */
	public List<Person> findByName(String name) throws MyExceptions {
		Connection conn = getConnection();
		List<Person> persons = new ArrayList<Person>();
		Person person;
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_FINDBYNAME_SQL);
			pre.setString(1, name);
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				person = new Person();
				person.setId(rs.getLong("id"));
				person.setNom(rs.getString("nom"));
				person.setPrenom(rs.getString("prenom"));
				person.setEmail(rs.getString("email"));
				person.setPass(rs.getString("pass"));
				person.setRole(rs.getString("role"));
				person.setHasvoted(rs.getBoolean("hasvoted"));
				persons.add(person);
			}
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
		return persons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.UserService#findAll()
	 */
	public List<Person> findAll() throws MyExceptions {
		Connection conn = getConnection();
		List<Person> persons = new ArrayList<Person>();
		Person person;
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(SQLConstantes.USER_FINDALL_SQL);

			while (rs.next()) {
				person = new Person();
				person.setId(rs.getLong("id"));
				person.setNom(rs.getString("nom"));
				person.setPrenom(rs.getString("prenom"));
				person.setEmail(rs.getString("email"));
				person.setPass(rs.getString("pass"));
				person.setRole(rs.getString("role"));
				person.setHasvoted(rs.getBoolean("hasvoted"));
				persons.add(person);
			}
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
		return persons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#create(fr.dauphine
	 * .lamsade.hib.elections.domain.User)
	 */
	public void create(Person person) throws MyExceptions {
		Connection conn = getConnection();
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_CREATE_SQL);

			pre.setString(1, person.getNom());
			pre.setString(2, person.getPrenom());
			pre.setString(3, person.getEmail());
			pre.setString(4, person.getPass());
			pre.setString(5, person.getRole());
			pre.setBoolean(6, person.getHasvoted());
			pre.executeUpdate();
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#delete(fr.dauphine
	 * .lamsade.hib.elections.domain.User)
	 */
	public void delete(Person person) throws MyExceptions {
		// nom, prenom, email, pass, role,hasvoted
		Connection conn = getConnection();
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_DELETE_SQL);

			pre.setLong(1, person.getId());
			pre.executeUpdate();
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#update(fr.dauphine
	 * .lamsade.hib.elections.domain.User)
	 */
	public void update(Person person) throws MyExceptions {
		Connection conn = getConnection();
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_UPDATE_SQL);

			pre.setString(1, person.getNom());
			pre.setString(2, person.getPrenom());
			pre.setString(3, person.getEmail());
			pre.setString(4, person.getPass());
			pre.setString(5, person.getRole());
			pre.setBoolean(6, person.getHasvoted());
			pre.setLong(7, person.getId());
			pre.executeUpdate();
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
	}

	/**
	 * @return the dbService
	 * @throws MyExceptions
	 */
	public Connection getConnection() throws MyExceptions {
		return DbConnectionServiceImpl.getInstance().getConnection();
	}

}
