package Controle.Portal;

import Entidade.Portal.Tipo;
import Entidade.Portal.Usuario;
import Persistencia.LoginDAO;
import Persistencia.UsrDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.text.*;
import java.util.Date;
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

        String action = request.getParameter("action");

        //LOG PARA TESTE
        System.out.println("Controle Acionado com Operacao: " + operacao);

        String proximaPagina = "";

        if (operacao.equals("login")) {

            Usuario usr = new Usuario();

            String login = request.getParameter("login");
            String senha = request.getParameter("pwd");

            String direciona = request.getParameter("dispara");

            Usuario usuario = LoginDAO.getInstance().login(login, senha);

            if (usuario == null) {

                request.getSession().removeAttribute("usr");
                request.setAttribute("MsgErro", "Login Inválido!");
                proximaPagina = direciona;
            } else {

                request.getSession().setAttribute("pass-login", "logado");
                request.getSession().setAttribute("Usuario", usuario);

                proximaPagina = "/usrPass?operacao=homePainel";
            }



        } 
        
        else if (operacao.equals("cad_usuario")) {

            //COVNERTE DATA
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // ISNTANCIA A CLASSE - Obtem dados do formulario
            Usuario formUsr = new Usuario();

            String msgErroEmail = "";
            String msgErroLogin = "";

            // Verifica se o email informado ja existe
            Usuario email = UsrDAO.getInstance().validaEmail(request.getParameter("email"));

            if (email != null) {
                msgErroEmail = "<span style='color:red;'>O E-mail informado ja está em uso.</span>";
            }

            // Verifica se o CPF informado ja existe
            Usuario login = UsrDAO.getInstance().validaLogin(request.getParameter("login"));

            if (login != null) {
                msgErroLogin = "<span style='color:red;'>O login informado já existe.</span>";
            }
            
            // Verifica se as senhas conferem
            String senha = "";
            String pwd = request.getParameter("senha");
            String pwd2 = request.getParameter("confSenha");
            
            if(pwd !=""){
                if(pwd.equals(pwd2)){
                    senha = pwd;
                }
                else{
                    request.setAttribute("erroSenha", "<span style='color:red;'>As senhas não conferem</span>");
                }
            }
            
            if(request.getParameter("tipo").equals("0")){
                
                request.setAttribute("erroTipo", "<span style='color:red;'>Defina o tipo de usuario</span>");
                
                proximaPagina = "/admin/painel/user/adicionar_usuario.jsp";
            }
            
            else if(request.getParameter("dat_nascimento").equals("")){
                
                request.setAttribute("erroData", "<span style='color:red;'>Preencha o campo data de nascimento</span>");
                
                proximaPagina = "/admin/painel/user/adicionar_usuario.jsp";
            }
            
            else{
                //RECUPERA PARAMENTRO DESCRICAO
                try{

                    formUsr.setTipo_id(Integer.parseInt(request.getParameter("tipo")));
                    formUsr.setNome(request.getParameter("name"));
                    formUsr.setEmail(request.getParameter("email"));
                    formUsr.setLogin(request.getParameter("login"));
                    formUsr.setSenha(senha);
                    Date dtnascimento = dateFormat.parse(request.getParameter("dat_nascimento"));
                    formUsr.setData_nascimento(dtnascimento);

                    // Mensagem de erro e proxima pagina
                    String msgErro = formUsr.validaDados(formUsr.INCLUSAO);

                    // Monta Usuario com dados validos ou monta mensagens de erro

                    if (msgErro.equals("") && msgErroLogin.equals("") && msgErroEmail.equals("")) {

                        Usuario usr = new Usuario();

                        usr.setTipo_id(formUsr.getTipo_id());
                        usr.setNome(formUsr.getNome());
                        usr.setEmail(formUsr.getEmail());
                        usr.setLogin(formUsr.getLogin());
                        usr.setSenha(formUsr.getSenha());
                        usr.setData_nascimento(formUsr.getData_nascimento());
                        usr.setBloq("false");

                        //EFETUA A GRAVACAO DOS DADOS
                        UsrDAO.getInstance().grava(usr);

                        request.setAttribute("msg", "<div class='msg_success'>Usuario cadastrado com sucesso</div>");

                        proximaPagina = "/portifolio?nav=painel";

                    }else {

                        request.setAttribute("msgErro", msgErro);
                        request.setAttribute("msgErroEmail", msgErroEmail);
                        request.setAttribute("msgErroLogin", msgErroLogin);                    
                        request.setAttribute("usr", formUsr);                    

                        proximaPagina = "/admin/painel/user/adicionar_usuario.jsp";
                    }
                }catch(ParseException e){

                }
                
            }
            
            

        } 
        
        else if (operacao.equals("editCadUser")) {
            

            proximaPagina = "/";
        }
        
        else if (operacao.equals("adm_usuario")) {
            
            int id = Integer.parseInt(request.getParameter("user"));
            
            List<Tipo> lstTipo = UsrDAO.getInstance().leTipo();
            
            List<Usuario> listUsr = UsrDAO.getInstance().leTodos(id);
            
            request.setAttribute("listUsr", listUsr);
            request.setAttribute("lstTipo", lstTipo);

            proximaPagina = action;
        } 
        
        else if (operacao.equals("editDadosPessoais")) {


            List<Tipo> lstTipo = UsrDAO.getInstance().leTipo();

            //CRIA UM ATRIBUTO PARA MANDAR PARA A JSP
            request.setAttribute("lstTipo", lstTipo);

            proximaPagina = action;
        } 
        
        else if (operacao.equals("homePainel")) {
            proximaPagina = "/portifolio?nav=painel";
        }

        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);


    }
}
