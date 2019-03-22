$(function () {
    $.ajax({
        url:"/init",
        type:"post",
        dataType:"json",
        success:function (data) {
            console.log(data);
            var v = "";
            $.each(data,function (index,k) {
                v+='<a href='+k.href+'>'+k.title+'</a><br>';
            });
            $("#div").html(v);
        }
    })
});