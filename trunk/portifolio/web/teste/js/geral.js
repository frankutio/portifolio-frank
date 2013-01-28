$(function(){
    
    $('.hide-element').bind('expand', function () {
        var select = $(this).attr("data-target");
        $("#"+select).hide();
    }).bind('collapse', function () {
        var select = $(this).attr("data-target");
        $("#"+select).slideDown();
    });

})