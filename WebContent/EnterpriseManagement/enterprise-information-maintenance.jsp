<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    <!-- Propeller date time picker css-->
    <link rel="stylesheet" type="text/css" href="../components/datetimepicker/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="../components/datetimepicker/css/pmd-datetimepicker.css" />

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
                    <span>企业信息管理</span>
                </h1>
                <!-- End Title -->
                <!--breadcrum start-->
                <ol class="breadcrumb text-left">
                    <li><a href="../Login/index.jsp">主页</a></li>
                    <li class="active">企业信息维护</li>
                </ol>
                <!--breadcrum end-->
            </div>

            <div class="col-md-12">
                <div class="component-box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="pmd-card pmd-z-depth pmd-card-custom-form">
                                <div class="pmd-card-body">
                                <c:choose>
                                	<c:when test="${company.auditDate != null }">
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group">
                                            <h2> <label class="control-label"></label> 基本信息 </h2>
                                        </div>
                                    </div>
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group col-md-6">
                                            <div class="input-group-addon"><label class="control-label col-md-2">企业简介：</label></div>
                                            <input type="text" disabled="disabled" value="${company.profile }" name="profile" class="mat-input form-control">
                                        </div>
                                    </div>
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group col-md-4">
                                            <div class="input-group-addon"><label class="control-label col-md-2">联系人：</label></div>
                                            <input type="text" disabled="disabled" value="${company.contacts}" name="contacts" class="mat-input form-control">
                                        </div>
                                    </div>
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group col-md-6">
                                            <div class="input-group-addon"><label class="control-label col-md-2">公司地址：</label></div>
                                            <input type="text" disabled="disabled" value="${company.address}" name="address" class="mat-input form-control">
                                        </div>
                                    </div>
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group col-md-4">
                                            <div class="input-group-addon"><label class="control-label col-md-2">电话：</label></div>
                                            <input type="text" disabled="disabled" value="${company.phone}" name="phone" class="mat-input form-control">
                                        </div>
                                    </div>
                                	</c:when>
                                	<c:otherwise>
                                	
                                <form action="${pageContext.request.contextPath }/EnterpriseManagement/UpdateCompanyInfos" method="post">
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group">
                                            <h2> <label class="control-label"></label> 基本信息 </h2>
                                        </div>
                                    </div>
                                    <%-- <div class="form-group pmd-textfield">
                                        <div class="input-group col-md-6">
                                            <div class="input-group-addon"><label class="control-label col-md-2">企业简介：</label></div>
                                            <textarea  name="profile" class="form-control">${company.profile }</textarea>
                                        </div>
                                    </div> --%>
                                    <div class="form-group pmd-textfield">
											<label class="control-label col-md-2 arer-lable" >企业简介：</label>
											<textarea name="profile" class="form-control">${company.profile }</textarea>
									</div>
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group col-md-4">
                                            <div class="input-group-addon"><label class="control-label col-md-2">联系人：</label></div>
                                            <input type="text"  value="${company.contacts}" name="contacts" class="mat-input form-control">
                                        </div>
                                    </div>
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group col-md-6">
                                            <div class="input-group-addon"><label class="control-label col-md-2">公司地址：</label></div>
                                            <input type="text"  value="${company.address}" name="address" class="mat-input form-control">
                                        </div>
                                    </div>
                                    <div class="form-group pmd-textfield">
                                        <div class="input-group col-md-4">
                                            <div class="input-group-addon"><label class="control-label col-md-2">电话：</label></div>
                                            <input type="text"  value="${company.phone}" name="phone" class="mat-input form-control">
                                        </div>
                                    </div>
                                    <button data-target="#form-dialog" data-toggle="modal" class="btn pmd-btn-raised pmd-ripple-effect btn-warning pmd-z-depth" type="button">重置密码</button>
                                    <div tabindex="-1" class="modal fade" id="form-dialog" style="display: none;" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header pmd-modal-bordered">
                                                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                                    <h2 class="pmd-card-title-text">修改密码</h2>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="button-group col-md-12">
                                        <input class="btn pmd-ripple-effect btn-primary" type="submit" value="确定"/>
                                        <input data-dismiss="modal" class="btn pmd-ripple-effect btn-primary" type="reset" value="重置"></input>
                                        <a href="javascript:history.back(-1);" data-dismiss="modal" class="btn pmd-ripple-effect btn-default" type="button">返回</a>
                                    </div>
                                    </form>
                                	</c:otherwise>
                                </c:choose>
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
                $(this).find("a[href='../EnterpriseManagement/" + sPage + "']").parents(".dropdown").addClass("open");
                $(this).find("a[href='../EnterpriseManagement/" + sPage + "']").parents(".dropdown").find('.dropdown-menu').css("display", "block");
                $(this).find("a[href='../EnterpriseManagement/" + sPage + "']").parents(".dropdown").find('a.dropdown-toggle').addClass("active");
                $(this).find("a[href='../EnterpriseManagement/" + sPage + "']").addClass("active");
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



</body>

</html>