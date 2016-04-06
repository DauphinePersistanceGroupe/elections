package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Person;
import fr.dauphine.lamsade.hib.elections.services.UserService;
import fr.dauphine.lamsade.hib.elections.utils.Constantes;

/**
 * @author gnepa.rene.barou
 * 
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

	private static Logger log = Logger.getLogger(UserBean.class
			.getCanonicalName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 673718825850668676L;

	@EJB
	private UserService serviceUser;
	private Person person = new Person();

	private Person personBeforeEdit = null;
	private List<Person> personsList;

	public UserBean() {

	}

	public void deleteUser(Person person) {
		try {
			serviceUser.delete(person);
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	public String editUser(Person person) {
		this.person = person;
		System.out.println("editUser: " + this.person);
		return "userEdit";
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @return the personBeforeEdit
	 */
	public Person getPersonBeforeEdit() {
		return personBeforeEdit;
	}

	/**
	 * @return the personsList
	 */
	public List<Person> getPersonsList() {
		return personsList;
	}

	@PostConstruct
	public void init() {
		personsList = new ArrayList<Person>();
		try {
			setPersonsList(serviceUser.findAll());
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	public void inscrire() {
		try {
			person.setHasvoted(false);
			person.setRole(Constantes.USER_ELECT);
			serviceUser.create(person);
			FacesMessage message = new FacesMessage("Succès de l'inscription !");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @param personBeforeEdit
	 *            the personBeforeEdit to set
	 */
	public void setPersonBeforeEdit(Person personBeforeEdit) {
		this.personBeforeEdit = personBeforeEdit;
	}

	/**
	 * @param personsList
	 *            the personsList to set
	 */
	public void setPersonsList(List<Person> personsList) {
		this.personsList = personsList;
	}

	public void updateUser() {
		try {
			System.out.println("updateUser: " + person);
			Person personToEdit = serviceUser.findById(person.getId());
			System.out.println("personToEdit: " + personToEdit);
			personToEdit.setNom(person.getNom());
			personToEdit.setPrenom(person.getPrenom());
			serviceUser.update(personToEdit);
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	public void login() {
		boolean isAuthentificate = false;
		try {
			Person login = serviceUser.findByEmail(this.person.getEmail());
			if (null != login && login.getPasswrd().equals(person.getPasswrd()))
				isAuthentificate = true;
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}

	}

}
