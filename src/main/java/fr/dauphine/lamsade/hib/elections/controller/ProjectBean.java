/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import fr.dauphine.lamsade.hib.elections.domain.Project;
import fr.dauphine.lamsade.hib.elections.services.ProjectService;
import fr.dauphine.lamsade.hib.elections.services.UserService;

/**
 * @author omar.trabelsi
 *
 */
@RequestScoped
public class ProjectBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ProjectService projectService;
	@EJB
	UserService userService;
	private Project project;
	String projectName;

	public String addProject() {
		this.project = new Project();
		return "add_project";
	}

	public String createProject(Project p) {

		try {
			projectService.create(p);
			return "project";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteProject(Project p) {

		try {
			projectService.delete(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProject(Project p) {

		try {
			projectService.update(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Project getProjectById(Long id) {
		Project project = new Project();
		try {
			return projectService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	public List<Project> getAllProjects() {
		try {
			return projectService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Project> getProjectByName(String name) {
		try {
			return projectService.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String vote(String name, String email) {
		System.out.println("HEEEEEEEEEEEEERRRRRRRRRRRREEEEEE "+email);
		System.out.println("HEEEEEEEEEEEEERRRRRRRRRRRREEEEEE"+name);
		try {
			Integer note = projectService.findByName(name).get(0).getNote();
			if (note == null)
				note = 1;
			else
				note++;
			projectService.vote(name, String.valueOf(note), email);
			;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
}