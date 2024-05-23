<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Insert title here</title>
    <script src="//code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        function f1(){
            $.ajax({
                url : "http://localhost:8081/t1/",
                type: 'GET',
            }).then(function(data) {
                console.log(data);
                console.log(123, data.factorA)
                let s1 = document.getElementById("span1");
                let s2 = document.getElementById("span2");
                s1.textContent = data.factorA;
                s2.textContent = data.factorB;
            });
        }
        function f3(){
            let alias = $('input[name=alias]').val();
            let a = $('#span1').text();
            let b = $('#span2').text();
            console.log(a, b)
            console.log(alias)

            let attempt = $('input[name=attempt]').val();

            let formData = {
                user:{alias: alias},
                multiplication:{factorA: a, factorB: b},
                resultAttempt:attempt
            };
            $.ajax({
                url : "http://localhost:8081/t2/",
                type: 'POST',
                data: JSON.stringify(formData),
                dataType : "json",
                contentType: "application/json"
            }).then(function(data) {
                console.log(typeof data);
                let s3 = document.getElementById("span3");
                s3.textContent = data===true?'�¾Ҿ�':'Ʋ�Ⱦ�';
            });
        }
    </script>

</head>
<body>
    <a href="t1">��ũ1</a><br/>
    <button onclick="f1();">������ �ּ���.</button><br/>
    <span id="span1"></span> ���ϱ�
    <span id="span2"></span> �� ?
    <form id="form1" name="form1" method="post">
        ��Ī : <input type='text' name="alias" value="tiger"/><br />
        ���� : <input type='text' name="attempt" value="200"/><br />
        <input type="button" value="����" onclick="f3();"/>
    </form>
    <h1><span id="span3"></span></h1>
</body>
</html>