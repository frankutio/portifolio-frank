<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="menu-tabs">
    <ul class="navigation">
        <c:if test="${local == 'open'}">
        <li class="menu-head">
            <span id="portifolio" class="menu-head-item active__"><a href="/portifolio?nav=portifolio">Portifólio</a></span>                
        </li>
        <li class="menu-head">
            <span id="contato" class="menu-head-item"><a href="/portifolio?nav=contato">Contato</a></span>
        </li> 
        </c:if>
                 
        <c:if test="${Usuario != null}">
        <li class="menu-head">
            <span class="menu-head-item logout"><a href="/logoff?operacao=logoff">Sair</a></span>                
        </li>
        </c:if>
    </ul>
</div>