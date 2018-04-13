
package controleurs.secondaires;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VersInscriptionCtrl implements Serializable, SousControleurInterface{

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        
        return "/WEB-INF/inscription-form.jsp";
    }
    
}
