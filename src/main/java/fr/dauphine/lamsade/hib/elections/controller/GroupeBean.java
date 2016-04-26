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
import fr.dauphine.lamsade.hib.elections.services.UserService;

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
	
	@EJB
	private UserService serviceUser;

	private Group group = new Group();

	private List<Group> groupesList;

	private List<Project> projets;

	private Person person;

	public GroupeBean() {

	}

	public String addGroup(Person person) {
		List<Person> persons = new ArrayList<Person>();
		Project projet = new Project();

		this.person = person;
		persons.add(person);
		group.setPersons(persons);
		group.setProject(projet);
		return "addGroup";
	}

	public void createGroupe() {

		try {
			serviceGroupe.create(group);
			groupesList = serviceGroupe.findAll();
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}
	public void saveGroupe(Long idGroup) {
		List<Person> persons ;
		try {
			person=serviceUser.findById(person.getId());
			
			group=serviceGroupe.findById(idGroup);
			persons=group.getPersons();
			if(null==persons){
				persons=new ArrayList<Person>();
			}
			persons.add(person);
			group.setPersons(persons);
			serviceGroupe.update(group);
			groupesList = serviceGroupe.findAll();
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
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
		return "groupeEdit";
	}

	/**
	 * @return the group
	 */
	public Group getGroupe() {
		return group;
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
	 * /**
	 * 
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
			groupesList = serviceGroupe.findAll();
			projets = serviceProjet.findAll();
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

}
