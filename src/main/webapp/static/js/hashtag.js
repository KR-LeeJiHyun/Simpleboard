$(document).ready(function (){
    $('.btn_add').click(function(){
        $('.hashtag').append('<span class="hashtag-text"><input type="text" name="hashtag">\
        <button class="btn_remove w-btn-outline w-btn-gray-outline">X</button></span>');

        $('.btn_remove').on('click', function () { 
            $(this).prev().remove();    
            $(this).next().remove();      
            $(this).remove();                       
        });
    });
})