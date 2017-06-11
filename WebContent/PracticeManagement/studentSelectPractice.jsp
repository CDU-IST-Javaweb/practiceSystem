<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<!-- Select2 css-->
<link rel="stylesheet" type="text/css"
	href="../components/select2/css/select2.min.css" />
<link rel="stylesheet" type="text/css"
	href="../components/select2/css/select2-bootstrap.css" />
<!-- Propeller select2 css-->
<link rel="stylesheet" type="text/css"
	href="../components/select2/css/pmd-select2.css" />
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

<body>
	<%@include file="test.jsp"%>
	<c:forEach items="${selectProjects }" var="selectProject">
		<div tabindex="-1" class="modal fade"
			id="form-dialog-${selectProject.no }" style="display: none;"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header pmd-modal-bordered">
						<button aria-hidden="true" data-dismiss="modal" class="close"
							type="button">×</button>
						<h2 class="pmd-card-title-text">选择方案</h2>
					</div>
					<div class="modal-body">
						<form class="form-horizontal"
							action="StudentChoicePracticeServlet" method="post">
							<div
								class="form-group pmd-textfield pmd-textfield-floating-label">
								<label for="first-name">方案号：</label> <input type="text"
									readonly="" class="mat-input form-control"
									value="${selectProject.no }" name="no">
								<div
									class="form-group pmd-textfield pmd-textfield-floating-label">
									<label for="first-name">方案名称：</label> <input type="text"
										readonly="" class="mat-input form-control"
										value="${selectProject.name }" name="name">
								</div>
								<div
									class="form-group pmd-textfield pmd-textfield-floating-label">
									<label class="control-label">选题理由</label>
									<textarea required class="form-control" name="reason"></textarea>
									<span class="help-text">选题理由不能为空</span>
								</div>
							</div>
							<div class="pmd-modal-action">
								<button class="btn pmd-ripple-effect btn-primary" type="submit">确定</button>
								<a href="javascript:history.back(-1);" data-dismiss="modal"
									class="btn pmd-ripple-effect btn-default" type="button">返回</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<!--content area start-->
	<div id="content" class="pmd-content inner-page">
		<!--tab start-->
		<div
			class="container-fluid full-width-container value-added-detail-page">
			<div>
				<div class="pull-right table-title-top-action">
					<div class="pmd-textfield pull-left">
						<input type="text" id="exampleInputAmount" class="form-control"
							placeholder="关于...">
					</div>
					<a href="javascript:void(0);"
						class="btn btn-primary pmd-btn-raised add-btn pmd-ripple-effect pull-left">搜索</a>
				</div>
				<!-- Title -->
				<h1 class="section-title" id="services">
					<span>实训方案管理</span>
				</h1>
				<!-- End Title -->
				<!--breadcrum start-->
				<ol class="breadcrumb text-left">
					<li><a href="../Login/index.jsp">主页</a></li>
					<li class="active">学生选择方案</li>
				</ol>
				<!--breadcrum end-->
			</div>

			<div class="col-md-12">
				<!-- responsive table example -->
				<div class="pmd-card pmd-z-depth pmd-card-custom-view">
					<h2 style="text-align: center;">学生选实训方案</h2>
					<!--<form class="col-md-12">
                  <label class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
                    <input type="radio" name="program" value="" >
                    <span>所有方案</span>
                  </label>
                  <label class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
                    <input type="radio" name="program"value="">
                    <span>我的方案</span>
                  </label>
                </form>-->
					<div class="col-md-3 form-inline">
						<select class="select-simple form-control pmd-select2">
							<option>所有方案</option>
							<option>我的方案</option>
						</select>
					</div>

					<table id="example"
						class="table pmd-table table-hover table-striped display responsive nowrap"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>方案号</th>
								<th>方案名称</th>
								<th>方案简介</th>
								<th>学生人数</th>
								<th>校外指导老师</th>
								<th>校外指导老师职称</th>
								<th>类别</th>
								<th>年级</th>
								<th>发布日期</th>
								<th>企业名称</th>
								<th>审核状态</th>
								<th>选择状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${selectProjects }" var="selectProject">
								<tr>
									<td>${selectProject.no }</td>
									<td>${selectProject.name }</td>
									<td title="${selectProject.introduction }"><c:if
											test="${selectProject.introduction.length()>30 }">
											${selectProject.introduction.substring(0,30) }...
											</c:if> <c:if test="${selectProject.introduction.length()<=30 }">
											${selectProject.introduction }</c:if></td>
									<td>${selectProject.studentsNum }</td>
									<td>${selectProject.companyTeacher }</td>
									<td>${selectProject.companyTeacherTitle }</td>
									<td>${selectProject.category }</td>
									<td>${selectProject.grade }</td>
									<td>${selectProject.releaseDate }</td>
									<!--  <td>${selectProject.companyUsername }</td>-->
									<td>${companyInfo[selectProject.no].companyName }</td>
									<td><c:if test="${stuProjectNo.equals(selectProject.no) }">
											<button type="button"
												class="btn pmd-btn-outline pmd-ripple-effect">已审核</button>
										</c:if> <c:if
											test="${!stuProjectNo.equals(selectProject.no)&&choiceState[selectProject.no]==1 }">
											<button type="button"
												class="btn pmd-btn-outline pmd-ripple-effect btn-danger">未审核</button>
										</c:if> <c:if
											test="${choiceState[selectProject.no]==0||choiceState[selectProject.no]==null }">
											-
										</c:if></td>
									<td><c:if test="${choiceState[selectProject.no]==1 }">
											<button type="button"
												class="btn pmd-btn-outline pmd-ripple-effect btn-success">已选</button>
										</c:if> <c:if
											test="${choiceState[selectProject.no]==0||choiceState[selectProject.no]==null }">
											<button type="button"
												class="btn pmd-btn-outline pmd-ripple-effect btn-danger">未选</button>
										</c:if></td>
									<td><a
										class="btn pmd-btn-raised pmd-ripple-effect btn-primary"
										href="InfoPracticeServlet?no=${selectProject.no }"> 详情</a> <c:if
											test="${PracticeIsUnderWay }">
											<c:if test="${choiceState[selectProject.no]==1 }">
												<a type="button"
													href="StudentChoicePracticeServlet?no=${selectProject.no }"
													class="btn pmd-btn-raised pmd-ripple-effect btn-danger pmd-z-depth">
													退选 </a>
											</c:if>
											<c:if
												test="${choiceState[selectProject.no]==0||choiceState[selectProject.no]==null }">
												<a type="button"
													data-target="#form-dialog-${selectProject.no }"
													data-toggle="modal"
													class="btn pmd-btn-raised pmd-ripple-effect btn-success pmd-z-depth">
													选择 </a>
											</c:if>
										</c:if></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<!-- responsive table example end -->

			</div>


		</div>
	</div>

	<!-- Footer Starts -->
	<!--footer start-->
	<footer class="admin-footer">
	<div class="container-fluid">
		<ul class="list-unstyled list-inline">
			<li><span class="pmd-card-subtitle-text">信息科学与工程学院 &copy;
					2017. 版权所有.</span>
				<h3 class="pmd-card-subtitle-text">
					技术支持 BY <a href="http://computer.cdu.edu.cn/" target="_blank">信工学院.</a>
				</h3></li>
			<li class="pull-right download-now"><a
				onClick="downloadPMDadmintemplate()" href="javascript：void(0);">
					<div>
						<i class="material-icons media-left pmd-sm">settings</i>
					</div>
					<div>
						<span class="pmd-card-subtitle-text">Version- 1.0.0</span>
						<h3 class="pmd-card-title-text">By cduyzh</h3>
					</div>
			</a></li>
			<li class="pull-right for-support"><a
				href="mailto:support@propeller.in">
					<div>
						<i class="material-icons media-left pmd-sm">email</i>
					</div>
					<div>
						<span class="pmd-card-subtitle-text">For Support</span>
						<h3 class="pmd-card-title-text">450311265@qq.com</h3>
					</div>
			</a></li>
		</ul>
	</div>
	</footer>
	<!--footer end-->
	<!-- Footer Ends -->

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
	<script type="text/javascript">
		(function() {
			"use strict";
			var toggles = document.querySelectorAll(".c-hamburger");
			for (var i = toggles.length - 1; i >= 0; i--) {
				var toggle = toggles[i];
				toggleHandler(toggle);
			}
			;

			function toggleHandler(toggle) {
				toggle
						.addEventListener(
								"click",
								function(e) {
									e.preventDefault();
									(this.classList.contains("is-active") === true) ? this.classList
											.remove("is-active")
											: this.classList.add("is-active");
								});
			}

		})();
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
	<script type="text/javascript">
		$(document).ready(function() {

			$(".select-simple").select2({
				theme : "bootstrap",
				minimumResultsForSearch : Infinity,
			});

			$(".select-with-search").select2({
				theme : "bootstrap"
			});

			$(".select-tags").select2({
				tags : false,
				theme : "bootstrap",
			});

			$(".select-add-tags").select2({
				tags : true,
				theme : "bootstrap",
			});
		});
	</script>
	<script type="text/javascript"
		src="../components/select2/js/pmd-select2.js"></script>
	<script>
		//Propeller  Customised Javascript code 
		$(document)
				.ready(
						function() {
							var exampleDatatable = $('#example')
									.DataTable(
											{
												responsive : {
													details : {
														type : 'column',
														target : 'tr'
													}
												},
												columnDefs : [ {
													className : 'control',
													orderable : false,
													targets : 1
												} ],
												order : [ 1, 'asc' ],
												bFilter : true,
												bLengthChange : true,
												pagingType : "simple",
												"paging" : true,
												"searching" : true,
												"language" : {
													"info" : " _START_ - _END_ of _TOTAL_ ",
													"sLengthMenu" : "<span class='custom-select-title'>每页显示行数:</span> <span class='custom-select'> _MENU_ </span>",
													"sSearch" : "",
													"sSearchPlaceholder" : "搜索",
													"paginate" : {
														"sNext" : " ",
														"sPrevious" : " "
													},
												},
												dom : "<'pmd-card-title'<'data-table-title-responsive'><'search-paper pmd-textfield'f>>"
														+ "<'row'<'col-sm-12'tr>>"
														+ "<'pmd-card-footer' <'pmd-datatable-pagination' l i p>>",
											});

							/// Select value
							$('.custom-select-info').hide();

							$("div.data-table-title")
									.html(
											'<h2 class="pmd-card-title-text">Table Card</h2>');
							$("div.data-table-title")
									.html(
											'<h2 class="pmd-card-title-text">Table Responsive</h2>');
							$(".custom-select-action")
									.html(
											'<button class="btn btn-sm pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-primary" type="button"><i class="material-icons pmd-sm">delete</i></button><button class="btn btn-sm pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-primary" type="button"><i class="material-icons pmd-sm">more_vert</i></button>');

						});
	</script>
</body>

</html>