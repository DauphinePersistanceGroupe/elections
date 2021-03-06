/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author gnepa.rene.barou
 * 
 *         Entity implementation class for Entity: Person
 * 
 */
@Entity
@NamedQuery(name = "Person.findAll", query = "SELECT u FROM Person u")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 407127474693946058L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 50)
	private String email;

	private Boolean hasvoted;

	@NotNull
	@Size(min = 1, max = 25)
	private String nom;

	private String passwrd;

	private String prenom;

	private String role;
	
	
	private boolean isAdmin;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;


	public Person() {
	}


	public String getEmail() {
		return this.email;
	}


	public Boolean getHasvoted() {
		return this.hasvoted;
	}

	public Long getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPasswrd() {
		return this.passwrd;
	}

	public String getPrenom() {
		return this.prenom;
	}


	public String getRole() {
		return this.role;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setHasvoted(Boolean hasvoted) {
		this.hasvoted = hasvoted;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return this.isAdmin;
	}


	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public Group getGroup() {
		return group;
	}


	public void setGroup(Group group) {
		this.group = group;
	}


	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
    public boolean equals(Object other) {
		boolean result;
		if((other instanceof Person) && id != null){
			result= id.equals(((Person) other).id);
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