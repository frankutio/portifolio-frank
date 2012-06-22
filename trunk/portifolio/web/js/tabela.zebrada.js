/*
projeto: frankbezerra.com
arquivo: tabelas.zebradas.js
autor: Frank Bezerra - frankutio@gmail.com
resumo: Marca as tabelas de forma zebrada (cor alternada)


« Resumo »
<<------------------------------------------------------------------------------
Espera ser executado o evento 'ajaxLoadContentSucess' , após isso , marca as
linhas das tabelas que tenham a classe «tabela-zebrada»
------------------------------------------------------------------------------>>

*/

function zebrar_tabela(){    
    $('table.crud tr:odd').addClass('zebra');  
    $('table.crud tr:even').addClass(''); 
    
}

function zebrar_ul(){
    
    var li = $('.category-father');
    for (var i=0; i < li.length; i++) {
        var className = (i%2==0) ? 'zebra' : '';
        li.eq(i).addClass(className);
    }
}