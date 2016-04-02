package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.User;
import fr.dauphine.lamsade.hib.elections.services.UserService;

/**
 * Servlet implementation class UserControler
 */
@ManagedBean
public class UserControler implements Serializable {

	@EJB
	private UserService serviceUser;
	
	private String user;
	/**
	 * 
	 */
	private static final long serialVersionUID = 673718825850668676L;
	
	public UserControler(){
		
	}
	public String getUser() {
		StringBuffer result=new StringBuffer();
		try {
//			User user=serviceUser.findById(1L);
			List<User> users=serviceUser.findAll();
			for (User user2 : users) {
				result.append(user2.toString());
				result.append("</br>");
			}
		} catch (MyExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.toString();
		
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

}
