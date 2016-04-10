package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Person;
import fr.dauphine.lamsade.hib.elections.services.UserService;
import fr.dauphine.lamsade.hib.elections.utils.Constantes;
import fr.dauphine.lamsade.hib.elections.utils.UrlConstantes;
import fr.dauphine.lamsade.hib.elections.utils.UtilSessionBean;

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
		return UrlConstantes.USER_EDIT;
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

	public String inscrire() {
		try {
			if(serviceUser.count()==0){
				person.setRole(Constantes.USER_ADMIN);
			}else{
				person.setRole(Constantes.USER_ELECT);
			}
			person.setHasvoted(false);
			
			serviceUser.create(person);
			FacesMessage message = new FacesMessage("Succès de l'inscription !");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "login";
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
			return "inscription";
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
			Person personToEdit = serviceUser.findById(person.getId());
			if (person.isAdmin()) {
				personToEdit.setRole(Constantes.USER_ADMIN);
				personToEdit.setAdmin(true);
			}
			personToEdit.setNom(person.getNom());
			personToEdit.setPrenom(person.getPrenom());
			serviceUser.update(personToEdit);
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	public String login() {
		boolean isAuthentificate = false;
		Person login = new Person();
		try {
			login = serviceUser.findByEmail(this.person.getEmail());
			if (null != login && login.getPasswrd().equals(person.getPasswrd())) {
				isAuthentificate = true;
			}

		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		if (isAuthentificate) {
			HttpSession session = UtilSessionBean.getSession();
            session.setAttribute("username", login.getNom());
            if(login.isAdmin()){
            	session.setAttribute("useradmin", login.getRole());
            	return  UrlConstantes.ADMIN_ACCUEIL;
            }else{
            	return  UrlConstantes.USER_ACCUEIL;
            }
            
			
		} else {
			FacesMessage message = new FacesMessage("User et/ou mot de passe incorrect !");
			FacesContext.getCurrentInstance().addMessage("loginForm:passwrd", message);
			return UrlConstantes.LOGIN;
		}

	}
	
	public String logout() {
        HttpSession session = UtilSessionBean.getSession();
        session.invalidate();
        return "deconnection";
    }

}
