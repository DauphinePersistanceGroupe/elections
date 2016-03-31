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
import fr.dauphine.lamsade.hib.elections.domain.Projet;
import fr.dauphine.lamsade.hib.elections.services.ProjetService;
import fr.dauphine.lamsade.hib.elections.utils.SQLConstantes;

/**
 * @author omar.trabelsi
 *
 */
public class ProjetServiceImpl implements ProjetService{
	
	public Projet findById(Long id) throws MyExceptions {
		Connection conn=getConnection();
		Projet projet=new Projet();
		try {
			PreparedStatement pre=conn.prepareStatement(SQLConstantes.PROJET_FINDBYID_SQL);
			pre.setLong(1, id);
			ResultSet rs=pre.executeQuery();
			
					if (rs.next()) {
						projet.setId(rs.getLong("id"));
						projet.setNom(rs.getString("nom"));
						projet.setDescription(rs.getString("description"));
						projet.setNote(Integer.parseInt(rs.getString("note")));
					}
//			id, nom, description, note
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(),e.getCause());
		}
		return projet;
	}

	
	/* (non-Javadoc)
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#findByName(java.lang.String)
	 */
	public List<Projet> findByName(String name) throws MyExceptions {
		Connection conn=getConnection();
		List<Projet> projets=new ArrayList<Projet>();
		Projet projet;
		try {
			PreparedStatement pre=conn.prepareStatement(SQLConstantes.PROJET_FINDBYNAME_SQL);
			pre.setString(1, name);
			ResultSet rs=pre.executeQuery();
			
					while (rs.next()) {
						projet=new Projet();
						projet.setId(rs.getLong("id"));
						projet.setNom(rs.getString("nom"));
						projet.setDescription(rs.getString("description"));
						projet.setNote(Integer.parseInt(rs.getString("note")));
						projets.add(projet);
					}
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(),e.getCause());
		}
		return projets;
	}

	/* (non-Javadoc)
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#findAll()
	 */
	public List<Projet> findAll() throws MyExceptions {
		Connection conn=getConnection();
		List<Projet> projets=new ArrayList<Projet>();
		Projet projet;
		try {
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(SQLConstantes.PROJET_FINDALL_SQL);
			
					while (rs.next()) {
						projet=new Projet();
						projet.setId(rs.getLong("id"));
						projet.setNom(rs.getString("nom"));
						projet.setDescription(rs.getString("description"));
						projet.setNote(Integer.parseInt(rs.getString("note")));
						projets.add(projet);
					}
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(),e.getCause());
		}
		return projets;
	}

	/* (non-Javadoc)
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#create(fr.dauphine.lamsade.hib.elections.domain.Projet)
	 */
	public void create(Projet projet) throws MyExceptions {
		Connection conn=getConnection();
		try {
			PreparedStatement pre=conn.prepareStatement(SQLConstantes.PROJET_CREATE_SQL);

			pre.setString(1, projet.getNom());
			pre.setString(2, projet.getDescription());
			pre.setString(3, String.valueOf(projet.getNote()));
			pre.executeUpdate();
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(),e.getCause());
		}

	}

	/* (non-Javadoc)
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#delete(fr.dauphine.lamsade.hib.elections.domain.Projet)
	 */
	public void delete(Projet projet) throws MyExceptions {
//		nom, description, note
		Connection conn=getConnection();
		try {
			PreparedStatement pre=conn.prepareStatement(SQLConstantes.PROJET_DELETE_SQL);

			pre.setLong(1, projet.getId());
			pre.executeUpdate();
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(),e.getCause());
		}

	}

	/* (non-Javadoc)
	 * @see fr.dauphine.lamsade.hib.elections.services.ProjetService#update(fr.dauphine.lamsade.hib.elections.domain.Projet)
	 */
	public void update(Projet projet) throws MyExceptions {
		Connection conn=getConnection();
		try {
			PreparedStatement pre=conn.prepareStatement(SQLConstantes.PROJET_UPDATE_SQL);

			pre.setString(1, projet.getNom());
			pre.setString(2, projet.getDescription());
			pre.setString(3, String.valueOf(projet.getNote()));
			pre.setLong(7, projet.getId());
			pre.executeUpdate();
		} catch (SQLException e) {
			throw new MyExceptions(e.getMessage(),e.getCause());
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
