/*
 * Fancybox para ser usado em requisições com modais
 */
function showFancyboxModal(url){
    $.fancybox({
        'type': 'ajax',
        'ajax': { 
            type : "POST",
            cache : false,
            url : url              
        },
        
        'autoDimensions':true,
        'scrolling':'auto' 
    });
}