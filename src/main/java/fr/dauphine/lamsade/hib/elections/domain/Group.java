/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;



/**
 * @author gnepa.rene.barou
 *
 * The persistent class for the "GROUP" database table.
 * 
 */
@Entity
@Table(name="GROUPE", uniqueConstraints = @UniqueConstraint(columnNames = "nom"))
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263919500065860345L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=255)
	private String description;

	@Column(length=255)
	private String nom;

	//uni-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="projet_id", nullable=false)
	private Project project;

	@Transient
	private List<Person> persons;
	
	public Group() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
    public boolean equals(Object other) {
		boolean result;
		if((other instanceof Group) && id != null){
			result= id.equals(((Group) other).id);
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