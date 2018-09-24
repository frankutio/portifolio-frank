package Controle.Portal;

import Entidade.Portal.Image;
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

import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.io.File;
import javax.servlet.ServletContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FilerUpload extends HttpServlet {
    
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
        
        // Verifica o caminho do contexto e cria variavel
        ServletContext context = getServletContext();
        String contextoPath = context.getRealPath(File.separator);
        
        //RECEBE O TIPOD E OPERACAO A REALIZAR
        String operacao = request.getParameter("operacao");
        String action = request.getParameter("action");       


        //LOG PARA TESTE
        System.out.println("Controle Acionado com Operacao: " + operacao);
        
        // Instancia o objeto usuário
        Usuario usr = new Usuario();
        
        //Instancia a foto
        
        Image img = new Image();

        String proximaPagina = "";
        
        if (operacao.equals("trocaFoto")) {
            
            // Prepara a Servlet para trabalhar com upload
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Manipulador de upload de arquivos
            ServletFileUpload upload = new ServletFileUpload(factory);
            
            if (ServletFileUpload.isMultipartContent(request)) {
                
                try {
                    // Recebe lista de campos do formulario
                    List<FileItem> itens = upload.parseRequest(request);

                    for (FileItem fi : itens) {
                        
                        if (fi.isFormField()) {
                            
                            // Recebe os campos q nao sao file
                            if(fi.getFieldName().equals("usrId")){
                                    usr.setId_user(Integer.parseInt(fi.getString()));
                              }
                            
                            if(fi.getFieldName().equals("action")){
                                img.setUrl(fi.getString());
                            }
                        }   
                        else {
                           
                                // Cria a pasta no perfil do usuário
                                String diretorio = contextoPath + "/img/perfil/" + usr.getId_user() + "/";
                                
                                java.io.File f = new java.io.File(diretorio);
                                f.mkdir();
                                
                                // Apaga a foto no perfil do usuário (se houver)
                                java.io.File delF = new java.io.File(contextoPath + img.getUrl());
                                delF.delete();
                                
                               // Retira o nome do arquivo (Usado para corrigir bug do IE)
                                String nome = fi.getName().toString();
                                String arquivo = nome.substring(nome.lastIndexOf("\\")+1);
                                
                                // Cria um objeto file com nome do arquivo
                                // A pasta deve oferecer acesso de escrita para Conteiner
                                File uploadedFile = new File(diretorio + arquivo);
                                // Grava arquivo na pasta especificada
                                fi.write(uploadedFile);
                                
                                // Grava o nome do arquivo no perfil do usuario
                                usr.setFoto("img/perfil/" + usr.getId_user() + "/" + arquivo);
                                UsrDAO.getInstance().alteraFoto(usr.getId_user(), usr.getFoto());
                                request.setAttribute("msg", "<div class='msg_success'>Foto alterada com sucesso</div>");
                                
                                // Atualiza os dados do usuário
                                Usuario usuario = UsrDAO.getInstance().carregaDados(usr.getId_user());
                                request.getSession().setAttribute("Usuario", usuario);
                            }
                    }
                }
                catch (Exception ex) {
                    request.setAttribute("Error", ex.getMessage());
                    proximaPagina = "/usrPass?operacao=homePainel";
                }            
            }
            
            proximaPagina = "/usrPass?operacao=homePainel";
        }
        
        //PARA DIRECIONAR AS PAGINAS PARA O LOCAL CERTO.
        RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);
        
    }
}