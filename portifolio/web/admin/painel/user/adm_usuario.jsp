<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${local == 'open'}">
    <jsp:forward page="/portifolio?nav=painel"></jsp:forward>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>

        <%@ include file="/partials/headLinks_css.jsp" %>
        <%@ include file="/partials/restrict/headLinks_css.jsp" %>

        <%@ include file="/partials/headLinks_js.jsp" %>
        <%@ include file="/partials/restrict/headLinks_js.jsp" %>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Painel Administrativo</title>

    </head>

    <body>

        <%@ include file="/partials/head.jsp" %>

        <div id="faixa-top">

            <div class="container_24">
                <div class="faixa-title contraste ico-sessao"> 
                    <h2>Administrar Usuários</h2>   	
                </div>
            </div>

        </div>

        <div id="container" class="container_24 text-left">

            <%@ include file="/partials/menu/restrict/menu.jsp" %>

            <div class="content">

                <c:if test="${msg != null || msg != ''}">
                    <div>${msg}</div>
                </c:if>

                <div class="band-title-gear">
                    <span>Usuário(s) do Portal</span>
                </div>

                <div class="menu-gear">
                    <div class="table-crud">
                        <table class="crud doiscell" cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <th class="center date">
                                        <div>
                                            <h5>Tipo</h5>
                                        </div>
                                    </th>
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
                                            <h5>Status</h5>
                                        </div>
                                    </th>
                                    <th class="center login">
                                        <div>
                                            <h5>Ação</h5>
                                        </div>
                                    </th>
                                </tr>
                                <c:if test="${listUsr == null || listUsr == ''}">
                                    <tr>
                                        <td colspan="4" class="msg-table empty">
                                            <div class="center">
                                                <h5 class="font-normal">Não existem usuários cadastrados</h5>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${listUsr != null || listUsr != ''}">
                                    <c:forEach items="${listUsr}" var="usr">
                                        <tr>
                                            <td class="center">
                                                <c:forEach items="${lstTipo}" var="tipo">
                                                    <c:if test="${tipo.id == usr.tipo_id}">
                                                        ${tipo.tipo_user}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td class="center">${usr.login}</td>
                                            <td>${usr.nome}</td>
                                            <td class="center">
                                                <c:if test="${usr.lixo == 'false'}">
                                                    <span class="active">Ativo</span>
                                                </c:if> 
                                                <c:if test="${usr.lixo == 'true'}">
                                                    <span class="inactive">bloqueado</span>
                                                </c:if> 
                                            </td>
                                            <td class="list-button small">                                                
                                                <c:if test="${usr.lixo == 'true'}">
                                                    <a href="/usrPass?operacao=restalCadUser&codigo=${usr.id_user}" class="Recuperar button-gray bt-img restal" title="Recuperar">
                                                        <span></span>
                                                    </a>
                                                </c:if>
                                                <c:if test="${usr.lixo == 'false'}">
                                                    <a href="/usrPass?operacao=editCadUser&codigo=${usr.id_user}" class="alterar button-gray bt-img edit" title="Editar">
                                                        <span></span>
                                                    </a>
                                                    <a href="/usrPass?operacao=bloqCadUser&codigo=${usr.id_user}" class="bloquear button-gray bt-img bloq" title="Bloquear">
                                                        <span></span>
                                                    </a>
                                                </c:if>                                                
                                            </td>
                                        </tr>                                      
                                    </c:forEach>
                                </c:if>

                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="clear"></div>

            </div>

        </div>

        <%@ include file="/partials/footer.jsp" %>

    </body>
</html>