<%@page import="java.util.ArrayList"%>
<%@page import="cn.edu.cdu.practice.service.impl.ProjectServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
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
<!-- Styles Ends -->
</head>

<body>
	<%@include file="test.jsp"%>
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
					<li class="active">方案管理</li>
				</ol>
				<!--breadcrum end-->
			</div>
			<div class="col-md-12">
				<div class="component-box">
					<!-- input states example -->
					<div class="row">
						<div class="col-md-12">
							<div class="pmd-card pmd-z-depth pmd-card-custom-form">
								<div class="pmd-card-body">
									<h2>修改实训方案</h2>
									<form action="UpdatePracticeServlet" method="post">
										<div class="form-group pmd-textfield">
											<div class="input-group col-md-6">
												<div class="input-group-addon">
													<label class="control-label col-md-2">企业用户名</label>
												</div>
												<input type="text" class="mat-input form-control"
													disabled="" value="${company.companyName }">
											</div>
										</div>
										<div class="form-group pmd-textfield">
											<div class="input-group col-md-6">
												<div class="input-group-addon">
													<label class="control-label col-md-2">方案号</label>
												</div>
												<input type="text" class="mat-input form-control"
													disabled="" value="${updateProjectNo }"> <input
													type="hidden" value="${updateProjectNo }" name="no">
											</div>
										</div>


										<div class="form-group pmd-textfield ">
											<div class="input-group col-md-6">
												<div class="input-group-addon">
													<label class="control-label col-md-2">方案名称</label>
												</div>
												<input type="text" value="${updateProjectInfo.name }"
													class="mat-input form-control " name="name">
											</div>
										</div>
										<div class="form-group pmd-textfield">
											<label class="control-label col-md-2 arer-lable">方案简介</label>
											<textarea required class="form-control" name="introduction">${updateProjectInfo.introduction }</textarea>
										</div>
										<div class="form-group pmd-textfield">
											<label class="control-label col-md-1">适合专业</label>
											<%
												ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
												ArrayList<String> professionals = projectServiceImpl.findAllProfessional();
											%>
											<c:forEach items="<%=professionals%>" var="professional">
												<label
													class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
													<c:if test="${majorInfo.get(professional)==1 }">
														<input type="checkbox" value="${professional }" name="major"
															checked="checked">
													</c:if> <c:if test="${majorInfo.get(professional)!=1 }">
														<input type="checkbox" value="${professional }" name="major">
													</c:if> <span>${professional }</span>
												</label>
											</c:forEach>

											<!-- 
											 <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"信息与计算科学(本)\")==1 }">
													<input type="checkbox" value="信息与计算科学(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"信息与计算科学(本)\")!=1 }">
													<input type="checkbox" value="信息与计算科学(本)" name="major">
												</c:if> <span> 信息与计算科学(本)</span>
											</label> <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"数字媒体技术(本)\")==1 }">
													<input type="checkbox" value="数字媒体技术(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"数字媒体技术(本)\")!=1 }">
													<input type="checkbox" value="数字媒体技术(本)" name="major">
												</c:if> <span> 数字媒体技术(本)</span>
											</label> <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"电子信息工程(本)\")==1 }">
													<input type="checkbox" value="电子信息工程(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"电子信息工程(本)\")!=1 }">
													<input type="checkbox" value="电子信息工程(本)" name="major">
												</c:if> <span> 电子信息工程(本)</span>
											</label> <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"电气工程及其自动化(本)\")==1 }">
													<input type="checkbox" value="电气工程及其自动化(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"电气工程及其自动化(本)\")!=1 }">
													<input type="checkbox" value="电气工程及其自动化(本)" name="major">
												</c:if> <span> 电气工程及其自动化(本)</span>
											</label> <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"网络工程(本)\")==1 }">
													<input type="checkbox" value="网络工程(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"网络工程(本)\")!=1 }">
													<input type="checkbox" value="网络工程(本)" name="major">
												</c:if> 网络工程(本)</span>
											</label> <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"自动化(本)\")==1 }">
													<input type="checkbox" value="自动化(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"自动化(本)\")!=1 }">
													<input type="checkbox" value="自动化(本)" name="major">
												</c:if><span> 自动化(本)</span>
											</label> <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"计算机科学与技术(本)\")==1 }">
													<input type="checkbox" value="计算机科学与技术(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"计算机科学与技术(本)\")!=1 }">
													<input type="checkbox" value="计算机科学与技术(本)" name="major">
												</c:if> <span> 计算机科学与技术(本)</span>
											</label> <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"软件工程(本)\")==1 }">
													<input type="checkbox" value="软件工程(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"软件工程(本)\")!=1 }">
													<input type="checkbox" value="软件工程(本)" name="major">
												</c:if> <span> 软件工程(本)</span>
											</label> <label
												class="checkbox-inline pmd-checkbox pmd-checkbox-ripple-effect">
												<c:if test="${majorInfo.get(\"通信工程(本)\")==1 }">
													<input type="checkbox" value="通信工程(本)" name="major"
														checked="checked">
												</c:if> <c:if test="${majorInfo.get(\"通信工程(本)\")!=1 }">
													<input type="checkbox" value="通信工程(本)" name="major">
												</c:if><span> 通信工程(本)</span>
											</label> -->
										</div>
										<div class="form-group pmd-textfield ">
											<div class="input-group col-md-4">
												<div class="input-group-addon">
													<label class="control-label col-md-2">学生人数</label>
												</div>
												<input type="text" value="${updateProjectInfo.studentsNum }"
													class="mat-input form-control " name="students_num">
											</div>
										</div>
										<div class="form-group pmd-textfield">
											<div class="input-group col-md-4">
												<div class="input-group-addon">
													<label class="control-label col-md-2">类别</label>
												</div>
												<select class="select-with-search form-control pmd-select2"
													name="category">
													<c:if
														test="${updateProjectInfo.category.equals(\"技能实训\") }">
														<option value="技能实训" selected="selected">技能实训</option>
														<option value="概念实训">概念实训</option>
														<option value="综合实训">综合实训</option>
													</c:if>
													<c:if
														test="${updateProjectInfo.category.equals(\"概念实训\") }">
														<option value="技能实训">技能实训</option>
														<option value="概念实训" selected="selected">概念实训</option>
														<option value="综合实训">综合实训</option>
													</c:if>
													<c:if
														test="${updateProjectInfo.category.equals(\"综合实训\") }">
														<option value="技能实训">技能实训</option>
														<option value="概念实训">概念实训</option>
														<option value="综合实训" selected="selected">综合实训</option>
													</c:if>
												</select>
											</div>
										</div>
										<!--Simple Select with Search-->
										<div class="form-group pmd-textfield">
											<div class="input-group col-md-4">
												<div class="input-group-addon">
													<label class="control-label col-md-2">年级</label>
												</div>
												<select class="select-with-search form-control pmd-select2"
													name="grade">
													<c:if test="${gradeFlag==1}">
														<option value="1" selected="selected">大一</option>
														<option value="2">大二</option>
														<option value="3">大三</option>
														<option value="4">大四</option>
													</c:if>

													<c:if test="${gradeFlag==2}">
														<option value="1">大一</option>
														<option value="2" selected="selected">大二</option>
														<option value="3">大三</option>
														<option value="4">大四</option>
													</c:if>

													<c:if test="${gradeFlag==3}">
														<option value="1">大一</option>
														<option value="2">大二</option>
														<option value="3" selected="selected">大三</option>
														<option value="4">大四</option>
													</c:if>

													<c:if test="${gradeFlag==4}">
														<option value="1">大一</option>
														<option value="2">大二</option>
														<option value="3">大三</option>
														<option value="4" selected="selected">大四</option>
													</c:if>

												</select>
											</div>
										</div>
										<div class="form-group pmd-textfield ">
											<div class="input-group col-md-4">
												<div class="input-group-addon">
													<label class="control-label col-md-2">校外指导老师</label>
												</div>
												<input type="text"
													value="${updateProjectInfo.companyTeacher }"
													class="mat-input form-control " name="company_teacher">
											</div>
										</div>
										<div class="form-group pmd-textfield">
											<div class="input-group col-md-4">
												<div class="input-group-addon">
													<label class="control-label col-md-2">校外指导老师 职称</label>
												</div>
												<input type="text"
													value="${updateProjectInfo.companyTeacherTitle }"
													class="mat-input form-control "
													name="company_teacher_title">
											</div>
										</div>
										<div class="button-group">
											<button type="submit"
												class="btn pmd-ripple-effect btn-primary">确定</button>
											<a href="javascript:history.back(-1);" data-dismiss="modal"
												class="btn pmd-ripple-effect btn-default" type="button">返回</a>
										</div>

									</form>
								</div>
							</div>
						</div>

					</div>
					<!-- input states example end -->
				</div>
			</div>


		</div>
	</div>

	<!-- Footer Starts -->
	<!--footer start-->
	<footer class="admin-footer">
		<div class="container-fluid">
			<ul class="list-unstyled list-inline">
				<li><span class="pmd-card-subtitle-text">信息科学与工程学院
						&copy; 2017. 版权所有.</span>
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

</body>

</html>