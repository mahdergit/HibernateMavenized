/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rules;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
public class MessagesUtil {
    
    public static void displayError(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage errMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        context.addMessage(null, errMsg);
    }
}
