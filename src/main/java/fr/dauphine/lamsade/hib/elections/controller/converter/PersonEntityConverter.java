package fr.dauphine.lamsade.hib.elections.controller.converter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import fr.dauphine.lamsade.hib.elections.Exception.MyExceptions;
import fr.dauphine.lamsade.hib.elections.domain.Person;
import fr.dauphine.lamsade.hib.elections.services.UserService;

@ManagedBean
@ApplicationScoped
public class PersonEntityConverter implements Converter {

	@EJB
	private UserService personService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			Person p = personService.findById(Long.valueOf(submittedValue));
			return p;
		} catch (NumberFormatException | MyExceptions e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s is not a valid Person ID", submittedValue)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof Person) {
			return String.valueOf(((Person) modelValue).getId());
		} else {
			throw new ConverterException(new FacesMessage(String.format(
					"%s is not a valid Person", modelValue)));
		}
	}

}
