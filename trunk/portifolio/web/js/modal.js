/* Escuta um elemento para startar uma modal */


// Modal    
$(".show-modal").click(function(element) {
    element.preventDefault();
    showFancyboxModal(this.href);
    return false;
});