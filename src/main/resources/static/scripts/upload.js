$(document).on('click', '#deleteSelected', function(event){

    event.preventDefault();
    var pathname = '/deleteFiles';
    var param =[];

    $('input[name=radio]:checked').each(function() {
        //console.log($(this));
        //console.log($(this).parent().text());
        param.push($(this).parent().text());
    });
    $.ajax({
        "url": pathname;
        "data": {param}
    })
})