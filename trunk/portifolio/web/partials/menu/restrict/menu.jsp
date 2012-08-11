<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menu-cms">
    <div class="menu-tabs">
        <ul class="navigation">
            <li class="menu-head">
                <span class="menu-head-item drop">Perfil</span>
                <ul>
                    <li><a href="/portifolio?nav=painel">Home</a></li>
                    <li>
                        <span>Dados cadastrais</span>
                        <ul>
                            <li><a href="/portifolio?nav=editDadosPessoais">Dados Pessoais</a></li>
                        </ul>
                    </li>
                    <li>
                        <span>Minha conta</span>
                        <ul>
                            <li><a href="/portifolio?nav=alterar-senha">Alterar senha</a></li>
                        </ul>
                    </li>
                </ul>                
            </li>
            <li class="menu-head">
                <span class="menu-head-item drop">Portif�lio</span>
                <ul>
                    <li><a href="/portifolio?nav=cmsPortifolio&action=adicionar_portifolio">Adicionar Portif�lio</a></li>
                    <li><a href="/portifolio?nav=cmsPortifolio&action=adm_portifolio">Administrar Portif�lio</a></li>
                </ul>
            </li>
            <li class="menu-head">
                <span class="menu-head-item drop">Galeria</span>
                <ul>
                    <li><a href="/portifolio?nav=cmsGalaeria&action=adicionar_foto">Adicionar Foto</a></li>
                    <li><a href="/portifolio?nav=cmsGalaeria&action=adm_galeria">Administrar Galeria</a></li>
                </ul>
            </li>
            <li class="menu-head">
                <span class="menu-head-item drop">Usu�rios</span>
                <ul>
                    <c:if test="${Usuario.tipo_id == 1}">
                    <li><a href="/portifolio?nav=cmsUser&action=adicionar_usuario&user=${Usuario.id_user}">Adicionar Usu�rio</a></li>
                    </c:if>                    
                    <li><a href="/portifolio?nav=cmsUser&action=adm_usuario&user=${Usuario.id_user}">Administrar Usu�rios</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="clear"></div>
</div>