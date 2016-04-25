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
import fr.dauphine.lamsade.hib.elections.domain.Project;
import fr.dauphine.lamsade.hib.elections.services.ProjectService;

@ManagedBean
@ApplicationScoped
public class ProjectEntityConverter implements Converter {

	@EJB
	private ProjectService projectService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			Project p = projectService.findById(Long.valueOf(submittedValue));
			return p;
		} catch (NumberFormatException | MyExceptions e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s is not a valid Project ID", submittedValue)), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof Project) {
			return String.valueOf(((Project) modelValue).getId());
		} else {
			throw new ConverterException(new FacesMessage(String.format(
					"%s is not a valid Project", modelValue)));
		}
	}

}
