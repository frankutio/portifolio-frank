package Controle.Portal;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletNavigation extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //RECEBE O TIPOD E OPERACAO A REALIZAR
        String nav = request.getParameter("nav");
        //LOG PARA TESTE
        System.out.println("Controle Acionado com Opção: " + nav);

        String proximaPagina = "";


        if (nav.equals("portifolio")) {
            
            request.getSession().setAttribute("local", "open");

            proximaPagina = "/index.jsp";

        }
        
        else if (nav.equals("contato")) {
            
            request.getSession().setAttribute("local", "open");

            proximaPagina = "/contato.jsp";

        }
        
        else if (nav.equals("login")) {
            
            request.getSession().setAttribute("local", "open");

            proximaPagina = "/admin/index.jsp";

        }
        
        else if (nav.equals("painel")) {
            
            request.getSession().setAttribute("local", "restrict");

            proximaPagina = "/admin/painel/index.jsp";

        }
        
        else if (nav.equals("editDadosPessoais")) {
            
            request.getSession().setAttribute("local", "restrict");
            
            String action = "/admin/painel/dados_pessoais.jsp";

            proximaPagina = "/usrPass?operacao=editDadosPessoais&action="+action;

        }
        
        else if (nav.equals("alterar-senha")) {
            
            request.getSession().setAttribute("local", "restrict");

            proximaPagina = "/admin/painel/senha.jsp";

        }

        else if (nav.equals("cmsPortifolio")) {

            String action = request.getParameter("action");

            if(action.equals("adicionar_portifolio")){
                proximaPagina = "/admin/painel/portifolio/adicionar_portifolio.jsp";
            }

            else if(action.equals("adm_portifolio")){
                proximaPagina = "/admin/painel/portifolio/adm_portifolio.jsp";
            }

            request.getSession().setAttribute("local", "restrict");

        }

        else if (nav.equals("cmsGaleria")) {

            String action = request.getParameter("action");

            if(action.equals("adicionar_foto")){
                proximaPagina = "/admin/painel/galeria/adicionar_foto.jsp";
            }

            else if(action.equals("adm_galeria")){
                proximaPagina = "/admin/painel/galeria/adm_galeria.jsp";
            }

            request.getSession().setAttribute("local", "restrict");

        }

        else if (nav.equals("cmsUser")) {

            String action = request.getParameter("action");
            String user = request.getParameter("user");

            if(action.equals("adicionar_usuario")){               
                
                String redirect = "/admin/painel/user/adicionar_usuario.jsp";                
                
                proximaPagina = "usrPass?operacao=add_user&action="+redirect+"&user="+user;
            }

            else if(action.equals("adm_usuario")){
                
                String redirect = "/admin/painel/user/adm_usuario.jsp";
                proximaPagina = "usrPass?operacao=adm_usuario&action="+redirect+"&user="+user;
            }

            request.getSession().setAttribute("local", "restrict");

        }


        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);

    }
}
