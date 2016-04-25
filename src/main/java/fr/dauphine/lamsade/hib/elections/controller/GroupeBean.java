/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Group;
import fr.dauphine.lamsade.hib.elections.domain.Person;
import fr.dauphine.lamsade.hib.elections.domain.Project;
import fr.dauphine.lamsade.hib.elections.services.GroupService;
import fr.dauphine.lamsade.hib.elections.services.ProjectService;


/**
 * @author yosra.helal
 * 
 * @reviewer gnepa.rene.barou
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
	private GroupService serviceGroupe;
	
	@EJB
	private ProjectService serviceProjet;
	
	private Group group = new Group();

	private Group groupeBeforeEdit = null;
	private List<Group> groupesList;
	
	private Person person;
	
	private Project projet;
	
	private long projetId;
	
	private List<Project> projets;
	
	
	public GroupeBean() {

	}
	public String addGroup(Person person){
		List<Person> persons=new ArrayList<Person>();
		projets=new ArrayList<Project>();
		projet=new Project();
		
		this.person=person;
		try {
			projets=serviceProjet.findAll();
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		persons.add(person);
		group.setPersons(persons);
		group.setProject(projet);
		return "addGroup";
	}
	
	public void createGroupe() {

		try {
			projetId=1L;
			projet=serviceProjet.findById(projetId);
			group.setProject(projet);
			serviceGroupe.create(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteGroupe(Group group) {
		try {
			serviceGroupe.delete(group);
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	public String editGroupe(Group group) {
		this.group = group;
		System.out.println("editGroupe: " + this.group);
		return "groupeEdit";
	}

	/**
	 * @return the group
	 */
	public Group getGroupe() {
		return group;
	}

	/**
	 * @return the groupeBeforeEdit
	 */
	public Group getGroupeBeforeEdit() {
		return groupeBeforeEdit;
	}

	/**
	 * @return the groupesList
	 */
	public List<Group> getGroupesList() {
		return groupesList;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroupe(Group group) {
		this.group = group;
	}

	/**
	 * @param groupeBeforeEdit
	 *            the groupeBeforeEdit to set
	 */
	public void setGroupeBeforeEdit(Group groupeBeforeEdit) {
		this.groupeBeforeEdit = groupeBeforeEdit;
	}

	/**
	 * @param groupesList
	 *            the groupesList to set
	 */
	public void setGroupesList(List<Group> groupesList) {
		this.groupesList = groupesList;
	}

	@PostConstruct
	public void init() {
		groupesList = new ArrayList<Group>();
		try {
			setGroupesList(serviceGroupe.findAll());
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Project getProjet() {
		return projet;
	}
	public void setProjet(Project projet) {
		this.projet = projet;
	}
	public List<Project> getProjets() {
		return projets;
	}
	public void setProjets(List<Project> projets) {
		this.projets = projets;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Long getProjetId() {
		return projetId;
	}
	public void setProjetId(Long projetId) {
		this.projetId = projetId;
	}

}
