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



/**
 * @author gnepa.rene.barou
 *
 * The persistent class for the "GROUPE" database table.
 * 
 */
@Entity
@Table(name="GROUPE")
@NamedQuery(name="Groupe.findAll", query="SELECT g FROM Groupe g")
public class Groupe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263919500065860345L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	private String nom;

	//bi-directional many-to-one association to Projet
	@ManyToOne
	@JoinColumn(name="projet_id", nullable=false)
	private Projet projet;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private Person person;

	public Groupe() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Projet getProjet() {
		return this.projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Person getUser() {
		return this.person;
	}

	public void setUser(Person person) {
		this.person = person;
	}

}