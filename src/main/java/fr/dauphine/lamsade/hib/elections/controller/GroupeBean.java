/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import fr.dauphine.lamsade.hib.elections.domain.Groupe;
import fr.dauphine.lamsade.hib.elections.services.GroupeService;


/**
 * @author yosra.helal
 *
 */
@ManagedBean
@SessionScoped
public class GroupeBean implements Serializable {

	private static Logger log = Logger.getLogger(GroupeBean.class
			.getCanonicalName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 673718825850668676L;

	@EJB
	private GroupeService serviceGroupe;
	private Groupe groupe = new Groupe();

	private Groupe groupeBeforeEdit = null;
	private List<Groupe> groupesList;
	
	
	public GroupeBean() {

	}
	
	public void createGroupe(Groupe g) {

		try {
			groupeService.create(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteGroupe(Groupe groupe) {
		try {
			serviceGroupe.delete(groupe);
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	public String editGroupe(Groupe groupe) {
		this.groupe = groupe;
		System.out.println("editGroupe: " + this.groupe);
		return "groupeEdit";
	}

	/**
	 * @return the groupe
	 */
	public Groupe getGroupe() {
		return groupe;
	}

	/**
	 * @return the groupeBeforeEdit
	 */
	public Groupe getGroupeBeforeEdit() {
		return groupeBeforeEdit;
	}

	/**
	 * @return the groupesList
	 */
	public List<Groupe> getGroupesList() {
		return groupesList;
	}

	/**
	 * @param groupe
	 *            the groupe to set
	 */
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	/**
	 * @param groupeBeforeEdit
	 *            the groupeBeforeEdit to set
	 */
	public void setGroupeBeforeEdit(Groupe groupeBeforeEdit) {
		this.groupeBeforeEdit = groupeBeforeEdit;
	}

	/**
	 * @param groupesList
	 *            the groupesList to set
	 */
	public void setGroupesList(List<Groupe> groupesList) {
		this.groupesList = groupesList;
	}

	@PostConstruct
	public void init() {
		groupesList = new ArrayList<Groupe>();
		try {
			setGroupesList(serviceGroupe.findAll());
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

}
