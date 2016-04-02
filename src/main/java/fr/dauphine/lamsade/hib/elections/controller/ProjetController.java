/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import fr.dauphine.lamsade.hib.elections.domain.Projet;
import fr.dauphine.lamsade.hib.elections.services.ProjetService;

/**
 * @author omar.trabelsi
 *
 */
public class ProjetController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	static ProjetService projetService;

	public static void createProjet(Projet p) {

		try {			
			projetService.create(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void deleteProjet(Projet p) {

		try {
			projetService.delete(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateProjet(Projet p) {

		try {
			projetService.update(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Projet getProjetById(Integer id){
		Projet projet = new Projet();
		try {
			projet = projetService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projet;
	}
	public static List<Projet> getAllProjets(){
		try {
			return projetService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static List<Projet> getProjetByName(String name){
		try {
			return projetService.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public static void main(String[] args) {
		Projet newProjet = new Projet();
		newProjet.setNom("TEST");
		newProjet.setDescription("TEST");
		newProjet.setNote(0);
		createProjet(newProjet);
		Projet projet = new Projet();
		projet = getProjetById(2);
		List<Projet> projetsList = getAllProjets();
		deleteProjet(newProjet);
		
	}
}
