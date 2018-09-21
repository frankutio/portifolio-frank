package Controle.Portal;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletLogoof extends HttpServlet {

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

        String proximaPagina = "/admin/painel/index.jsp";


        if (operacao.equals("logoff")) {
            
            request.getSession().removeAttribute("pass-login");
            request.getSession().removeAttribute("Usuario");
            request.getSession().removeAttribute("local");
            request.getSession().removeAttribute("tipoUser");
            request.getSession().removeAttribute("superUser");
            request.getSession().removeAttribute("Permissao");

            // Remove os dados de mensagem da sessao.
            request.getSession().removeAttribute("MsgErro");

            proximaPagina = "/portifolio?nav=login";

        }


        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);

    }
}
