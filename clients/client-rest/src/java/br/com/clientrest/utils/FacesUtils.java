package br.com.clientrest.utils;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FacesUtils {

	private FacesUtils(){  
	}  

	public static HttpSession getSession(){  
		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
	}  

	public static HttpServletRequest getRequest(){  
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
	}  

	public static HttpServletResponse getResponse(){  
		return(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
	}
	
	public static UIComponent getComponent(String componentId) {
		return FacesContext.getCurrentInstance().getViewRoot().findComponent(componentId);
	}
	
	public static String getUrlContext() {
        return "http://"+getRequest().getServerName()+getRequest().getContextPath();
	}

	/**
	 * Add warn message.
	 * 
	 * @param msg the information message
	 * @param msg is a key bundle
	 * @param arg of the bundle
	 */
	public static void addWarnMessage(String msg, boolean messageByKey, Object[] params) {
		addWarnMessage(null, msg, messageByKey, params);
	}
	
	/**
	 * Add warn message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the information message
	 * @param msg is a key bundle
	 * @param arg of the bundle
	 */
	public static void addWarnMessage(String clientId, String msg, boolean messageByKey, Object[] params) {
				
		if(messageByKey)
			msg = MessageFormat.format(FacesUtils.getMessageByKey(msg), params);
					
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
	}
	
	/**
	 * Add info message.
	 * 
	 * @param msg the information message
	 * @param msg is a key bundle
	 * @param arg of the bundle
	 */
	public static void addInfoMessage(String msg, boolean messageByKey, Object[] params) {
		addInfoMessage(null, msg, messageByKey, params);
	}
	
	/**
	 * Add info message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the information message
	 * @param msg is a key bundle
	 * @param arg of the bundle
	 */
	public static void addInfoMessage(String clientId, String msg, boolean messageByKey, Object[] params) {
				
		if(messageByKey)
			msg = MessageFormat.format(FacesUtils.getMessageByKey(msg), params);
					
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	/**
	 * Add error message.
	 * 
	 * @param msg the information message
	 * @param msg is a key bundle
	 * @param arg of the bundle
	 */
	public static void addErrorMessage(String msg, boolean messageByKey, Object[] params) {
		addErrorMessage(null, msg, messageByKey, params);
	}
	
	/**
	 * Add error message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the information message
	 * @param msg is a key bundle
	 * @param arg of the bundle
	 */
	public static void addErrorMessage(String clientId, String msg, boolean messageByKey, Object[] params) {
				
		if(messageByKey)
			msg = MessageFormat.format(FacesUtils.getMessageByKey(msg), params);
					
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
	
	/**
	 * Add message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the information message
	 */
	public static void addMessage(List<FacesMessage> message) {
		addMessage(null, message);
	}
	
	/**
	 * Add message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the information message
	 */
	public static void addMessage(String clientId, List<FacesMessage> message) {
		for (FacesMessage facesMessage : message) {
			FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
		}	
	}
	
	/**
	 * Add warn message.
	 * 
	 * @param msg the information message
	 */
	public static void addWarnMessage(String msg) {
		addWarnMessage(null, msg);
	}
	
	/**
	 * Add warn message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the information message
	 */
	public static void addWarnMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
	}
	
	/**
	 * Add information message.
	 * 
	 * @param msg the information message
	 */
	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}
	
	/**
	 * Add information message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the information message
	 */
	public static void addInfoMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	/**
	 * Add error message.
	 * 
	 * @param msg the error message
	 */
	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}
	
	/**
	 * Add error message to a sepcific client.
	 * 
	 * @param clientId the client id 
	 * @param msg the error message
	 */	
	public static void addErrorMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
	
	public String clearMessages(){
		
		Iterator<FacesMessage> it = FacesContext.getCurrentInstance().getMessages();
		while (it.hasNext()){
			FacesMessage faceMessage = (FacesMessage) it.next();
			faceMessage.setDetail("");
		}
		return "";
	}
		
	public static String getJsfEl(String value) {
		return "#{' + value + '}";
	}

    public static String getMessageByKey(String key) {
        try {
            String messageBundleName = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(messageBundleName, FacesContext.getCurrentInstance().getViewRoot().getLocale());
            return resourceBundle.getString(key);
        } catch (Exception e) {
            return key;
        }
    }
    
    public static String getMessageByKey(String key, Object[] params) {
        String messageBundleName = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(messageBundleName);
        
        try {
        	
        	MessageFormat mf = new MessageFormat(resourceBundle.getString(key));
        	
            return mf.format(params, new StringBuffer(), null).toString();
            
        } catch (Exception e) {
            return key;
        }

    }
    
    public static String getMessageByKey(String key, Locale locale) {
        try {
        	String messageBundleName = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(messageBundleName, locale);
            return resourceBundle.getString(key);
        } catch (Exception e) {
            return key;
        }
    }

}
