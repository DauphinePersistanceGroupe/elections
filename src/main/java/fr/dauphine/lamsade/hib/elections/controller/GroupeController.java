/**
 * 
 */
package fr.dauphine.lamsade.hib.elections.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import fr.dauphine.lamsade.hib.elections.domain.Groupe;
import fr.dauphine.lamsade.hib.elections.services.GroupeService;

/**
 * @author yosra.helal
 *
 */

public class GroupeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	static GroupeService groupeService;

	public static void createGroupe(Groupe g) {

		try {			
			groupeService.create(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void deleteGroupe(Groupe g) {

		try {
			groupeService.delete(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateGroupe(Groupe g) {

		try {
			groupeService.update(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Groupe getGroupeById(Long id){
		Groupe groupe = new Groupe();
		try {
			groupe = groupeService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupe;
	}
	public static List<Groupe> getAllGroupes(){
		try {
			return groupeService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static List<Groupe> getGroupeByName(String name){
		try {
			return groupeService.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
