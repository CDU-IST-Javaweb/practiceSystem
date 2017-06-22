<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Propeller_Admin_Dashboard">
<meta content="width=device-width, initial-scale=1, user-scalable=no"
	name="viewport">

<title>成都大学|信工学院实训实习系统</title>
<link rel="shortcut icon" type="image/x-icon"
	href="../assets/images/favicon1.ico">

<!-- Google icon -->
<!--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">-->

<!-- Bootstrap css -->
<link rel="stylesheet" type="text/css"
	href="../assets/css/bootstrap.min.css">

<!-- Propeller css -->
<link rel="stylesheet" type="text/css"
	href="../assets/css/propeller.min.css">


<!-- Propeller theme css-->
<link rel="stylesheet" type="text/css"
	href="../assets/css/propeller-theme.css" />

<!-- Propeller admin theme css-->
<link rel="stylesheet" type="text/css"
	href="../assets/css/propeller-admin.css">
<!--my style css-->
<link rel="stylesheet" type="text/css" href="../assets/css/style.css">
<!-- Propeller textbox -->
<link href="http://propeller.in/components/textfield/css/textfield.css"
	type="text/css" rel="stylesheet" />

<!-- Propeller table -->
<link href="http://propeller.in/components/table/css/table.css"
	type="text/css" rel="stylesheet" />

<!-- Propeller card -->
<link href="http://propeller.in/components/card/css/card.css"
	type="text/css" rel="stylesheet" />

<!-- Propeller Datatables bootstrap -->
<link
	href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css"
	type="text/css" rel="stylesheet" />

<!-- Propeller Datatables bootstrap responsive  -->
<link
	href="https://cdn.datatables.net/responsive/2.1.0/css/responsive.bootstrap.min.css"
	type="text/css" rel="stylesheet" />

<!-- Propeller Datatables select -->
<link
	href="https://cdn.datatables.net/select/1.2.0/css/select.dataTables.min.css"
	type="text/css" rel="stylesheet" />
<!-- Propeller Datatable -->
<link href="../assets/css/pmd-datatable.css" type="text/css"
	rel="stylesheet" />
<!-- Styles Ends -->
</head>
<script type="text/javascript">
	function check_upload(theform) {
		var filename = document.getElementById("filename").value;
		if (filename == "" || filename == null
				|| (filename.indexOf(".xls") == -1)&&filename.indexOf(".xlsx") == -1) ){
			alert('只能上传.xls或.xlsx文件');
			return false;
		}
	}
</script>

<body>
	<%@include file="test.jsp"%>

	<!--content area start-->
	<div id="content" class="pmd-content inner-page">
		<div
			class="container-fluid full-width-container value-added-detail-page">

			<!-- Title -->
			<h1 class="section-title" id="services">
				<span>实训成绩管理</span>
			</h1>
			<!-- End Title -->
			<h3>1、实训成绩在线查看及修改</h3>

			<form action="RecordingScoreServlet">
				<div class="col-md-4 form-inline">
					<select class="select-simple form-control pmd-select2" name="no"
						style="width: 100%">
						<c:forEach items="${onStartProject }" var="project">
							<option value="${project.no }">${project.name }(方案号:${project.no })</option>
						</c:forEach>

					</select>
				</div>

				<input type="hidden" value="2" name="selectChoiceType">
				<div class="PM-nav">
					<button class="btn pmd-btn-raised pmd-ripple-effect btn-primary"
						type="submit">查询</button>
				</div>
			</form>


			<h3>2、excel表格导入成绩</h3>

			<form action="UpFileToRecordScore" enctype="multipart/form-data"
				method="post" onsubmit="return check_upload(this)">
				<input type="file" id="filename" name="photo" /> <input
					type="submit" value="上传" />
			</form>

			<h2  style="color: red;">注意事项</h2>
			a、excel第一行指明各列属性名,其中projectno、studentno、score属性需在第一行的前10个单元格,不区分大小写;<br>
			b、excel只有第一个工作簿数据有效;<br>
			c、应保证该表中的projectno一样,处理文件时以第二行的projectno为标准,即第一条数据的projectno认为是所有数据的projectno;<br>
			d、系统会对操作文件及时间进行备份;<br>
			e、上传文件后，如果回到该页面,没有跳到错误界面,那么就表示录入成功了,当然,你也可以通过功能1来对单个学生成绩进行修改或查询所有学生成绩是否录入成功.<br>

			<br>
			<div style="color:blue;">
				<c:if test="${requestScope.UpFileToRecordScoreResult!=null }">
			    文件上传、录入成绩结果:${requestScope.UpFileToRecordScoreResult }
			</c:if>
			</div>
		</div>

	</div>


	<!-- Scripts Starts -->
	<script src="../assets/js/jquery-1.12.2.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<!--控制左侧工具栏选择状态-->
	<script>
		$(document).ready(
				function() {
					var sPath = window.location.pathname;
					var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
					$(".pmd-sidebar-nav").each(
							function() {
								$(this).find(
										"a[href='../PracticeManagement/"
												+ sPage + "']").parents(
										".dropdown").addClass("open");
								$(this).find(
										"a[href='../PracticeManagement/"
												+ sPage + "']").parents(
										".dropdown").find('.dropdown-menu')
										.css("display", "block");
								$(this).find(
										"a[href='../PracticeManagement/"
												+ sPage + "']").parents(
										".dropdown").find('a.dropdown-toggle')
										.addClass("active");
								$(this).find(
										"a[href='../PracticeManagement/"
												+ sPage + "']").addClass(
										"active");
							});
				});
	</script>


	<script src="../assets/js/propeller.min.js"></script>

	<!-- Select2 js-->
	<script type="text/javascript"
		src="../components/select2/js/select2.full.js"></script>
	<!-- Datatable js -->
	<script type="text/javascript" language="javascript"
		src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

	<!-- Datatable Bootstrap -->
	<script type="text/javascript" language="javascript"
		src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

	<!-- Datatable responsive js-->
	<script type="text/javascript" language="javascript"
		src="https://cdn.datatables.net/responsive/2.1.0/js/dataTables.responsive.min.js"></script>

	<!-- Datatable select js-->
	<script type="text/javascript" language="javascript"
		src="https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js"></script>
	<!-- Propeller Select2 -->

	<script type="text/javascript"
		src="../components/select2/js/pmd-select2.js"></script>


</body>

</html>