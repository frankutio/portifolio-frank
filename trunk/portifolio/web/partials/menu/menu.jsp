<div class="menu-tabs">
    <ul class="navigation">
        <c:if test="${area == 'open'}">
        <li class="menu-head">
            <span id="portifolio" class="menu-head-item active__"><a href="/">Portifólio</a></span>                
        </li>
        <li class="menu-head">
            <span id="contato" class="menu-head-item"><a href="/contato.jsp">Contato</a></span>
        </li>
        </c:if>        
        <c:if test="${pass-login != null}">
        <li class="menu-head">
            <span class="menu-head-item logout"><a href="/logoff?operacao=logoff">Sair</a></span>                
        </li>
        </c:if>
    </ul>
</div>