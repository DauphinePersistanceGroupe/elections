package fr.dauphine.lamsade.hib.elections.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author gnepa.rene.barou
 *
 */
public class UtilSessionBean {

	public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
 
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
 
    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
 
    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }
    
    public static String getUserAdmin() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("useradmin");
        else
            return null;
    }
    
    public static String getContextPath(){
    	return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
//    	request.contextPath;
    }
}
