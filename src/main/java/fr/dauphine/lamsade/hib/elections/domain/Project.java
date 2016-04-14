/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author omar.trabelsi
 *The persistent class for the "PROJECT" database table.
 *
 *@reviewer gnepa.rene.barou
 */
@Entity
@Table(name="PROJECT")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4089021386275603684L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	private String description;

	private String nom;
	
	@Column(name="note", nullable = true, columnDefinition ="int default 0")
	private Integer note;
	
	@OneToMany(mappedBy="project", cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	private List<Group> groups;

	public Project() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getNote() {
		return this.note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}