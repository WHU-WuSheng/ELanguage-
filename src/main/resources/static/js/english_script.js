$.get("/api/english", do_something);
function do_something(data, status) {
    //console.log(data);
    //console.log(data["_que_1"]);
    $("ol").append("<p>"+data["_que_1"]+"</p>");
    $("ol").append("<p>"+data["_que_2"]+"</p>");
    $("ol").append("<p>"+data["_que_3"]+"</p>");
    $("ol").append("<p>"+data["_que_4"]+"</p>");
    $("ol").append("<p>"+data["_que_5"]+"</p>");
    $("p").eq(0).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">A. the; /</a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">B. a; / </a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">C. the; a </a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">D. a; the</a>"+
        "</div>")
    $("p").eq(1).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">A. a;不填</a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">B. the;the</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">C.不填;the </a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">D. a; the</a>"+
        "</div>")
    $("p").eq(2).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">A. a </a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">B. an </a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">C. the</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">D.不填</a>"+
        "</div>")
    $("p").eq(3).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">A. The ; the</a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">B. A; a  </a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">C. The ; a</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">D. A; the</a>"+
        "</div>")
    $("p").eq(4).after("<div class='btn-group'>"+
    "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">A. thought </a>" +
    "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\"> B. support </a>"+
    "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">C. protection</a>"+
    "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">D. authority</a>"+
    "</div>"+"<br> <a  id='shi' onclick=\"over(this)\" class=\"finish btn btn-default btn-sm\" style='margin-bottom: 20px;margin-top: 20px;'>完成</a>")
}

var count = 0;
function score(id) {
    id.className = 'btn btn-info btn-sm';
    count += 20;
    console.log(count);
}

function change(id) {
    id.className = 'btn btn-info btn-sm';
    console.log(count);
}

function over(id) {
    id.className = 'btn btn-info btn-sm';
    ccc();
}

function ccc() {
    alert("您本次得分为:"+count.toString());
    var d = {};
    d.language = "英语";
    d.count = count;
    d.user= $("#username").val();
    $.ajax({
        type:'POST',
        data:JSON.stringify(d),
        contentType :'application/json',
        dataType:'json',
        url :'submit',
        success: function(response){
            if(response.success){
                alert(response.message);
            }
        }
    });
    window.location.href="/demo/chooseCapability";
    setTimeout("javascript:location.href='/demo/chooseCapability'",5000)
    count=0;
}



