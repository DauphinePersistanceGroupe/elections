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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.dauphine.lamsade.hib.elections.utils.Constantes;

/**
 * @author gnepa.rene.barou
 * 
 *         Entity implementation class for Entity: Person
 * 
 */
@Entity
@Table(name = "PERSON")
@NamedQuery(name = "Person.findAll", query = "SELECT u FROM Person u")
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
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String nom;

	private String passwrd;

	private String prenom;

	private String role;
	
	@Transient
	private boolean isAdmin;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	// bi-directional many-to-one association to Groupe
	@OneToMany(mappedBy = "person")
	private List<Groupe> groupes;

	// bi-directional many-to-many association to Projet
	@ManyToMany
	@JoinTable(name = "GROUPE", joinColumns = { @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "projet_id", nullable = false) })
	private List<Projet> projets;

	public Person() {
	}

	public Groupe addGroupe(Groupe groupe) {
		getGroupes().add(groupe);
		groupe.setUser(this);

		return groupe;
	}

	public String getEmail() {
		return this.email;
	}

	public List<Groupe> getGroupes() {
		return this.groupes;
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

	public List<Projet> getProjets() {
		return this.projets;
	}

	public String getRole() {
		return this.role;
	}

	public Groupe removeGroupe(Groupe groupe) {
		getGroupes().remove(groupe);
		groupe.setUser(null);

		return groupe;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
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

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public void setRole(String role) {
		this.role = role;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return Constantes.USER_ADMIN.equalsIgnoreCase(this.role);
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
//	public void setAdmin(boolean isAdmin) {
//		this.isAdmin = isAdmin;
//	}
}