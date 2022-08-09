$(document).ready(function (){
    $('.comment_update').on('click',function(){
        $(this).parent().parent().children()[0].classList.add("hidden");
        $(this).parent().parent().children()[1].classList.remove("hidden");
    });
    $('.comment_cancel').on('click', function(){
        $(this).parent().parent().parent().children()[0].classList.remove("hidden");
        $(this).parent().parent().parent().children()[1].classList.add("hidden");
    });
})