<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
                    <span>学生管理</span>
                </h1>
                <!-- End Title -->
                <!--breadcrum start-->
                <ol class="breadcrumb text-left">
                    <li><a href="index.html">主页</a></li>
                    <li class="active">学生个人信息维护</li>
                </ol>
                <!--breadcrum end-->
            </div>

            <div class="col-md-12">
                <div class="component-box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="pmd-card pmd-z-depth pmd-card-custom-form">
                                <div class="pmd-card-body">
                                    <!-- Eric 增加form表单 -->
                                    <form  action="UpdateStudentServlet" method="post">
	                                   <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-4">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">学号：</label></div>
	                                            <input type="text" disabled="" value="${student.no }" class="mat-input form-control">
	                                        </div>
	                                    </div>
	                                    <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-4">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">姓名：</label></div>
	                                            <input type="text" disabled="" value="${student.name }" class="mat-input form-control">
	                                        </div>
	                                    </div>
	                                    <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-4">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">性别：</label></div>
	                                            <input type="text" disabled="" value="${student.gender }" class="mat-input form-control">
	                                        </div>
	                                    </div>
	                                    <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-4">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">入学年份：</label></div>
	                                            <input type="text" disabled="" value="${student.grade }" class="mat-input form-control">
	                                        </div>
	                                    </div>
	                                    <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-4">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">层次：</label></div>
	                                            <input type="text" disabled="" value="${student.level }" class="mat-input form-control">
	                                        </div>
	                                    </div>
	                                    <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-4">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">专业名称：</label></div>
	                                            <input type="text" disabled="" value="${student.professional }" class="mat-input form-control">
	                                        </div>
	                                    </div>
	                                    <!--  邮箱需验证
	                                    <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-4">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">邮箱：</label></div>
	                                            <input type="text" disabled="" value="${student.mailbox }" class="mat-input form-control">
	                                        </div>
	                                    </div>
	                                    -->
	                                    <div class="verification-body">
	                                    	<table>
	                                    	<tr>
	                                    		<td>
					                            <div class="form-group pmd-textfield pmd-textfield-floating-label verification-code-width">
					                                <label class="control-label  arer-lable">密保邮箱地址:</label>
					                                <div class="input-group">
					                                    <div class="input-group-addon"><!--  <i class="material-icons md-dark pmd-sm">email</i>--></div>
					                                    <input type="text" class="form-control" value="${student.mailbox }" name="mbemail" id="forgot-email">
					                                </div>
					                            </div>
					                            <td/>
					                            <td>
					                            <div class="div-email">
					                                <a href="javascript:void(0);" class="send2" >发送验证码</a>
					                            </div>
					                            </td>
				                            </tr>
				                            </table>
				                        </div>
				                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
				                            <label class="control-label  arer-lable" >验证码:</label>
				                            <!-- 于曦添加 -->
				                            <input type="hidden" id="rvchidden" name="rvchidden">
				                            
				                            <div class="input-group">
				                                <div class="input-group-addon"><!--  <i class="material-icons md-dark pmd-sm">comment</i>--></div>
				                                <input type="text" class="form-control" id="exampleInputAmount" name="rvcinAction">
				                            </div>
				                        </div>
	                                    <div class="form-group pmd-textfield col-md-8">
	                                        <label class="control-label  arer-lable">学科背景:</label>
	                                        <textarea required class="form-control" name="background" >${student.subjectBackground }</textarea>
	                                    </div>
	                                    <div class="form-group pmd-textfield col-md-8">
	                                        <label class="control-label  arer-lable">学科经历:</label>
	                                        <textarea required class="form-control" name="experience" >${student.learningExperience }</textarea>
	                                    </div>
	                                    <div class="form-group pmd-textfield col-md-8">
	                                        <label class="control-label  arer-lable">研究方向:</label>
	                                        <textarea required class="form-control" name="direction" >${student.researchDirection }</textarea>
	                                    </div>
	                                    <div class="button-group col-md-8">
	                                        <input type="submit" class="btn pmd-ripple-effect btn-primary" value="确定"></input>
	                                        <input type="reset" class="btn pmd-ripple-effect btn-primary" value="重置"></input>
	                                        <!--  <a href="javascript:history.back(-1);" type="button" class="btn pmd-ripple-effect btn-default"> 返回 </a>-->
	                                    </div>
									</form>
									<form  action="UpdateStudentServlet" method="post">
		                                <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-5">
	                                            <div class="input-group-addon"><label class="control-label col-md-2"><i class="material-icons media-left pmd-md" style="display: inline-block;color: #cdf809;padding-top: 9px;">warning</i><h3 style="color: #f80b03;display: inline-block;">如果需要修改密码请在下方填写原密码和要修改后的密码，否则为空。</h3></label></div>                                           
	                                        </div>
	                                    </div>
	                                    <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-5">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">原密码：</label></div>
	                                            <input name="oldpwd" type="text"  value="" class="mat-input form-control" />
	                                        </div>
	                                    </div>
	                                    
	                                    <div class="form-group pmd-textfield">
	                                        <div class="input-group col-md-5">
	                                            <div class="input-group-addon"><label class="control-label col-md-2">修改后密码：</label></div>
	                                            <input name="newpwd" type="text"  value="" class="mat-input form-control" />
	                                        </div>
	                                    </div>
	                                    <div class="button-group col-md-8">
	                                        <input type="submit" class="btn pmd-ripple-effect btn-primary" value="确定"></input>
	                                    </div> 
									</form>
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
                $(this).find("a[href='../StudentManagement/" + sPage + "']").parents(".dropdown").addClass("open");
                $(this).find("a[href='../StudentManagement/" + sPage + "']").parents(".dropdown").find('.dropdown-menu').css("display", "block");
                $(this).find("a[href='../StudentManagement/" + sPage + "']").parents(".dropdown").find('a.dropdown-toggle').addClass("active");
                $(this).find("a[href='../StudentManagement/" + sPage + "']").addClass("active");
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
    
	<script type="text/javascript">
		$(document).ready(function(){
			$(".send2").click(function(){
// 				获取页面输入的email，将其作为参数传递到后台servlet中进行处理，得到的验证码在页面存放起来。
				var mbemail = $("#forgot-email").val();
				 htmlobj=$.ajax({url:"/practiceSystem/Login/UpdateEmailServlet?mbemail="+mbemail,async:false});
				  $("#rvchidden").val(htmlobj.responseText);
			});
		});
	</script>

</body>

</html>