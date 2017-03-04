<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="common/tag.jsp" %>
<%String path= request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表页</title>
    <%@include file="common/head.jsp" %>

</head>
<body>
<!-- 页面显示部分 -->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>秒杀列表</h2>

        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>库存</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>创建时间</th>
                    <th>详情页</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="spike" items="${list}">
                    <tr>
                        <td>${spike.name}</td>
                        <td>${spike.number}</td>
                        <td>
                            <fmt:formatDate value="${spike.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${spike.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${spike.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                        </td>
                        <td>
                            <a class="btn btn-info" href="<%=path%>/spike/${spike.spikeId}/detail" target="_blank">link</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>

</div>

</body>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="<%=path%>/jquery/jquery-3.1.1.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="<%=path%>/bootstrap/js/bootstrap.min.js"></script>
</html>
