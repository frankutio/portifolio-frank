<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${local == 'open'}">
    <jsp:forward page="/portifolio?nav=painel"></jsp:forward>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        
        <%@ include file="/partials/headLinks.jsp" %>
        
        <title>Painel Administrativo</title>

        <%@ include file="/partials/headLinks_css.jsp" %>
        <%@ include file="/partials/restrict/headLinks_css.jsp" %>

        <%@ include file="/partials/headLinks_js.jsp" %>
        <%@ include file="/partials/restrict/headLinks_js.jsp" %>
        

    </head>

    <body>

        <%@ include file="/partials/head.jsp" %>

        <div id="faixa-top">

            <div class="container_24">
                <div class="faixa-title contraste ico-painel"> 
                    <h2>Painel Administrativo</h2>   	
                </div>
            </div>

        </div>

        <div id="container" class="container_24 text-left">

            <%@ include file="/partials/menu/restrict/menu.jsp" %>

            <div class="content">
                
                <c:if test="${msg != null || msg != ''}">
                    <div>${msg}</div>
                </c:if>
                    
                <c:if test="${MsgErro != null || MsgErro != ''}">
                    ${MsgErro}
                </c:if>                    
                

                <div class="grid_4 inside-collumns">
                    <!-- Foto -->
                    <div class="box-content-photo">
                        <div class="container-details">
                            <div class="foto">
                                <img src="${Usuario.foto}" alt="Imagem do usuario" width="125" height="125" />
                            </div> 
                            <div class="slide-bt">
                                <a href="/admin/painel/teste.jsp" class="show-modal">Alterar foto</a>
                            </div>
                        </div>
                    </div> 
                    <div class="clear"></div>           
                    <!-- Foto -->
                </div>

                <div class="grid_20 inside-collumns collumn-inside-center-full">
                    <div class="band-title-gear">
                        <span>Sobre mim</span>
                    </div>

                    <div class="menu-gear">
                        <h4>&quot; Meu nome é <strong>Frank Bezerra</strong>, sou <strong>Desenvolvedor de interfaces Web</strong> no portal <span class="underline">Medicando</span>, especializado em Design-web, crio interfaces e templates para portais seguindo os padrões de web standards e acessibilidade. &quot;</h4>
                    </div>

                    <div class="band-title-gear">
                        <span>Portifólio(s) Cadastrado(s)</span>
                    </div>

                    <div class="menu-gear">
                        <div class="table-crud">
                            <table class="crud trescell" cellpadding="0" cellspacing="0">
                                <tr>
                                    <th class="center date">
                                        <div>
                                            <h5>Data</h5>
                                        </div>
                                    </th>
                                    <th class="center content-tab">
                                        <div>
                                            <h5>Portifólio</h5>
                                        </div>
                                    </th>
                                    <th class="center login">
                                        <div>
                                            <h5>Dono</h5>
                                        </div>
                                    </th>
                                </tr>
                                <tr>
                                    <td class="center">20/03/2007</td>
                                    <td>Medicando</td>
                                    <td class="center">Frank</td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="band-title-gear">
                        <span>Mensagem(s)</span>
                    </div>

                    <div class="menu-gear">
                        <div class="table-crud">
                            <table class="crud doiscell" cellpadding="0" cellspacing="0">
                                <tr>
                                    <th class="center date">
                                        <div>
                                            <h5>Data</h5>
                                        </div>
                                    </th>
                                    <th class="center content-tab">
                                        <div>
                                            <h5>Assunto</h5>
                                        </div>
                                    </th>
                                </tr>
                                <tr>
                                    <td class="center">20/03/2007</td>
                                    <td>Medicando</td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="band-title-gear">
                        <span>Usuário(s) do Portal</span>
                    </div>

                    <div class="menu-gear">
                        <div class="table-crud">
                            <table class="crud doiscell" cellpadding="0" cellspacing="0">
                                <tr>
                                    <th class="center date">
                                        <div>
                                            <h5>Login</h5>
                                        </div>
                                    </th>
                                    <th class="center content-tab">
                                        <div>
                                            <h5>Nome</h5>
                                        </div>
                                    </th>
                                    <th class="center date">
                                        <div>
                                            <h5>Tipo</h5>
                                        </div>
                                    </th>
                                </tr>
                                <c:if test="${listUsr == null || listUsr == ''}">
                                    <tr>
                                        <td colspan="3" class="msg-table empty">
                                            <div class="center">
                                                <h5 class="font-normal">Não existem usuários cadastrados</h5>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${listUsr != null || listUsr != ''}">
                                    <c:forEach items="${listUsr}" var="usr">
                                        
                                        <tr <c:if test="${usr.bloq == 'true'}">class="bloq"</c:if>>
                                            <td class="center">${usr.login}</td>
                                            <td>${usr.nome}</td>
                                            <td class="center">
                                                <c:forEach items="${lstTipo}" var="tipo">
                                                    <c:if test="${tipo.id == usr.tipo_id}">
                                                        ${tipo.tipo_user}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>                                      
                                    </c:forEach>
                                </c:if>
                            </table>
                        </div>
                    </div>

                    <script type="text/javascript">
                        <!-- 
                        $(function(){ 
                            // zebrando as tabelas        
                            zebrar_tabela();
                        });
                        
                        // Modal    
                        $(".show-modal").live('click', function(element) {
                            element.preventDefault();
                            showFancyboxModal(this.href);
                            return false;
                        });
                        -->
                    </script>


                </div>

                <div class="clear"></div>

            </div>
            
        </div>

        <%@ include file="/partials/footer.jsp" %>

    </body>
</html>