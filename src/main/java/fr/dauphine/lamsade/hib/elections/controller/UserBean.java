package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class UserBean implements Serializable {

	private static Logger log = Logger.getLogger(UserBean.class
			.getCanonicalName());

	@EJB
	private UserService serviceUser;

	private Person person;
	/**
	 * 
	 */
	private static final long serialVersionUID = 673718825850668676L;

	public UserBean() {
		person = new Person();
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
	public String getPerson() {
		StringBuffer result = new StringBuffer();
		try {
			Person person = serviceUser.findByEmail("barou2000@gmail.com");
			result.append(person.toString());
			List<Person> persons = serviceUser.findAll();
			for (Person person2 : persons) {
				result.append(person2.toString());
				result.append("</br>");
			}
		} catch (MyExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.toString();

	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

}
