<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8" charset="utf-8">
	<title>用户管理</title>
	
	<jsp:include page="jqgrid-public.jsp" flush="true"/>
	<jsp:include page="public.jsp" flush="true"/>
    
    <script type="text/javascript" src="../static/js/user.js"></script> 
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>

	<div class="container">
	    <table id="jqGrid" twidth="1170" theight="300"
	    addCaption="新建" updateCaption="编辑" deleteCaption="删除" viewCaption="查看"
	    >
	    </table>
	    <div id="jqGridPager"></div>
	</div>
	<jsp:include page="footer.jsp" flush="true"/> 
	


	<div id="dialog" title="用户信息" dialogWidth="500" dialogHeight="400">
	
	<!-- 面板框 -->
	<div class="panel panel-default">
	    <div class="panel-body">
			<!-- form显示所有信息 -->
		  <form class="form-horizontal" role="form" id="form" 
		  	addAction="add" updateAction="update" deleteAction="delete">
			  <div class="form-group">
			    <label for="firstname" class="col-sm-2 control-label">ID</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="id" readonly="readonly">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="lastname" class="col-sm-2 control-label">用户名<span style="color:red">*</span></label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="username" required="true">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="lastname" class="col-sm-2 control-label">密码<span style="color:red">*</span></label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="password" required="true">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="lastname" class="col-sm-2 control-label">email<span style="color:red">*</span></label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="email" required="true">
			    </div>
			  </div>
			  
			  <div class="hidden">
				  <input type="text" name="insertDate">
				  <input type="text" name="insertBy">
				  <input type="text" name="updateDate">
				  <input type="text" name="updateBy">
			  </div>
			  
			</form>
	    
	    </div>
	    <!-- 
    	<div class="panel-footer">面板脚注</div>
	     -->
	</div>
		
	</div>

</body>
</html>
