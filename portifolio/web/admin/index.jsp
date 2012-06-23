<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${Usuario != null}">
    <jsp:forward page="/portifolio?nav=painel"></jsp:forward>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>

        <%@ include file="/partials/headLinks_css.jsp" %>
        
        <%@ include file="/partials/headLinks_js.jsp" %>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Painel Administrativo</title>

    </head>

    <body>

        <%@ include file="/partials/head.jsp" %>

        <div id="faixa-top">

            <div class="container_24">
                <div class="faixa-title contraste ico-login"> 
                    <h2>Login</h2>   	
                </div>

            </div>

        </div>

        <div id="container" class="container_24 text-left">

            <div class="box-shadow-droid form-standard box-login">
                <div class="title">
                    <h3>Acessar Painel de Controle</h3>
                </div>
                <div class="box-content">
                    <form name="login" action="/usrPass" method="post" id="form-login">
                        <input type="hidden" name="operacao" value="login" />
                        <input type="hidden" name="dispara" value="/admin/index.jsp" />
                        
                        <dl>
                            <dt><label for="login">Login</label></dt>
                            <dd><input type="text" name="login" /></dd>
                            <dt><label for="pwd">Senha</label></dt>
                            <dd><input type="password" name="pwd" /></dd>
                            <dt></dt>
                            <dd class="submit-element"><input type="submit" value="Entrar" /></dd>
                        </dl>
                    </form>
                    <c:if test="${MsgErro != null}">
                        <div class="msg-notification">
                             <%
                                if(request.getAttribute("MsgErro") != null) {
                                  out.print(request.getAttribute("MsgErro"));
                                }
                            %>
                        </div>
                    </c:if>                    
                </div>
            </div>
        </div>

        <%@ include file="/partials/footer.jsp" %>

    </body>
</html>