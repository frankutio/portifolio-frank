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

        <script type="text/javascript" src="/js/lib/tiny/tiny_mce.js"></script>
        <script type="text/javascript" src="/js/lib/tiny/editor-custon.js"></script>        

    </head>

    <body>

        <%@ include file="/partials/head.jsp" %>

        <div id="faixa-top">

            <div class="container_24">
                <div class="faixa-title contraste ico-sessao"> 
                    <h2>Dados Pessoais</h2>   	
                </div>

            </div>

        </div>

        <div id="container" class="container_24 text-left">

            <%@ include file="/partials/menu/restrict/menu.jsp" %>

            <div class="content">

                <div class="box-shadow-droid form-standard form-full">
                    <div class="title">
                        <h3>${title} Usuário</h3>
                    </div>
                    <div class="box-content">
                        <form name="profile" action="/usrPass" method="post" id="form-dados-pessoais">
                            <input type="hidden" name="operacao" value="${operacao}" />
                            <dl>
                                <dt><label for="tipo">Tipo</label></dt>
                                <dd>
                                    <select id="tipo" name="tipo">
                                        <option value="0"></option>
                                        <c:forEach items="${lstTipo}" var="lstTipo">
                                        <option value="${lstTipo.id}"
                                            <c:if  test="${usr.tipo_id == lstTipo.id}">
                                                selected ="${lstTipo.tipo_user}"
                                            </c:if>   > ${lstTipo.tipo_user}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${msgErroTipo != null || msgErroTipo != ''}">
                                        <div>${msgErroTipo}</div>
                                    </c:if>
                                </dd>
                                <dt><label for="nome">Nome</label></dt>
                                <dd><input type="text" name="name" id="nome" value="${usr.nome}" /></dd>
                                <dt><label for="login">Login</label></dt>
                                <dd>
                                    <input type="text" name="login" id="login" value="${usr.login}" />
                                    <c:if test="${msgErroLogin != null || msgErroLogin != ''}">
                                        <div>${msgErroLogin}</div>
                                    </c:if>
                                </dd>
                                <dt><label for="email">E-mail</label></dt>
                                <dd>
                                    <input type="text" name="email" id="email" value="${usr.email}" />
                                    <c:if test="${msgErroEmail != null || msgErroEmail != ''}">
                                        <div>${msgErroEmail}</div>
                                    </c:if>
                                </dd>
                                <dt><label for="senha">Senha</label></dt>
                                <dd>
                                    <input type="password" name="senha" id="senha" value="${usr.senha}" />
                                    <c:if test="${msgErroSenha != null || msgErroSenha != ''}">
                                        <div>${msgErroSenha}</div>
                                    </c:if>
                                </dd>
                                <dt><label for="confSenha">Confirme a senha</label></dt>
                                <dd><input type="password" name="confSenha" id="confSenha" value="" /></dd>
                                <dt><label for="dat_nascimento">Data de Nascimento</label></dt>
                                <dd>
                                    <input type="text" name="dat_nascimento" class="date" id="dat_nascimento" value="${usr.getData_nascimentoString()}" />
                                    <c:if test="${msgErroData != null || msgErroData != ''}">
                                        <div>${msgErroData}</div>
                                    </c:if>
                                </dd> 
                                <dt></dt>
                                <dd class="submit-element"><input type="submit" value="Cadastrar" /></dd>
                            </dl>
                        </form>
                                    
                    </div>        	
                </div>

                <div class="clear"></div>

            </div>
        </div>

        <%@ include file="/partials/footer.jsp" %>

    </body>
</html>