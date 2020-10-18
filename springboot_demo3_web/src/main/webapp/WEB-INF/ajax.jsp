<%--
  Created by IntelliJ IDEA.
  User: wy
  Date: 2020/9/24
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>您好Springboot</title>
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        //让页面加载完成之后执行
        $(function(){

            /*$.get("/findAjax",function (data){
                let trs=null;
                for(let user of data){
                    let id = user.id;
                    let name = user.name;
                    let age = user.age;
                    let sex = user.sex;
                    //最终需要在table中展现
                    trs += "<tr align='center'><td>"+id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+sex+"</td></tr>"
                }
                //将结果追加到table中即可.
                $("#tab1").append(trs);
            });*/

            //1.$.get("url地址","添加参数","回调函数","返回值结果类型 text/html/json....一般ajax会自动匹配.");
            /*$.get("/findAjax",function(data){
                //data = [{user},{user},{user}]  es6~jsp中冲突
                //需求: 将userList集合信息动态的添加到table中.
                console.log(data);
                let trs = null;
                $(data).each(function(index){
                    //index代表循环遍历的下标从0开始
                    let user = data[index];
                    let id = user.id;
                    let name = user.name;
                    let age = user.age;
                    let sex = user.sex;
                    //最终需要在table中展现
                    trs += "<tr align='center'><td>"+id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+sex+"</td></tr>"
                });

                //将结果追加到table中即可.
                $("#tab1").append(trs);
            });*/

            //2.$.post();
            //3.$.getJSON();
            //4.$.getScript();
            //5.$.ajax();  说明
            /*$.ajax({
                type: "get",
                url: "/findAjax2",
                //data: {"id":"1","name":"tomcat"},
                data: "id=1&name=tomcat",
                success: function(data){
                    alert("ajax调用成功!!!");
                    alert(data);
                },
                async :  true,
                error :  function(data){
                    alert("服务器异常,请稍后重试!!!!");
                }
            });*/

            $.ajax({
                type: "get",
                url: "/findAjax",
                async:true,
                success:function (data){
                    let trs=null;
                    for(let user of data){
                        let id = user.id;
                        let name = user.name;
                        let age = user.age;
                        let sex = user.sex;
                        //最终需要在table中展现
                        trs += "<tr align='center'><td>"+id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+sex+"</td></tr>"
                    }
                    //将结果追加到table中即可.
                    $("#tab1").append(trs);
                },
                error:function (){
                    alert("服务器异常,请稍后重试!!!!");
                }
            });

        })

    </script>
</head>
<body>
<table id="tab1"  border="1px" width="65%" align="center">
    <tr>
        <td colspan="6" align="center"><h3>学生信息</h3></td>
    </tr>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th></th>
    </tr>
</table>
</body>
</html>