$.get("/api/chinese", do_something);
function do_something(data, status) {
    //console.log(data);
    //console.log(data["_que_1"]);
    $("ol").append("<p>"+data["_que_1"]+"</p>");
    $("ol").append("<p>"+data["_que_2"]+"</p>");
    $("ol").append("<p>"+data["_que_3"]+"</p>");
    $("ol").append("<p>"+data["_que_4"]+"</p>");
    $("ol").append("<p>"+data["_que_5"]+"</p>");
    $("p").eq(0).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">A. 차이</a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">B. 인기</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">C. 주위</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">D. 결과</a>"+
        "</div><br>")
    $("p").eq(1).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">A. 자르고 </a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">B. 그치고 </a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">C. 그만두고</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">D. 지나가고</a>"+
        "</div><br>")
    $("p").eq(2).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">A. 마치</a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">B. 먼저</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">C. 만약</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">D. 아마</a>"+
        "</div><br>")
    $("p").eq(3).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">A. 맞지</a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">B. 닿지</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">C. 떨리지</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">D. 빠지지</a>"+
        "</div><br>")
    $("p").eq(4).after("<div class='btn-group'>"+
        "<a href='javascript:void(0)' type='button' onclick=\"score(this)\" class=\"btn btn-default btn-sm\">A. 활발하게 </a>" +
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\"> B. 고소하게 </a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">C. 자세하게</a>"+
        "<a href='javascript:void(0)' type='button' onclick=\"change(this)\" class=\"btn btn-default btn-sm\">D. 소중하게</a>"+
        "</div>"+ "<br><a  id='shi' onclick=\"over(this)\" class=\"btn btn-default btn-sm\" style='margin-bottom: 20px;margin-top: 20px;'>完成</a>")
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
        d.language = "韩语";
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



