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
import fr.dauphine.lamsade.hib.elections.domain.Group;
import fr.dauphine.lamsade.hib.elections.domain.Person;
import fr.dauphine.lamsade.hib.elections.domain.Project;
import fr.dauphine.lamsade.hib.elections.services.GroupService;
import fr.dauphine.lamsade.hib.elections.services.ProjectService;
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
	
	@EJB
	ProjectService projectService;
	
	@EJB
	GroupService serviceGroup;
	
	private Person person = new Person();
	
	private Person newUser = new Person();

	private List<Person> personsList;
	
	private List<Project> projectList;
	
	private List<Group> groupList;

	public UserBean() {

	}

	public String deleteUser(Person person) {
		try {
			projectList=new ArrayList<Project>();
			serviceUser.delete(person);
			init();
//			setPersonsList(serviceUser.findAll());
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		return "userList";
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
			setProjectList(projectService.findAll());
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
	}

	public String inscrire() {
		try {
			if(serviceUser.count()==0){
				newUser.setRole(Constantes.USER_ADMIN);
			}else{
				newUser.setRole(Constantes.USER_ELECT);
			}
			newUser.setHasvoted(false);
			
			serviceUser.create(newUser);
			newUser=new Person();
			this.personsList=serviceUser.findAll();
			FacesMessage message = new FacesMessage("Succès de l'inscription !");
			FacesContext.getCurrentInstance().addMessage(null, message);
			init();
			return UrlConstantes.ACCUEIL;
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
			return UrlConstantes.INSCRIPTION;
		}
	}
	
	public String addGroup(Long idGroup, Long idPerson){
		try {
			Group group=serviceGroup.findById(idGroup);
			person=serviceUser.findById(idPerson);
			person.setGroup(group);
			serviceUser.update(person);
			init();
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		return  UrlConstantes.USER_ACCUEIL;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @param personsList
	 *            the personsList to set
	 */
	public void setPersonsList(List<Person> personsList) {
		this.personsList = personsList;
	}

	public String updateUser() {
		try {
			Person personToEdit = serviceUser.findById(person.getId());
			if (person.isAdmin()) {
				personToEdit.setRole(Constantes.USER_ADMIN);
				personToEdit.setAdmin(true);
			}else{
				personToEdit.setRole(Constantes.USER_ELECT);
				personToEdit.setAdmin(false);
			}
			personToEdit.setNom(person.getNom());
			personToEdit.setPrenom(person.getPrenom());
			serviceUser.update(personToEdit);
			init();
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		return "userList";
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
			this.person=login;
			HttpSession session = UtilSessionBean.getSession();
            session.setAttribute("username", login.getNom());
            session.setAttribute("usermail", login.getEmail());
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
	
	public String projectSorted(){
		List<Project> projects;
		try {
			projects = projectService.findAll();
			setProjectList(projects);
			
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		return "resultVote";
	}
	
	public String voted(Project project){
		
		try {
			Person user=serviceUser.findById(person.getId());
			if(!user.getHasvoted()){
				project=projectService.findById(project.getId());
				Integer vote=project.getNote();
				vote++;
				project.setNote(vote);
				user.setHasvoted(true);
				projectService.update(project);
				serviceUser.update(user);
				init();
				person=serviceUser.findById(user.getId());
			}
			
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		return "user";
	}
	
	public String linkedGroup(){
		groupList=new ArrayList<Group>();
		try {
			groupList=serviceGroup.findAll();
		} catch (MyExceptions e) {
			log.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		return "groupeList";
	}
	public String logout() {
        HttpSession session = UtilSessionBean.getSession();
        session.invalidate();
        return "deconnection";
    }

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public Person getNewUser() {
		return newUser;
	}

	public void setNewUser(Person newUser) {
		this.newUser = newUser;
	}

}
