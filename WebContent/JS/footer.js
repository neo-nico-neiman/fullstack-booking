$(function(){
    $('a').each(function(){
        if($(this).prop('href') == window.location.href){
            $(this).addClass('active');
            $(this).addClass('text-gray');
            $(this).parent('li').addClass('active');
        }
    })
});

