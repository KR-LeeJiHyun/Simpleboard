$(document).ready(function (){
    $('.btn_add').click(function(){
        
        if($('.hashtag').children().length != 6){
            $('.hashtag').append('<span class="hashtag-text"><input type="text" name="hashtag" required>\
            <button class="btn_remove w-btn-outline w-btn-gray-outline">X</button></span>');
            console.log($('.hashtag').children().length);
            $('.btn_remove').on('click', function () {     
                $(this).parent().remove();                       
            });
        }
        else alert("더 이상 태그를 추가할 수 없습니다!")
    });
})