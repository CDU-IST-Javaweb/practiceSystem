<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header Starts -->
<!--Start Nav bar -->
<!--顶部-->
<nav
	class="navbar navbar-inverse navbar-fixed-top pmd-navbar pmd-z-depth">

	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<!--左侧滑-->
		<div class="navbar-header">
			<a href="javascript:void(0);"
				class="btn btn-sm pmd-btn-fab pmd-btn-flat pmd-ripple-effect pull-left margin-r8 pmd-sidebar-toggle"><i
				class="material-icons">menu</i></a> <a href="../index.jsp"
				class="navbar-brand"> 成都大学信工学院|实训实习系统 </a>
		</div>
	</div>

</nav>
<!--End Nav bar -->
<!-- Header Ends -->

<!-- Sidebar Starts -->
<div class="pmd-sidebar-overlay"></div>

<!-- Left sidebar -->
<!--左侧工具栏菜单-->
<aside
	class="pmd-sidebar sidebar-default pmd-sidebar-slide-push pmd-sidebar-left pmd-sidebar-open bg-fill-darkblue sidebar-with-icons"
	role="navigation">
	<ul class="nav pmd-sidebar-nav">

		<!-- User info -->
		<li
			class="dropdown pmd-dropdown pmd-user-info visible-xs visible-md visible-sm visible-lg">
			<a aria-expanded="false" data-toggle="dropdown"
			class="btn-user dropdown-toggle media" data-sidebar="true"
			aria-expandedhref="javascript:void(0);">
				<div class="media-left">
					<img src="../assets/images/user-icon.png" alt="New User">
				</div>
				<div class="media-body media-middle">${account }</div>
				<div class="media-right media-middle">
					<i class="dic-more-vert dic"></i>
				</div>
		</a>
			<ul class="dropdown-menu">
				<li><a href="../Login/SignOutServlet">注销</a></li>
			</ul>
		</li>
		<!-- End user info -->

		<li><a class="pmd-ripple-effect" href="../Login/index.jsp"> <i
				class="material-icons media-left pmd-sm">home</i> <span
				class="media-body">主页</span>
		</a></li>
		<!--实训方案管理-->
		<li class="dropdown pmd-dropdown"><a aria-expanded="false"
			data-toggle="dropdown" class="btn-user dropdown-toggle media"
			data-sidebar="true" href="javascript:void(0);"> <i
				class="material-icons media-left pmd-sm">list</i> <span
				class="media-body">实训方案管理</span>
				<div class="media-right media-bottom">
					<i class="dic-more-vert dic"></i>
				</div>
		</a>
			<ul class="dropdown-menu">
				<c:if test="${role.equals(\"1\")||role.equals(\"9\") }">
					<li><a
						href="../PracticeManagement/SelectPracticeServlet?selectProjectType=1">方案管理</a></li>
				</c:if>
				<c:if test="${role.equals(\"1\") }">
					<li><a
						href="../PracticeManagement/ChoicePracticeInfoServlet?selectChoiceType=1">企业管理学生</a></li>
				</c:if>
				<c:if test="${role.equals(\"2\") }">
					<li><a
						href="../PracticeManagement/StudentSelectPracticeServlet">学生选择方案</a></li>
				</c:if>
			</ul></li>
		<!--企业信息管理-->
		<c:if test="${role.equals(\"1\")||role.equals(\"9\") }">
			<li class="dropdown pmd-dropdown"><a aria-expanded="false"
				data-toggle="dropdown" class="btn-user dropdown-toggle media"
				data-sidebar="true" href="javascript:void(0);"> <i
					class="material-icons media-left pmd-sm">list</i> <span
					class="media-body">企业信息管理</span>
					<div class="media-right media-bottom">
						<i class="dic-more-vert dic"></i>
					</div>
			</a>
				<ul class="dropdown-menu">
					<c:if test="${role.equals(\"9\") }">
						<li><a href="../EnterpriseManagement/ShowCompanyssServlet">企业信息管理</a></li>
					</c:if>
					<c:if test="${role.equals(\"1\") }">
						<li><a href="../EnterpriseManagement/ShowsCompanyServlet">企业信息维护</a></li>
					</c:if>
				</ul></li>
		</c:if>
		<!--学生管理-->
		<c:if test="${!role.equals(\"1\") }">
			<li class="dropdown pmd-dropdown"><a aria-expanded="false"
				data-toggle="dropdown" class="btn-user dropdown-toggle media"
				data-sidebar="true" href="javascript:void(0);"> <i
					class="material-icons media-left pmd-sm">list</i> <span
					class="media-body">学生管理</span>
					<div class="media-right media-bottom">
						<i class="dic-more-vert dic"></i>
					</div>
			</a>
				<ul class="dropdown-menu">
					<c:if test="${role.equals(\"9\") }">
						<li><a href="../StudentManagement/QueryStudentServlet">学生管理</a></li>
					</c:if>
					<c:if test="${role.equals(\"2\") }">
						<li><a href="../StudentManagement/UpdateStudentServlet">学生个人信息维护</a></li>
					</c:if>
				</ul></li>
		</c:if>
		<!--通知公告管理-->
		<c:if test="${role.equals(\"1\")||role.equals(\"9\") }">
			<li class="dropdown pmd-dropdown"><a aria-expanded="false"
				data-toggle="dropdown" class="btn-user dropdown-toggle media"
				data-sidebar="true" href="javascript:void(0);"> <i
					class="material-icons media-left pmd-sm">list</i> <span
					class="media-body">通知公告管理</span>
					<div class="media-right media-bottom">
						<i class="dic-more-vert dic"></i>
					</div>
			</a>
				<ul class="dropdown-menu">
					<c:if test="${role.equals(\"1\") }">
						<li><a href="../SystemsManagement/ShowNoticeListServlet">发布通知公告</a></li>
					</c:if>
					<c:if test="${role.equals(\"9\") }">
						<li><a href="../SystemsManagement/ShowAdminNotices">学院通知公告</a></li>
						<li><a href="../SystemsManagement/AdminLookNoticesServlet">审核通知公告</a></li>
					</c:if>
				</ul></li>
		</c:if>
		<!--系统配置-->
		<c:if test="${role.equals(\"9\") }">
			<li class="dropdown pmd-dropdown"><a aria-expanded="false"
				data-toggle="dropdown" class="btn-user dropdown-toggle media"
				data-sidebar="true" href="javascript:void(0);"> <i
					class="material-icons media-left pmd-sm">settings</i> <span
					class="media-body">系统配置</span>
					<div class="media-right media-bottom">
						<i class="dic-more-vert dic"></i>
					</div>
			</a>
				<ul class="dropdown-menu">
					<li><a href="../SystemsManagement/SelectSystemConfigServlet">系统参数配置</a></li>
				</ul></li>
		</c:if>
		<!--登出-->
		<li><a class="pmd-ripple-effect" href="../Login/SignOutServlet"> <i
				class="media-left media-middle"> <svg version="1.1" id="Layer_1"
						x="0px" y="0px" width="18px" height="18px"
						viewBox="288.64 337.535 18 18"
						enable-background="new 288.64 337.535 18 18" xml:space="preserve">
				<path fill="#C9C8C8"
							d="M295.39,337.535v2.25h9v13.5h-9v2.25h11.25v-18H295.39z M297.64,342.035v3.375h-9v2.25h9v3.375l3.375-3.375
					l1.125-1.125l-1.125-1.125L297.64,342.035z" />
				</svg></i> <span class="media-body">注销登录</span>
		</a></li>
		<!--提醒事项-->
		<!-- <li><a class="pmd-ripple-effect"
			href="../SystemsManagement/notifications.html"> <i
				class="media-left media-middle"> <svg version="1.1" id="Layer_1"
						x="0px" y="0px" width="15.3px" height="18px"
						viewBox="289.99 337.535 15.3 18"
						enable-background="new 289.99 337.535 15.3 18"
						xml:space="preserve">
					<g>
						<g>
							<path fill="#C9C8C8"
							d="M297.64,355.535c0.99,0,1.8-0.81,1.8-1.8h-3.6C295.84,354.725,296.65,355.535,297.64,355.535z
								 M303.49,350.135v-4.95c0-2.79-1.891-5.041-4.501-5.67v-0.63c0-0.72-0.63-1.35-1.35-1.35c-0.72,0-1.35,0.63-1.35,1.35v0.63
								c-2.61,0.629-4.5,2.88-4.5,5.67v4.95l-1.8,1.8v0.9h15.3v-0.9L303.49,350.135z" />
						</g>
					</g>
				</svg></i> <span class="media-body">提醒事项</span>
		</a></li> -->

	</ul>
</aside>
<!-- End Left sidebar -->
<!-- Sidebar Ends -->

