<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<%@ include file="/partials/headLinks_css.jsp" %>

<%@ include file="/partials/headLinks_js.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Portif�lio Frank Bezerra</title>

</head>

<body>

<%@ include file="/partials/head.jsp" %>

<div id="faixa-top">

  <div class="container_24">
    <div class="grid_24 presentation aling-left">    
      <%@ include file="/partials/about.jsp" %>
    </div>
    
  </div>
  
</div>

<div id="container" class="container_24">
  <div class="home-about aling-left">
  
    <div class="F1 about-F about1"> 
    	<img src="/img/medicando/capa.png" alt="Medicando" rel="#medicando" width="431" height="324" />
      <div class="item-content">
        <h2 class="font-normal">Portal Medicando</h2>
        <p class="item-description">
        	<strong>Empresa:</strong> Medicando
        </p>
        <p class="item-description">
        	<strong>Servi�o:</strong> Design da interface
        </p>
        <p class="item-description">
        	<strong>Tecnologias:</strong> XHTML, CSS, CSS 3, Tableless, Web Standards, Jquery, Photoshop e FireWorks CS5
        </p>
        <p class="item-description">
        	<strong>Descri��o:</strong> O Medicando � a rede para quem cuida da sa�de. Aqui voc� encontrar�, num s� lugar, conte�do de qualidade, buscador completo de profissionais de sa�de e uma rede social, onde pacientes poder�o interagir, trocar ideias e experi�ncias com seus especialistas de prefer�ncia.
        </p>
      </div>
    </div>
    
    <div class="F1 about-F about2">
    	<img src="/img/mirante/capa.png" alt="Mirante" rel="#mirante" width="431" height="324" />
      <div class="item-content">
        <h2 class="font-normal">Mirante Tecnologia</h2>
        <p class="item-description">
        	<strong>Empresa:</strong> Mirante Tecnologia
        </p>
        <p class="item-description">
        	<strong>Servi�o:</strong> Design da interface e analista de Interface
        </p>
        <p class="item-description">
        	<strong>Tecnologias:</strong> XHTML, CSS, Tableless, Web Standards, Jquery, Photoshop e FireWorks CS5
        </p>
        <p class="item-description">
        	<strong>Descri��o:</strong> A Mirante � uma empresa que presta servi�os aos �rg�os p�blicos para desenvolvimento de sistemas Web, ao longo de minha jornada, trabalhei com o desenvolvimento para os portais da ANVISA, Aeronautica e TC-DF.
        </p>
      </div>
    </div>
    
  </div>
</div>

<%@ include file="/partials/footer.jsp" %>


<!-- Galeria Mirante -->
<div id="mirante" class="apple_overlay black"> 
  <a class="close"></a>
  <!-- Galeria de Fotos --> 
  <!-- "previous page" action --> 
  <a class="prev browse left" title="Voltar">Voltar</a> 
  
  <!-- root element for scrollable -->
  <div class="scrollable"> 
    
    <!-- root element for the items -->
    <div class="items"> 
      
      <!-- 1 -->
      <div> 
      	<img src="/img/mirante/mirante01.png" alt="Poster da Mirante" />
        <div class="details">
            <h2>Intranet Mirante</h2>
            <p> Template escolhido para a nova intranet da empresa. </p>
        </div>
      </div>
      
      <!-- 2 -->
      <div> 
      	<img src="/img/mirante/mirante02.png" alt="Intranet Mirante" />
        <div class="details">
            <h2>Intranet Mirante</h2>
            <p> Template escolhido para a nova intranet da empresa. </p>
        </div>
      </div>
      
      <!-- 3 -->
      <div>
      	<img src="/img/mirante/mirante03.png" alt="Portal Mirante" />
        <div class="details">
            <h2>Portal Mirante</h2>
            <p> Ajudei na escolha da paleta de cores, marca��o e comportamento dos menus.</p>
        </div>
      </div>
    </div>
    
  </div>
  
  <!-- "next page" action --> 
  <a class="next browse right" title="Avan�ar">Avan�ar</a> 
  <!-- Galeria de Foto -->  
  
</div>

<!-- Galeria Mirante -->


<!-- Galeria Medicando -->
<div id="medicando" class="apple_overlay black"> 
  <a class="close"></a>
  <!-- Galeria de Fotos --> 
  <!-- "previous page" action --> 
  <a class="prev browse left" title="Voltar">Voltar</a> 
  
  <!-- root element for scrollable -->
  <div class="scrollable"> 
    
    <!-- root element for the items -->
    <div class="items"> 
      
      <!-- 1 -->
      <div> 
      	<img src="/img/medicando/medicando01.png" alt="Tela inicial do portal Medicando" />
        <div class="details">
            <h2>Portal Medicando</h2>
            <p> Diagrama��o de campos, paleta de cores, padr�es Web e sem�ntica.</p>
        </div>
      </div>
      
      <!-- 2 -->
      <div> 
      	<img src="/img/medicando/medicando02.png" alt="Tela de Vantagens Medciando" />
        <div class="details">
            <h2>Tela Vantagens Medicando</h2>
            <p> Efeitos de imagens, diagrama��o e disposi��o de campos. </p>
        </div>
      </div>
      
      <!-- 3 -->
      <div>
      	<img src="/img/medicando/medicando03.png" alt="Tela de Login" />
        <div class="details">
            <h2>Tela de Login</h2>
            <p> Efeitos de imagens, diagrama��o e disposi��o de campos, cria��o de bot�es e formata��o de formul�rios.</p>
        </div>
      </div>
      
      <!-- 4 -->
      <div>
      	<img src="/img/medicando/medicando06.png" alt="Noticias Medicando" />
        <div class="details">
            <h2>Se��o de Not�cias</h2>
            <p> Uso de CSS 3 e sem�ntica.</p>
        </div>
      </div>
      
      <!-- 5 -->
      <div>
      	<img src="/img/medicando/medicando07.png" alt="Not�cia na integra" />
        <div class="details">
            <h2>Conte�do na Integra</h2>
            <p> Diagrama��o e disposi��o de campos.</p>
        </div>
      </div>
      
    </div>
    
  </div>
  
  <!-- "next page" action --> 
  <a class="next browse right" title="Avan�ar">Avan�ar</a> 
  <!-- Galeria de Foto -->  
  
</div>

<!-- Galeria Medicando -->


</body>
</html>