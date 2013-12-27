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
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;


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
                
                if(usuario.getTipo_id() != 1 && usuario.getSuperUsr().equals("false")){
                    request.getSession().setAttribute("tipoUser", null);
                    request.getSession().setAttribute("superUser", null);                    
                }
                
                else{
                    request.getSession().setAttribute("tipoUser", "1");
                    request.getSession().setAttribute("Permissao", "1");
                }  
                
                if(usuario.getTipo_id() == 2){
                    request.getSession().setAttribute("Permissao", "2");
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
            String msgErroSuper = "";
            
            // Recuperando dados do formulario
            
            String tipo = request.getParameter("tipo"); 
            String nome = request.getParameter("name"); 
            String email = request.getParameter("email"); 
            String login = request.getParameter("login"); 
            String senha = request.getParameter("senha"); 
            String confSenha = request.getParameter("confSenha"); 
            String data = request.getParameter("dat_nascimento"); 
            String superUsr = request.getParameter("super");
            
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
                    
                    if(superUsr.equals("")){
                        msgErroSuper = "<span class='erro'>Informe se é um super usuário</span>";
                    }
                    
                    if(data.equals("")){
                        msgErroData = "";
                        formUsr.setData_nascimento(null);
                    }
                    
                    // prepara o super usuario para gravar a sua permissão no banco
            
                    if(superUsr.equals("sim")){
                        superUsr = "true";
                    }
                    
                    if(superUsr.equals("nao")){
                        superUsr = "false";
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
                    formUsr.setSuperUsr(superUsr);
                    
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
                        usr.setSuperUsr(formUsr.getSuperUsr());
                        // Testa se a data de nascimento foi preenchida
                        if(data.equals("")){
                            usr.setData_nascimento(null);
                        }
                        else{
                            Date dtnascimento = dateFormat.parse(data);
                            usr.setData_nascimento(dtnascimento);
                        }                        
                        usr.setBloq("false");
                        usr.setFoto("/img/perfil/profile_m_default.jpg");

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
        
        
        else if (operacao.equals("alterar_dados_pessoais")) {

            //COVNERTE DATA
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // ISNTANCIA A CLASSE - Obtem dados do formulario
            Usuario formUsr = new Usuario();

            String msgErroEmail = "";
            String msgErroLogin = "";
            String msgErroTipo = "";
            String msgErroData = "";
            String msgErroNome = "";
            
            // Recuperando dados do formulario           
            
            int idUser = Integer.parseInt(request.getParameter("usr")); 
            String nome = request.getParameter("name"); 
            String email = request.getParameter("email"); 
            String data = request.getParameter("dat_nascimento");  
            String about = request.getParameter("about");
            
                                
            
                //RECUPERA PARAMENTRO DESCRICAO
                try{
                    
                    if(nome.equals("")){
                        msgErroNome = "<span class='erro'>Preencha o nome</span>";
                    }
                    
                    if(email.equals("")){
                        msgErroEmail = "<span class='erro'>Preencha o campo de e-mail</span>";
                    }
                    
                    if(data.equals("")){
                        msgErroData = "<span class='erro'>Preencha o campo de Data</span>";
                    }
                    
                    // testando email
                    
                    Usuario validaEmail = UsrDAO.getInstance().validaEmail(email);
                    
                    Usuario current = UsrDAO.getInstance().leDados(idUser);
                    
                    
                    if(validaEmail != null){
                        
                        if(validaEmail.getEmail().equals(current.getEmail())){
                            msgErroEmail = "";
                        }
                        else{
                            msgErroEmail = "<span class='erro'>O E-mail informado já existe</span>";
                        }
                    }
                    
                    // Gravando os dados no objeto para leitura em caso de erro
                    
                    formUsr.setNome(nome);
                    formUsr.setEmail(email);
                    formUsr.setLogin(current.getLogin());
                    formUsr.setAbout(about);

                    // Monta Usuario com dados validos ou monta mensagens de erro

                    if (msgErroEmail.equals("") && msgErroTipo.equals("") && msgErroData.equals("") && msgErroNome.equals("")) {

                        Usuario usr = new Usuario();
                        
                        usr.setNome(formUsr.getNome());
                        usr.setEmail(formUsr.getEmail());
                        usr.setLogin(formUsr.getLogin());
                        usr.setAbout(formUsr.getAbout());                        
                        Date dtnascimento = dateFormat.parse(data);
                        usr.setData_nascimento(dtnascimento);
                        
                        //EFETUA A GRAVACAO DOS DADOS
                        UsrDAO.getInstance().alteraDadosPessoais(usr, idUser);
                        
                        Usuario usuario = UsrDAO.getInstance().carregaDados(idUser);

                        request.setAttribute("msg", "<div class='msg_success'>Dados alterados com sucesso</div>");
                        request.getSession().setAttribute("Usuario", usuario);

                        proximaPagina = "/usrPass?operacao=homePainel";

                    }else {
                        
                        request.setAttribute("msgErroEmail", msgErroEmail);
                        request.setAttribute("msgErroLogin", msgErroLogin);                   
                        request.setAttribute("msgErroTipo", msgErroTipo);                    
                        request.setAttribute("msgErroData", msgErroData);

                        proximaPagina = "/portifolio?nav=editDadosPessoais";
                    }
                    
                }catch(ParseException e){
                    
                    System.out.println(e);

                }               

        } 
        
        else if (operacao.equals("edit_usuario")){

            //COVNERTE DATA
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // ISNTANCIA A CLASSE - Obtem dados do formulario
            Usuario formUsr = new Usuario();

            String msgErroEmail = "";
            String msgErroSenha = "";
            String msgErroTipo = "";
            String msgErroData = "";
            String msgErroNome = "";
            String msgErroSuper = "";
            
            // Recuperando dados do formulario           
            
            int idUser = Integer.parseInt(request.getParameter("usr")); 
            String tipo = request.getParameter("tipo");
            String nome = request.getParameter("name"); 
            String email = request.getParameter("email"); 
            String senha = request.getParameter("senha"); 
            String confSenha = request.getParameter("confSenha"); 
            String data = request.getParameter("dat_nascimento");  
            String superUsr = request.getParameter("super");           
            
                                
            
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
                    
                    if(senha.equals("")){
                        msgErroSenha = "<span class='erro'>Preencha o campo de senha</span>";
                    }
                    
                    if(superUsr.equals("")){
                        msgErroSuper = "<span class='erro'>Informe se é um super usuário</span>";
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
                    
                    // prepara o super usuario para gravar a sua permissão no banco
            
                    if(superUsr.equals("sim")){
                        superUsr = "true";
                    }
                    
                    if(superUsr.equals("nao")){
                        superUsr = "false";
                    }
                                      
                                       
                    // Gravando os dados no objeto para leitura em caso de erro
                    
                    formUsr.setId_user(idUser);
                    formUsr.setTipo_id(Integer.parseInt(tipo));
                    formUsr.setNome(nome);
                    formUsr.setEmail(email);
                    formUsr.setSenha(senha);
                    formUsr.setSuperUsr(superUsr);
                    
                    // Mensagem de erro e proxima pagina
                    String msgErro = formUsr.validaDados(formUsr.ALTERACAO);

                    // Monta Usuario com dados validos ou monta mensagens de erro

                    if (msgErro.equals("") && msgErroEmail.equals("") && msgErroSenha.equals("") && msgErroTipo.equals("") && msgErroData.equals("")) {

                        Usuario usr = new Usuario();

                        usr.setId_user(formUsr.getId_user());
                        usr.setTipo_id(formUsr.getTipo_id());
                        usr.setNome(formUsr.getNome());
                        usr.setEmail(formUsr.getEmail());
                        usr.setSenha(formUsr.getSenha());
                        usr.setSuperUsr(formUsr.getSuperUsr());
                        // Testa se a data de nascimento foi preenchida
                        if(data.equals("")){
                            usr.setData_nascimento(null);
                        }
                        else{
                            Date dtnascimento = dateFormat.parse(data);
                            usr.setData_nascimento(dtnascimento);
                        }

                        //EFETUA A GRAVACAO DOS DADOS
                        UsrDAO.getInstance().alteraCadastro(usr, usr.getId_user());

                        request.setAttribute("msg", "<div class='msg_success'>Usuario alterado com sucesso</div>");

                        proximaPagina = "/usrPass?operacao=homePainel";

                    }else {
                        
                        request.setAttribute("title", "Editar");
                        request.setAttribute("operacao", "edit_usuario");

                        request.setAttribute("msgErro", msgErro);
                        request.setAttribute("msgErroEmail", msgErroEmail);                  
                        request.setAttribute("msgErroSenha", msgErroSenha);                    
                        request.setAttribute("msgErroTipo", msgErroTipo);                    
                        request.setAttribute("msgErroData", msgErroData);
                        request.setAttribute("usr", formUsr);  

                        proximaPagina = "/admin/painel/user/adicionar_usuario.jsp";
                    }
                    
                }catch(ParseException e){
                    
                    System.out.println(e);

                }               

        } 
        
        
        else if (operacao.equals("editCadUser")) {
            
            String redirect ="/usrPass?operacao=homePainel";
            
            int id = Integer.parseInt(request.getParameter("codigo"));
            int idUser = Integer.parseInt(request.getParameter("user"));           
                        
            // Carregando dados do usuario
            
            Usuario usuario = UsrDAO.getInstance().carregaDados(id);
            
            // Verifica se o usuario tem permissao para editar dados
            
            Usuario usr = UsrDAO.getInstance().carregaDados(idUser);
            
            if(usuario == null || usuario.getId_user() == idUser || usr.getSuperUsr() == null && usr.getTipo_id() != 1 || usr.getTipo_id() == 2 && usuario.getTipo_id() == 1){
                
                request.setAttribute("msg", "<div class='msg_erro'>Usuário não encontrado ou você não possui permissão para acessar essa funcionlidade.</div>");
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
        
        else if(operacao.equals("alterar_senha")){
            
            int id = Integer.parseInt(request.getParameter("usr"));
            String senha = request.getParameter("senha");
            String novaSenha = request.getParameter("novaSenha");
            String confSenha = request.getParameter("confSenha");
            
            String msgErroSenha = "";
            String msgErroNovaSenha = "";
            
            // Prepara para recuperar os dados do usuario para validação de senha
            
            Usuario usr = UsrDAO.getInstance().carregaDados(id);
            
            // Testa se as senhas foram digitadas corretamente
            
            if(!senha.equals(usr.getSenha())){
                msgErroSenha = "<div class='msg_erro'>A senha infomada não confere</div>";
            } 
            
            if(!novaSenha.equals(confSenha)){
                msgErroNovaSenha = "<div class='msg_erro'>As senhas digitadas não conferem</div>";
            }
            
            if (msgErroSenha.equals("") && msgErroNovaSenha.equals("")) {
                
                UsrDAO.getInstance().alteraSenha(id, novaSenha);                
                
                request.setAttribute("msg", "<div class='msg_success'>Senha alterada com sucesso</div>");
                
                proximaPagina = "/usrPass?operacao=homePainel";
                
            }
            
            else{
                request.setAttribute("msgErroSenha", msgErroSenha);
                request.setAttribute("msgErroNovaSenha", msgErroNovaSenha); 

                proximaPagina = "/admin/painel/senha.jsp";
            }
        
        }
        
        else if (operacao.equals("bloqCadUser")) {
                
            String redirect ="";
            
            int id = Integer.parseInt(request.getParameter("codigo"));
            int idUser = Integer.parseInt(request.getParameter("user"));
            
            // Carrega os dados do usuário logado no sistema            
             Usuario usuarioLogado = (Usuario) httpRequest.getSession().getAttribute("Usuario");
             
            // Carregando dados do usuario
            
            Usuario usuario = UsrDAO.getInstance().carregaDados(id);
            
            // Verifica se o usuario tem permissao para realizar essa ação
            
            Usuario usr = UsrDAO.getInstance().carregaDados(idUser);
            
            if(usuario == null || usuario.getId_user() == idUser || usr.getSuperUsr() == null && usr.getTipo_id() != 1 || usr.getTipo_id() == 2 && usuario.getTipo_id() == 1 || usuarioLogado.getTipo_id() != 1 && usuarioLogado.getSuperUsr().equals("false")){
                request.setAttribute("msg", "<div class='msg_erro'>Ação não permitida</div>");
                redirect = "/portifolio?nav=cmsUser&action=adm_usuario&user=" + idUser;
            }
            
            else{
                
                // Bloqueia o usuário solicitado
                
                int user = UsrDAO.getInstance().bloqUsr(id);
                
                if(user != 0){
                    request.setAttribute("msg", "<div class='msg_success'>Usuário bloqueado com sucesso</div>");
                }
                
                else{
                    request.setAttribute("msg", "<div class='msg_erro'>O bloqueio falhou</div>");
                }
                
                redirect = "/portifolio?nav=cmsUser&action=adm_usuario&user=" + idUser;
            
            }
            

            proximaPagina = redirect;
        } 
        
        else if (operacao.equals("restalCadUser")) {
            
            String redirect ="";
            
            int id = Integer.parseInt(request.getParameter("codigo"));
            int idUser = Integer.parseInt(request.getParameter("user"));
            
            // Carrega os dados do usuário logado no sistema            
             Usuario usuarioLogado = (Usuario) httpRequest.getSession().getAttribute("Usuario");
                
            // Carregando dados do usuario
            
            Usuario usuario = UsrDAO.getInstance().leDados(id);
            
            // Verifica se o usuario tem permissao para realizar essa ação
            
            Usuario usr = UsrDAO.getInstance().carregaDados(idUser);
            
            if(usuario == null || usuario.getId_user() == idUser || usr.getSuperUsr() == null && usr.getTipo_id() != 1 || usr.getTipo_id() == 2 && usuario.getTipo_id() == 1 || usuarioLogado.getTipo_id() != 1 && usuarioLogado.getSuperUsr().equals("false")){
                request.setAttribute("msg", "<div class='msg_erro'>Ação não permitida</div>");
                redirect = "/portifolio?nav=cmsUser&action=adm_usuario&user=" + idUser;
            }
            
            else{
                
                // Restaura o usuário solicitado
                
                int user = UsrDAO.getInstance().restauraUsr(id);
                
                if(user != 0){
                    request.setAttribute("msg", "<div class='msg_success'>Usuário restaurado com sucesso</div>");
                }
                
                else{
                    request.setAttribute("msg", "<div class='msg_erro'>A restauração falhou</div>");
                }
                
                redirect = "/portifolio?nav=cmsUser&action=adm_usuario&user=" + idUser;
            
            }
            

            proximaPagina = redirect;
        } 
        
        
        else if (operacao.equals("delCadUser")) {
            
            int id = Integer.parseInt(request.getParameter("codigo"));
            int idUser = Integer.parseInt(request.getParameter("user"));
            
            // Carrega os dados do usuário logado no sistema            
             Usuario usr = (Usuario) httpRequest.getSession().getAttribute("Usuario");
            
            // Verifica se o usuario está bloqueado antes da exclusão
            
            Usuario user = UsrDAO.getInstance().leDados(id);           
            
            
            if(usr.getTipo_id() != 1 || usr.getId_user() == id || user == null){
                request.setAttribute("msg", "<div class='msg_erro'>Ação não permitida</div>");
            }    
            
            else{
                
                if(user.getBloq().equals("false")){
                    request.setAttribute("msg", "<div class='msg_erro'>Bloqueie o usuário antes de excluir</div>");
                }
                
                else{
                    // Deleta o usuário solicitado
                
                    int del = UsrDAO.getInstance().delUsr(id);

                    if(del != 0){
                        request.setAttribute("msg", "<div class='msg_success'>Usuário deletado com sucesso</div>");
                    }

                    else{
                        request.setAttribute("msg", "<div class='msg_erro'>Não foi possivel apagar o usuário</div>");
                    }
                }
            
            }
            

            proximaPagina = "/portifolio?nav=cmsUser&action=adm_usuario&user=" + idUser;
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
            
            if(id != 1 || httpRequest.getSession().getAttribute("Permissao") == "2"){
                action = "/usrPass?operacao=homePainel";
                request.setAttribute("MsgErro", "<div class='msg-erro'>Ação não permitida</div>");
            }
            
            else{
                request.setAttribute("title", "Adicionar");
                request.setAttribute("operacao", "cad_usuario");
                request.setAttribute("lstTipo", lstTipo);
            } 

            proximaPagina = action;
        } 
        
        else if (operacao.equals("editDadosPessoais")) {


            List<Tipo> lstTipo = UsrDAO.getInstance().leTipo();

            //CRIA UM ATRIBUTO PARA MANDAR PARA A JSP
            request.setAttribute("lstTipo", lstTipo);

            proximaPagina = action;
        } 
        
        else if (operacao.equals("homePainel")) {
            
            int id = Integer.parseInt("0");
            List<Tipo> lstTipo = UsrDAO.getInstance().leTipo();            
            List<Usuario> listUsr = UsrDAO.getInstance().leTodos(id);
            
            request.setAttribute("listUsr", listUsr);
            request.setAttribute("lstTipo", lstTipo);
            
            proximaPagina = "/portifolio?nav=painel";
        }

        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);


    }
}
