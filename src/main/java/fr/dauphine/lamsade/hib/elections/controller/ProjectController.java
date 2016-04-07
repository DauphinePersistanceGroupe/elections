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

/**
 * @author omar.trabelsi
 *
 */
@RequestScoped
public class ProjectController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ProjectService projectService;
	private Project project;

	public String addProject() {
	    this.project = new Project();
	    return "add_project";
	  }

	public void createProject(Project p) {

		try {
			projectService.create(p);
		} catch (Exception e) {
			e.printStackTrace();
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}