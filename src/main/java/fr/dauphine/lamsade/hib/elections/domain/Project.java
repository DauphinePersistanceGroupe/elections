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
import javax.persistence.UniqueConstraint;


/**
 * @author omar.trabelsi
 *The persistent class for the "PROJECT" database table.
 *
 *@reviewer gnepa.rene.barou
 */
@Entity
@Table(name="PROJECT", uniqueConstraints = @UniqueConstraint(columnNames = "nom"))
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

	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
    public boolean equals(Object other) {
		boolean result;
		if((other instanceof Project) && id != null){
			result= id.equals(((Project) other).id);
		}else{
			result= other == this;
		}
		return result;
    }

    @Override
    public int hashCode() {
        return (id != null) 
            ? (getClass().hashCode() + id.hashCode())
            : super.hashCode();
    }

}