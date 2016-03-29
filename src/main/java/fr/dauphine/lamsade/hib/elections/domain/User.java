/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author gnepa.rene.barou
 * 
 * Entity implementation class for Entity: User
 * 
 */
@Entity
@Table(name="USER")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 407127474693946058L;

	@Id
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=2147483647)
	private String email;

	private Boolean hasvoted;

	@Column(nullable=false, length=2147483647)
	private String nom;

	@Column(nullable=false, length=2147483647)
	private String pass;

	@Column(nullable=false, length=2147483647)
	private String prenom;

	@Column(nullable=false, length=2147483647)
	private String role;

	//bi-directional many-to-one association to Groupe
	@OneToMany(mappedBy="user")
	private List<Groupe> groupes;

	//bi-directional many-to-many association to Projet
	@ManyToMany
	@JoinTable(
		name="\"GROUPE\""
		, joinColumns={
			@JoinColumn(name="user_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="projet_id", nullable=false)
			}
		)
	private List<Projet> projets;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getHasvoted() {
		return this.hasvoted;
	}

	public void setHasvoted(Boolean hasvoted) {
		this.hasvoted = hasvoted;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Groupe> getGroupes() {
		return this.groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Groupe addGroupe(Groupe groupe) {
		getGroupes().add(groupe);
		groupe.setUser(this);

		return groupe;
	}

	public Groupe removeGroupe(Groupe groupe) {
		getGroupes().remove(groupe);
		groupe.setUser(null);

		return groupe;
	}

	public List<Projet> getProjets() {
		return this.projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

}