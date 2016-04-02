/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author omar.trabelsi
 *The persistent class for the "PROJET" database table.
 *
 *@reviewer gnepa.rene.barou
 */
@Entity
@Table(name="PROJET")
@NamedQuery(name="Projet.findAll", query="SELECT p FROM Projet p")
public class Projet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4089021386275603684L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2147483647)
	private String description;

	@Column(nullable=false, length=2147483647)
	private String nom;

	private Integer note;

	//bi-directional many-to-one association to Groupe
	@OneToMany(mappedBy="projet")
	private List<Groupe> groupes;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="projets")
	private List<User> users;

	public Projet() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public List<Groupe> getGroupes() {
		return this.groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Groupe addGroupe(Groupe groupe) {
		getGroupes().add(groupe);
		groupe.setProjet(this);

		return groupe;
	}

	public Groupe removeGroupe(Groupe groupe) {
		getGroupes().remove(groupe);
		groupe.setProjet(null);

		return groupe;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}