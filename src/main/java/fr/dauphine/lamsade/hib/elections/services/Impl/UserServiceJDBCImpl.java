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
import fr.dauphine.lamsade.hib.elections.domain.User;
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
	public User findById(Long id) throws MyExceptions {
		Connection conn = getConnection();
		User user = new User();
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_FINDBYID_SQL);
			pre.setLong(1, id);
			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setRole(rs.getString("role"));
				user.setHasvoted(rs.getBoolean("hasvoted"));
			}
			// id, nom, prenom, email, pass, role,hasvoted
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#findByEmail(java
	 * .lang.String)
	 */
	public User findByEmail(String email) throws MyExceptions {
		Connection conn = getConnection();
		User user = null;
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_FINDBYEMAIL_SQL);
			pre.setString(1, email);
			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setRole(rs.getString("role"));
				user.setHasvoted(rs.getBoolean("hasvoted"));
			}
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#findByName(java
	 * .lang.String)
	 */
	public List<User> findByName(String name) throws MyExceptions {
		Connection conn = getConnection();
		List<User> users = new ArrayList<User>();
		User user;
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_FINDBYNAME_SQL);
			pre.setString(1, name);
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setRole(rs.getString("role"));
				user.setHasvoted(rs.getBoolean("hasvoted"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.dauphine.lamsade.hib.elections.services.UserService#findAll()
	 */
	public List<User> findAll() throws MyExceptions {
		Connection conn = getConnection();
		List<User> users = new ArrayList<User>();
		User user;
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(SQLConstantes.USER_FINDALL_SQL);

			while (rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				user.setRole(rs.getString("role"));
				user.setHasvoted(rs.getBoolean("hasvoted"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(), e.getCause());
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.dauphine.lamsade.hib.elections.services.UserService#create(fr.dauphine
	 * .lamsade.hib.elections.domain.User)
	 */
	public void create(User user) throws MyExceptions {
		Connection conn = getConnection();
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_CREATE_SQL);

			pre.setString(1, user.getNom());
			pre.setString(2, user.getPrenom());
			pre.setString(3, user.getEmail());
			pre.setString(4, user.getPass());
			pre.setString(5, user.getRole());
			pre.setBoolean(6, user.getHasvoted());
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
	public void delete(User user) throws MyExceptions {
		// nom, prenom, email, pass, role,hasvoted
		Connection conn = getConnection();
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_DELETE_SQL);

			pre.setLong(1, user.getId());
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
	public void update(User user) throws MyExceptions {
		Connection conn = getConnection();
		try {
			PreparedStatement pre = conn
					.prepareStatement(SQLConstantes.USER_UPDATE_SQL);

			pre.setString(1, user.getNom());
			pre.setString(2, user.getPrenom());
			pre.setString(3, user.getEmail());
			pre.setString(4, user.getPass());
			pre.setString(5, user.getRole());
			pre.setBoolean(6, user.getHasvoted());
			pre.setLong(7, user.getId());
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
