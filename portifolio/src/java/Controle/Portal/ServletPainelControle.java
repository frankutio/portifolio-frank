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
                
                if(usuario.getTipo_id() != 1){
                    request.getSession().setAttribute("tipoUser", null);
                }
                
                else{
                    request.getSession().setAttribute("tipoUser", "1");
                }

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
            String msgErroSenha = "";
            String msgErroTipo = "";
            String msgErroData = "";
            String msgErroNome = "";
            
            // Recuperando dados do formulario
            
            String tipo = request.getParameter("tipo"); 
            String nome = request.getParameter("name"); 
            String email = request.getParameter("email"); 
            String login = request.getParameter("login"); 
            String senha = request.getParameter("senha"); 
            String confSenha = request.getParameter("confSenha"); 
            String data = request.getParameter("dat_nascimento");      
            
            // carrega a lista de tipos
            
            List<Tipo> lstTipo = UsrDAO.getInstance().leTipo();
                     
            
                //RECUPERA PARAMENTRO DESCRICAO
                try{
                    
                    if(tipo.equals("") || tipo.equals("0")){
                        msgErroTipo = "<span class='erro'>Escolha o tipo de usuário</span>";
                    }
                    
                    if(nome.equals("")){
                        msgErroNome = "<span class='erro'>Preencha o nome</span>";
                    }
                    
                    if(email.equals("")){
                        msgErroEmail = "<span class='erro'>Preencha o campo de e-mail</span>";
                    }
                    
                    if(login.equals("")){
                        msgErroLogin = "<span class='erro'>Preencha o campo de login</span>";
                    }
                    
                    if(senha.equals("")){
                        msgErroSenha = "<span class='erro'>Preencha o campo de senha</span>";
                    }
                    
                    if(data.equals("")){
                        msgErroData = "";
                        formUsr.setData_nascimento(null);
                    }
                    
                    // testando as senhas
                    
                    String pwd = senha;
                    String pwd2 = confSenha;
                    
                    if(pwd !=""){
                        if(pwd.equals(pwd2)){
                            senha = pwd;
                        }
                        else{
                            msgErroSenha = "<span class='erro'>As senhas não conferem</span>";
                        }
                    }
                    
                    // testando email
                    
                    Usuario validaEmail = UsrDAO.getInstance().validaEmail(email);
                    
                    if(validaEmail != null){
                        msgErroEmail = "<span class='erro'>O E-mail informado já existe</span>";
                    }
                    
                    // Testando o login
                    
                    Usuario validaLogin = UsrDAO.getInstance().validaLogin(login);
                    
                    if(validaLogin != null){
                        msgErroLogin = "<span class='erro'>O login informado já existe</span>";
                    }
                    
                    // Gravando os dados no objeto para leitura em caso de erro

                    formUsr.setTipo_id(Integer.parseInt(tipo));
                    formUsr.setNome(nome);
                    formUsr.setEmail(email);
                    formUsr.setLogin(login);
                    formUsr.setSenha(senha);
                    
                    // Mensagem de erro e proxima pagina
                    String msgErro = formUsr.validaDados(formUsr.INCLUSAO);

                    // Monta Usuario com dados validos ou monta mensagens de erro

                    if (msgErro.equals("") && msgErroLogin.equals("") && msgErroEmail.equals("") && msgErroSenha.equals("") && msgErroTipo.equals("") && msgErroData.equals("")) {

                        Usuario usr = new Usuario();

                        usr.setTipo_id(formUsr.getTipo_id());
                        usr.setNome(formUsr.getNome());
                        usr.setEmail(formUsr.getEmail());
                        usr.setLogin(formUsr.getLogin());
                        usr.setSenha(formUsr.getSenha());
                        // Testa se a data de nascimento foi preenchida
                        if(data.equals("")){
                            usr.setData_nascimento(null);
                        }
                        else{
                            Date dtnascimento = dateFormat.parse(data);
                            usr.setData_nascimento(dtnascimento);
                        }                        
                        usr.setBloq("false");

                        //EFETUA A GRAVACAO DOS DADOS
                        UsrDAO.getInstance().grava(usr);

                        request.setAttribute("msg", "<div class='msg_success'>Usuario cadastrado com sucesso</div>");

                        proximaPagina = "/portifolio?nav=painel";

                    }else {
                        
                        request.setAttribute("title", "Adicionar");
                        request.setAttribute("operacao", "cad_usuario");

                        request.setAttribute("msgErro", msgErro);
                        request.setAttribute("msgErroEmail", msgErroEmail);
                        request.setAttribute("msgErroLogin", msgErroLogin);                    
                        request.setAttribute("msgErroSenha", msgErroSenha);                    
                        request.setAttribute("msgErroTipo", msgErroTipo);                    
                        request.setAttribute("msgErroData", msgErroData);
                        request.setAttribute("lstTipo", lstTipo);
                        request.setAttribute("usr", formUsr);  

                        proximaPagina = "/admin/painel/user/adicionar_usuario.jsp";
                    }
                    
                }catch(ParseException e){
                    
                    System.out.println(e);

                }               

        } 
        
        else if (operacao.equals("editCadUser")) {
            
            String redirect ="/portifolio?nav=painel";
            
            int id = Integer.parseInt(request.getParameter("codigo"));
            int idUser = Integer.parseInt(request.getParameter("user"));           
                        
            // Carregando dados do usuario
            
            Usuario usuario = UsrDAO.getInstance().carregaDados(id);
            
            if(usuario == null || usuario.getId_user() == idUser){
                request.setAttribute("msg", "<div class='msg_erro'>Usuário não encontrando</div>");
                redirect = "/portifolio?nav=cmsUser&action=adm_usuario&user=" + idUser;
            }
            
            else{   
                
                // Preparando pra gerar o tipo do usuario            
                List<Tipo> lstTipo = UsrDAO.getInstance().leTipo();
                
                request.setAttribute("usr", usuario);
                request.setAttribute("lstTipo", lstTipo);
                request.setAttribute("title", "Editar");
                request.setAttribute("operacao", "edit_usuario");
                
                redirect = "/admin/painel/user/adicionar_usuario.jsp";
            } 
            
            proximaPagina = redirect;
            
        }
        
        else if (operacao.equals("adm_usuario")) {
            
            int id = Integer.parseInt(request.getParameter("user"));
            
            List<Tipo> lstTipo = UsrDAO.getInstance().leTipo();
            
            List<Usuario> listUsr = UsrDAO.getInstance().leTodos(id);
            
            request.setAttribute("listUsr", listUsr);
            request.setAttribute("lstTipo", lstTipo);

            proximaPagina = action;
        } 
        
        else if (operacao.equals("add_user")) {
            
            int id = Integer.parseInt(request.getParameter("user"));
            
            List<Tipo> lstTipo = UsrDAO.getInstance().leTipo();
            
            request.setAttribute("title", "Adicionar");
            request.setAttribute("operacao", "cad_usuario");
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
