<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">

	<title>用户管理</title>
	
   	<script type="text/javascript" src="../static/js/jquery/jquery-1.10.0-min.js"></script>
   	
    <script type="text/javascript" src="../static/js/jqgrid/i18n/grid.locale-cn.js"></script>

    <script type="text/javascript" src="../static/js/jqgrid/jquery.jqGrid.js"></script>
    
    <link rel="stylesheet" type="text/css" media="screen" href="../static/js/jqgrid/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="../static/css/ui.jqgrid/ui.jqgrid-bootstrap.css" />

    
   	<script>
	$.jgrid.defaults.width = 780;
	$.jgrid.defaults.responsive = true;
	$.jgrid.defaults.styleUI = 'Bootstrap';
	</script>
	
    <script type="text/javascript" src="../static/js/jqgrid/bootstrap.min.js"></script>

	<!-- 
	<link rel="stylesheet" href="../static/css/bootstrap/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" href="../static/css/bootstrap/css/bootstrap-theme.min.css" type="text/css" />
	<script type="text/javascript" src="../static/css/bootstrap/js/bootstrap.min.js"></script>
	 -->
    
    <script type="text/ecmascript" src="../static/js/user.js"></script> 
</head>
<body>



    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
    		
</body>
</html>