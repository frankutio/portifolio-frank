<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>

        <%@ include file="/partials/headLinks_css.jsp" %>
        <%@ include file="/partials/restrict/headLinks_css.jsp" %>
        
        <%@ include file="/partials/headLinks_js.jsp" %>        
        
        <script type="text/javascript" src="/js/lib/tiny/tiny_mce.js"></script>
        <script type="text/javascript" src="/js/lib/tiny/editor-custon.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />        

        <title>Painel Administrativo</title>

    </head>

    <body>

        <div id="head">
            <div class="container_24 top-head">
                <div class="logo aling-left">
                    <a href="#">Frank Bezerra, desenvolvedor de interfaces Web, Brasilía - DF</a>
                </div>     
                <div class="clear"></div>
            </div>
        </div>

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
                        <form name="profile" action="#" method="post" id="form-dados-pessoais">
                            <dl>
                                <dt><label for="nome">Nome</label></dt>
                                <dd><input type="text" name="name" id="nome" /></dd>
                                <dt><label for="login">Login</label></dt>
                                <dd><input type="text" name="login" id="login" disabled="disabled" /></dd>
                                <dt><label for="email">E-mail</label></dt>
                                <dd><input type="text" name="email" id="email" /></dd>
                                <dt><label for="dat_nascimento">Data de Nascimento</label></dt>
                                <dd><input type="text" name="dat_nascimento" class="date" id="dat_nascimento" /></dd>                        
                                <dt>
                                    <div class="info-form">Escreva abaixo, um breve resumo sobre você como profissional, este resumo aparecerá em seu portifólio</div>
                                </dt>
                                <dt><label for="about">Sobre mim</label></dt>
                                <dd>
                                    <textarea name="about" id="about" class="rich-text-medium"></textarea>
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