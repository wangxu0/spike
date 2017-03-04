<%@page contentType="text/html;charset=utf-8" language="java" %>
<%String path= request.getContextPath();%>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp" %>

</head>
<body>
<!-- 页面显示部分 -->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h1>${spike.name}</h1>
        </div>
        <div class="panel-body text-center">
            <h2 class="text-danger">
                <!--显示time图标-->
                <span class="glyphicon glyphicon-time"></span>
                <!--显示倒计时-->
                <span class="glyphicon" id="spike-box"></span>
            </h2>
        </div>
    </div>
</div>
<div id="spikePhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"></span>手机号：
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="spikePhone" id="spikePhoneKey" placeholder="手机号" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span id="spikePhoneMessage" class="glyphicon"></span>
                <button type="button" id="spikePhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    提交
                </button>
            </div>
        </div>
    </div>
</div>

</body>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="<%=path%>/jquery/jquery-3.1.1.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="<%=path%>/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/jquery/jquery.cookie.min.js"></script>
<script src="<%=path%>/jquery/jquery.countdown.min.js"></script>
<script src="<%=path%>/js/spike.js" type="text/javascript"></script>
<script type="text/javascript">

    $(function () {
        spike.detail.init(
            {
                spikeId:${spike.spikeId},
                startTime:${spike.startTime.time},
                endTime:${spike.endTime.time}
            }
        );
    });

</script>
</html>
