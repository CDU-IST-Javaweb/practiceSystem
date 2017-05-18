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


    <!-- Propeller theme css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller-theme.css" />

    <!-- Propeller admin theme css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller-admin.css">
    <!--my style css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
    <!-- Propeller textbox -->
    <link href="http://propeller.in/components/textfield/css/textfield.css" type="text/css" rel="stylesheet" />

    <!-- Propeller table -->
    <link href="http://propeller.in/components/table/css/table.css" type="text/css" rel="stylesheet" />

    <!-- Propeller card -->
    <link href="http://propeller.in/components/card/css/card.css" type="text/css" rel="stylesheet" />

    <!-- Propeller Datatables bootstrap -->
    <link href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" type="text/css" rel="stylesheet" />

    <!-- Propeller Datatables bootstrap responsive  -->
    <link href="https://cdn.datatables.net/responsive/2.1.0/css/responsive.bootstrap.min.css" type="text/css" rel="stylesheet" />

    <!-- Propeller Datatables select -->
    <link href="https://cdn.datatables.net/select/1.2.0/css/select.dataTables.min.css" type="text/css" rel="stylesheet" />
    <!-- Propeller Datatable -->
    <link href="../assets/css/pmd-datatable.css" type="text/css" rel="stylesheet" />
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
                    <span>通知公告管理</span>
                </h1>
                <!-- End Title -->
                <!--breadcrum start-->
                <ol class="breadcrumb text-left">
                    <li><a href="../Login/index.jsp">主页</a></li>
                    <li class="active">审核企业通知公告</li>
                </ol>
                <!--breadcrum end-->
            </div>

            <div class="col-md-12">
                <div class="component-box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="pmd-card pmd-z-depth">
                                <div class="pmd-card-body  pmd-card-custom-form">
                                    <h2>未审核通知公告列表</h2>
                                    <div class="table-responsive">
                                        <table id="example" class="table pmd-table table-hover table-striped display responsive nowrap" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>企业用户名</th>
                                                    <th>标题</th>
                                                    <th>发布日期</th>
                                                    <th>查看</th>
                                                    <th colspan="2" align="center">操作</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                               <c:forEach items="${aditNotice }" var="notice">
                                            		<tr>
                                            			<td>${notice.id }</td>
                                            			<td>${notice.companyUsername }</td>
                                            			<td>${notice.title }</td>
                                            			<td>${notice.releaseDate }</td>
														<td><a href="${pageContext.request.contextPath }/SystemsManagement/LookNoticeById?Id=${notice.id}">查看</a></td>
														<td><a href="${pageContext.request.contextPath }/SystemsManagement/ReviewNoticeServlet?Id=${notice.id}">审核通过</a></td>
                                            		</tr>
                                            	</c:forEach>
                                            </tbody>
                                                                <tr>
				  		<td colspan="8" align="right">
				  		共${pager1.totalSize}条纪录，当前第${pager1.pageNow}/${pager1.totalPage}页，每页${pager1.pageSize}条纪录
				  		<c:choose>
				  			<c:when test="${pager1.hasPre}">
				  				<a href="ShowNoticeListsServlet?pageNow=1">首页</a> |
                				<a href="ShowNoticeListsServlet?pageNow=${pager1.pageNow - 1}">上一页</a> |
				  			</c:when>
				  			<c:otherwise>
				  				<c:out value="首页 | 上一页 | "></c:out>
				  			</c:otherwise>
				  		</c:choose>
				  		<c:choose>
				  			<c:when test="${pager1.hasNext }">
                				<a href="ShowNoticeListsServlet?pageNow=${pager1.pageNow + 1}">下一页</a> |
                			<a href="ShowNoticeListsServlet?pageNow=${pager1.totalPage}">尾页</a>
               				</c:when>
               				<c:otherwise>
               					<c:out value="下一页 | 尾页"/>
               				</c:otherwise>
				  		</c:choose>
				  		</td>
				  			</tr>	  
                                        </table>
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

    <!-- Select2 js-->
    <script type="text/javascript" src="../components/select2/js/select2.full.js"></script>
    <!-- Datatable js -->
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

    <!-- Datatable Bootstrap -->
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

    <!-- Datatable responsive js-->
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/responsive/2.1.0/js/dataTables.responsive.min.js"></script>

    <!-- Datatable select js-->
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js"></script>
    <!-- Propeller Select2 -->
    <script type="text/javascript">
        $(document).ready(function() {

            $(".select-simple").select2({
                theme: "bootstrap",
                minimumResultsForSearch: Infinity,
            });

            $(".select-with-search").select2({
                theme: "bootstrap"
            });

            $(".select-tags").select2({
                tags: false,
                theme: "bootstrap",
            });

            $(".select-add-tags").select2({
                tags: true,
                theme: "bootstrap",
            });
        });
    </script>
    <script type="text/javascript" src="../components/select2/js/pmd-select2.js"></script>
   <!--  <script>
        //Propeller  Customised Javascript code 
        $(document).ready(function() {
            var exampleDatatable = $('#example').DataTable({
                responsive: {
                    details: {
                        type: 'column',
                        target: 'tr'
                    }
                },
                columnDefs: [{
                    className: 'control',
                    orderable: false,
                    targets: 1
                }],
                order: [1, 'asc'],
                bFilter: true,
                bLengthChange: true,
                pagingType: "simple",
                "paging": true,
                "searching": true,
                "language": {
                    "info": " _START_ - _END_ of _TOTAL_ ",
                    "sLengthMenu": "<span class='custom-select-title'>每页显示行数:</span> <span class='custom-select'> _MENU_ </span>",
                    "sSearch": "",
                    "sSearchPlaceholder": "搜索",
                    "paginate": {
                        "sNext": " ",
                        "sPrevious": " "
                    },
                },
                dom: "<'pmd-card-title'<'data-table-title-responsive'><'search-paper pmd-textfield'f>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'pmd-card-footer' <'pmd-datatable-pagination' l i p>>",
            });

            /// Select value
            $('.custom-select-info').hide();

            $("div.data-table-title").html('<h2 class="pmd-card-title-text">Table Card</h2>');
            $("div.data-table-title").html('<h2 class="pmd-card-title-text">Table Responsive</h2>');
            $(".custom-select-action").html('<button class="btn btn-sm pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-primary" type="button"><i class="material-icons pmd-sm">delete</i></button><button class="btn btn-sm pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-primary" type="button"><i class="material-icons pmd-sm">more_vert</i></button>');

        });
    </script> -->


</body>

</html>