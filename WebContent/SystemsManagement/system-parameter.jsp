<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Propeller_Admin_Dashboard">
    <meta content="width=device-width, initial-scale=1, user-scalable=no" name="viewport">

    <title>成都大学|信工学院实训实习系统</title>
    <link rel="shortcut icon" type="image/x-icon" href="../assets/images/favicon1.ico">

    <!-- Google icon -->
    <!--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">-->

    <!-- Bootstrap css -->
    <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css">

    <!-- Propeller css -->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller.min.css">


    <!-- Propeller theme css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller-theme.css" />
    <link rel="stylesheet" type="text/css" href="../assets/css/jquery.datetimepicker.css">

    <!-- Propeller admin theme css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller-admin.css">
    <!--my style css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
    <!-- Styles Ends -->
</head>

<body>
<%@include file="../PracticeManagement/test.jsp" %>

    <!--content area start-->
    <div id="content" class="pmd-content inner-page">
        <!--tab start-->
        <div class="container-fluid full-width-container value-added-detail-page">
            <div>
                <div class="pull-right table-title-top-action">
                    <div class="pmd-textfield pull-left">
                        <input type="text" id="exampleInputAmount" class="form-control" placeholder="关于...">
                    </div>
                    <a href="javascript:void(0);" class="btn btn-primary pmd-btn-raised add-btn pmd-ripple-effect pull-left">搜索</a>
                </div>
                <!-- Title -->
                <h1 class="section-title" id="services">
                    <span>系统配置</span>
                </h1>
                <!-- End Title -->
                <!--breadcrum start-->
                <ol class="breadcrumb text-left">
                    <li><a href="../Login/index.jsp">主页</a></li>
                    <li class="active">系统参数配置</li>
                </ol>
                <!--breadcrum end-->
            </div>
            <div class="col-md-12">
                <div class="component-box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="pmd-card pmd-z-depth">
                                <div class="pmd-card-body pmd-card-custom-form">
                                    <div class="table-responsive">
                                    <form action = "MyConfigServlet" method="post">
                                        <table class="table pmd-table table-bordered">
                                                <%-- <tr>
                                                    <td><input type="hidden" name="account" value="${sys.adminUsername }"></td>
                                                </tr> --%>
                                                <tr>
                                                	<th>管理员密码</th>
                                                	<td><input type="password" name="pwd" value=""/></td>
                                                </tr>
                                                <tr>
                                                <th>邀请码</th>
                                                <td><input type="text" name="code" value="${sys.invitationCode }"></td>
                                                </tr>
                                                <tr>
                                                <th>企业发布方案开始日期</th>
                                                <td><input type="text" name="releaseProjectStartDate" value="${sys.releaseProjectStartDate }"></td>
                                                </tr>
                                                <tr>
                                                <th>企业发布方案截至日期</th>
                                                 <td><input type="text" name="releaseProjectEndDate" value="${sys.releaseProjectEndDate }"></td>
                                                </tr>
                                                <tr>
                                                <th>学生选择案开始日期</th>
                                                 <td><input type="text" name="studentSelStartDate" value="${sys.studentSelStartDate }"></td>
                                                </tr>
                                                <tr>
                                                <th>学生选择案截至日期</th>
                                                 <td><input type="text" name="studentSelEndDate" value="${sys.studentSelEndDate }"></td>
                                                </tr>
                                                <tr> 
                                                <th>学生最多待选方案数量</th>
                                                 <td><input type="text" name="studentSelMaxnum" value="${sys.studentSelMaxnum }"></td>
                                                </tr>
                                                <tr>
                                                	<th>操作选项</th>
                                                	<td>
                                                		<input type="submit"  value="提交">
                                                		<input type="reset"  value="重置">
                                                	</td>
                                                </tr>
                                        </table>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer Starts -->
    <!--footer start-->
    <footer class="admin-footer">
        <div class="container-fluid">
            <ul class="list-unstyled list-inline">
                <li>
                    <span class="pmd-card-subtitle-text">信息科学与工程学院 &copy; 2017. 版权所有.</span>
                    <h3 class="pmd-card-subtitle-text">技术支持 BY <a href="http://computer.cdu.edu.cn/" target="_blank">信工学院.</a></h3>
                </li>
                <li class="pull-right download-now">
                    <a onClick="downloadPMDadmintemplate()" href="javascript：void(0);">
                        <div>
                            <i class="material-icons media-left pmd-sm">settings</i>
                        </div>
                        <div>
                            <span class="pmd-card-subtitle-text">Version- 1.0.0</span>
                            <h3 class="pmd-card-title-text">By cduyzh</h3>
                        </div>
                    </a>
                </li>
                <li class="pull-right for-support">
                    <a href="mailto:support@propeller.in">
                        <div>
                            <i class="material-icons media-left pmd-sm">email</i>
                        </div>
                        <div>
                            <span class="pmd-card-subtitle-text">For Support</span>
                            <h3 class="pmd-card-title-text">450311265@qq.com</h3>
                        </div>
                    </a>
                </li>
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
        $(document).ready(function() {
            var sPath = window.location.pathname;
            var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
            $(".pmd-sidebar-nav").each(function() {
                $(this).find("a[href='../SystemsManagement/" + sPage + "']").parents(".dropdown").addClass("open");
                $(this).find("a[href='../SystemsManagement/" + sPage + "']").parents(".dropdown").find('.dropdown-menu').css("display", "block");
                $(this).find("a[href='../SystemsManagement/" + sPage + "']").parents(".dropdown").find('a.dropdown-toggle').addClass("active");
                $(this).find("a[href='../SystemsManagement/" + sPage + "']").addClass("active");
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
            };

            function toggleHandler(toggle) {
                toggle.addEventListener("click", function(e) {
                    e.preventDefault();
                    (this.classList.contains("is-active") === true) ? this.classList.remove("is-active"): this.classList.add("is-active");
                });
            }

        })();
    </script>

    <script src="../assets/js/propeller.min.js"></script>
  <script src="../assets/js/jquery.datetimepicker.full.js"></script>
	<script>
  $('#datetimepicker').datetimepicker({
      dayOfWeekStart : 1,
      lang:'en',
      disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
      startDate:	'1986/01/05'
  });
  $('#datetimepicker').datetimepicker({value:'2015/04/15 05:03',step:10});

  </script>
</body>

</html>