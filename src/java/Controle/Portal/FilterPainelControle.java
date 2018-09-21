
package Controle.Portal;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Frank
 */
public class FilterPainelControle implements Filter {
    
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            if (httpRequest.getSession().getAttribute("pass-login") == null || httpRequest.getSession().getAttribute("Usuario") == null) {
                httpRequest.setAttribute("MsgErro", "Login Necessario.");
                httpRequest.getRequestDispatcher("/portifolio?nav=login").forward(request, response);
            }
            else {
                chain.doFilter(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void destroy(){
    }
}
