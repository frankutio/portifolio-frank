<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="content-modal text-left box-shadow-droid">
    
    <div class="title">
        <h3>Alterar Foto</h3>
    </div>
    
    <div class="box-content form-standard">
        <form name="photo" method="post" action="/upload?operacao=trocaFoto" enctype="multipart/form-data" id="FormFoto">
            <input type="hidden" name="usrId" value="${Usuario.id_user}" /> 
            <input type="hidden" name="action" value="${Usuario.foto}" />
            <dl>
                <dt>
                    <label for="photo"></label>
                </dt>
                <dd>
                    <input type="file" name="photo" id="photo" />
                </dd>
                <dt></dt>
                <dd class="submit-element">
                    <input type="submit" value="Atualizar" />
                </dd>
            </dl>
        </form>
    </div>    
    
</div>