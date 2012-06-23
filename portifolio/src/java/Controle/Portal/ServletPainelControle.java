package Controle.Portal;

import Entidade.Portal.Usuario;
import Persistencia.LoginDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletPainelControle extends HttpServlet {

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
        String operacao = request.getParameter("operacao");
        //LOG PARA TESTE
        System.out.println("Controle Acionado com Operacao: " + operacao);

        String proximaPagina = "";
        
        if(operacao.equals("login")){
        
            Usuario usr = new Usuario();
            
            String login = request.getParameter("login");
            String senha = request.getParameter("pwd");
            
            String direciona = request.getParameter("dispara");
            
            Usuario usuario = LoginDAO.getInstance().login(login, senha);
            
            if(usuario == null){

               request.getSession().removeAttribute("usr");
               request.setAttribute("MsgErro", "Login Inválido!");
               proximaPagina = direciona;
           }
            
            else{
                
                request.getSession().setAttribute("pass-login", "logado");
                request.getSession().setAttribute("Usuario", usuario);
                
                proximaPagina = "/usrPass?operacao=homePainel";
            }
            
            
        
        }
        
        else if(operacao.equals("homePainel")){
            proximaPagina = "/portifolio?nav=painel";
        }
        
        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);
        
        
    }
}
