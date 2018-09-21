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

        <script type="text/javascript" src="/js/lib/new_tiny/tinymce.min.js"></script>
        <script type="text/javascript" src="/js/lib/new_tiny/editor-custon.js"></script>        

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
                        <h3>Meus Dados Pessoais</h3>
                    </div>
                    <div class="box-content">
                        <form name="profile" action="/usrPass" method="post" id="form-dados-pessoais">
                            <input type="hidden" name="operacao" value="alterar_dados_pessoais" />
                            <input type="hidden" name="usr" value="${Usuario.id_user}" />
                            
                            <dl>
                                <dt><label for="tipo">Tipo</label></dt>
                                <dd>
                                    <select id="tipo" name="tipo" disabled="disabled">
                                        <option value="0"></option>
                                        <c:forEach items="${lstTipo}" var="lstTipo">
                                        <option value="${lstTipo.id}"
                                            <c:if  test="${Usuario.tipo_id == lstTipo.id}">
                                                selected ="selected"
                                            </c:if>   > ${lstTipo.tipo_user}</option>
                                        </c:forEach>
                                    </select>
                                </dd>
                                <dt><label for="nome">Nome</label></dt>
                                <dd><input type="text" name="name" id="nome" value="${Usuario.nome}" /></dd>
                                <dt><label for="login">Login</label></dt>
                                <dd><input type="text" name="login" id="login" disabled="disabled" value="${Usuario.login}" /></dd>
                                <dt><label for="email">E-mail</label></dt>
                                <dd>
                                    <input type="text" name="email" id="email" value="${Usuario.email}" />
                                    <c:if test="${msgErroEmail != null || msgErroEmail != ''}">
                                        <div>${msgErroEmail}</div>
                                    </c:if>
                                </dd>
                                <dt><label for="dat_nascimento">Data de Nascimento</label></dt>
                                <dd>
                                    <input type="text" name="dat_nascimento" class="date" id="dat_nascimento" value="${Usuario.dataNascimento}" />
                                    <c:if test="${msgErroData != null || msgErroData != ''}">
                                        <div>${msgErroData}</div>
                                    </c:if>
                                </dd>
                                <dt>
                                    <div class="info-form">Escreva abaixo, um breve resumo sobre você como profissional, este resumo aparecerá em seu portifólio</div>
                                </dt>
                                <dt><label for="about">Sobre mim</label></dt>
                                <dd>
                                    <textarea name="about" id="about" class="rich-text-medium rich-text">${Usuario.about}</textarea>
                                    <span class="count">397</span>
                                </dd>
                                <dt></dt>
                                <dd class="submit-element"><input type="submit" value="Atualizar" /></dd>                                
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