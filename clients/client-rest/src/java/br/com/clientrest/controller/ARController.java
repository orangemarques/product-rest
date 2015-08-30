package br.com.clientrest.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UICommandButton;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("aRController")
@Scope("session")
public class ARController implements Serializable {

	private static final long serialVersionUID = 5942594447802361216L;
	
	private static final String LANGUAGE_ES_ES = "es_ES";
	private static final String LANGUAGE_EN_US = "en_US";
	
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	public String homeAction(){
		return "home-action";
	}

	
	public void changeLocale(ActionEvent event) {
		UICommandButton button = (UICommandButton) event.getComponent();
		String language = (String) button.getAttributes().get("language");
		if (language.equals(LANGUAGE_EN_US))
			locale = new Locale("en","US");
		else if (language.equals(LANGUAGE_ES_ES))
			locale = new Locale("es","ES");
		else
			locale = new Locale("pt","BR");
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		ViewHandler handler = context.getApplication().getViewHandler();
		UIViewRoot root = handler.createView(context, viewId);
		root.setViewId(viewId);
		context.setViewRoot(root);;
		
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	
	
}
