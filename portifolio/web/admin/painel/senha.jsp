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
                        <h3>Alterar senha</h3>
                    </div>
                    <div class="box-content">
                        <form name="profile" action="/usrPass" method="post" id="form-alt-senha">
                            <input type="hidden" name="operacao" value="alterar_senha" />
                            <input type="hidden" name="usr" value="${Usuario.id_user}" />
                            
                            <dl>
                                <dt><label for="senha">Digite sua senha atual</label></dt>
                                <dd>
                                    <input type="password" name="senha" id="senha" value="" />
                                    <c:if test="${msgErroSenha != null || msgErroSenha != ''}">
                                        <div>${msgErroSenha}</div>
                                    </c:if>
                                </dd>
                                <dt><label for="novaSenha">Nova senha</label></dt>
                                <dd>
                                    <input type="password" name="novaSenha" id="novaSenha" />
                                    <c:if test="${msgErroNovaSenha != null || msgErroNovaSenha != ''}">
                                        <div>${msgErroNovaSenha}</div>
                                    </c:if>
                                </dd>
                                <dt><label for="confSenha">Confirme a nova senha</label></dt>
                                <dd><input type="password" name="confSenha" id="confSenha" /></dd>
                                <dt></dt>
                                <dd class="submit-element"><input type="submit" value="Alterar" /></dd>                                
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