<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="">
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="成都大学信息科学与工程学院实训实习系统">
    <meta content="width=device-width, initial-scale=1, user-scalable=no" name="viewport">
    <title>成都大学|信工学院实训实习系统</title>

    <link rel="shortcut icon" type="image/x-icon" href="../assets/images/favicon1.ico">
    <!--font-awesome css-->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" />
    <!-- Google icon -->
    <!--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">-->
    <!-- Bootstrap css -->
    <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css">
    <!--bootstrapValidator css-->
    <link rel="stylesheet" href="../assets/css/bootstrapValidator.css" />
    <!--normalize css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/normalize.css">
    <!-- Propeller css -->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller.min.css">

    <!-- Propeller theme css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller-theme.css" />

    <!-- Propeller admin theme css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/propeller-admin.css">
    <!--my style css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/front-style.css">
    <!--login css-->
    <link rel="stylesheet" type="text/css" href="../assets/css/login.css">
    <!-- Styles Ends -->

</head>


<body class="body-custom" style="height:unset;background: url(../assets/images/propeller-bg.jpg);">
     <!--顶部-->
    <nav class="navbar navbar-inverse navbar-fixed-top pmd-navbar pmd-z-depth-2">

        <div class="container-fluid">
            <div class="toplogo">
                            <a href="javascript:void(0);"><img src="../assets/images/cdu-logo-footer.png" alt="Logo"></a>
            </div>
            <!-- Brand and toggle get grouped for better mobile display -->
            <!--左侧滑-->
            <div class="navbar-header">
               
                <a href="javascript:void(0);" class="navbar-brand">
		   	成都大学信工学院|实训实习系统
		  </a>
            </div>
        </div>

    </nav>
    <div class="logincard" style="transform:unset">
        <div class="pmd-card card-default pmd-z-depth">
            <!--登录模块-->
            <div class="login-card">
                <form action = "LoginServlet" method = "post">
                    <!--头部-->
                    <div class="pmd-card-title card-header-border text-center">
                        <div class="loginlogo">
                            <a href="javascript:void(0);"><img src="../assets/images/logo-computer.jpg" alt="Logo"></a>
                        </div>
                        <h3>成都大学|信工学院<span><strong>实训实习系统</strong></span></h3>
                    </div>
                    <!--身体-->
                    <div class="pmd-card-body">
                        <div class="alert alert-success" role="alert"> 用户名或密码错误！ </div>
                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">账户名</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">perm_identity</i></div>
                                <input type="text" class="form-control" id="exampleInputAmount" name = "account">
                            </div>
                        </div>

                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">密码</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">lock_outline</i></div>
                                <input type="password" class="form-control" id="exampleInputAmount" name = "password">
                            </div>
                        </div>
                        <!--验证码-->
                        <div class="verification-body">
                            <div class="form-group pmd-textfield pmd-textfield-floating-label verification-code-width">
                                <label for="inputError1" class="control-label pmd-input-group-label">验证码</label>
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">comment</i></div>
                                    <input type="text" class="form-control" id="exampleInputAmount" name = "verificationCode">
                                </div>

                            </div>
                            <div class="verification-code" id="vcinAction">验证码</div>
                            <input type="hidden" id="vchidden" name="vchidden">
                        </div>


                        <!--用户角色-->
                        <div class="form-group  login_select">
                            <label class="control-label select_role">用户角色 : </label>

                            <!--Inline Radio button-->
                            <label class="radio-inline pmd-radio pmd-radio-ripple-effect">
                            <input type="radio" name="role"  checked="1" id="inlineRadio1" value="1">
                            <span for="inlineRadio1">企业</span></label>
                            <label class="radio-inline pmd-radio pmd-radio-ripple-effect">
                            <input type="radio" name="role" id="inlineRadio2" value="2">
                            <span for="inlineRadio2">学生</span></label>
                            <label class="radio-inline pmd-radio pmd-radio-ripple-effect">
                            <input type="radio" name="role" id="inlineRadio3" value="9">
                             <span for="inlineRadio3">管理员</span></label>
                        </div>
                    </div>
                    <!--底部-->
                    <div class="pmd-card-footer card-footer-no-border card-footer-p16 text-center">
                        <div class="form-group clearfix">
                            <div class="checkbox pull-left">
                                <label class="pmd-checkbox pmd-checkbox-ripple-effect">
								<input type="checkbox"  value="">
								<span class="pmd-checkbox"> 记住密码</span>
							</label>
                            </div>
                            <span class="pull-right forgot-password">
							<a href="javascript:void(0);">忘记密码?</a>
						</span>
                        </div>
                        <button type="submit" class="btn pmd-ripple-effect btn-primary btn-block">登录</button>
                        <p class="redirection-link">还没有企业账户？ <a href="javascript:void(0);" class="login-register">注册</a>. </p>

                    </div>

                </form>
            </div>
            <!--企业注册模块-->
            <div class="register-card">
                <div class="pmd-card-title card-header-border text-center">
                    <div class="loginlogo">
                        <a href="javascript:void(0);"><img src="../assets/images/logo-computer.jpg" alt="Logo"></a>
                    </div>
                    <h2> <span> <strong>实训企业注册</strong></span></h2>
                </div>
                <form id="defaultForm" method="post" action="${pageContext.request.contextPath }/EnterpriseManagement/RegistCompanyServlet">
                    <div class="pmd-card-body">
                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">注册邀请码</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">code</i></div>
                                <input type="text" class="form-control" name="rscode" id="exampleInputAmount">
                            </div>
                        </div>

                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">企业名称</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">domain</i></div>
                                <input type="text" class="form-control" name="qyname" id="exampleInputAmount">
                            </div>
                        </div>
                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">企业帐号</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">perm_identity</i></div>
                                <input type="text" class="form-control" name="qyusername" id="exampleInputAmount">
                            </div>
                        </div>
                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">用户密码</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">lock</i></div>
                                <input type="password" class="form-control" name="password" id="exampleInputAmount">
                            </div>
                        </div>

                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">再次输入密码</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">lock_outline</i></div>
                                <input type="password" class="form-control" name="confirmPassword" id="exampleInputAmount">
                            </div>
                        </div>
                        <!--<div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">密保邮箱</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">email</i></div>
                                <input type="email" class="form-control" id="exampleInputAmount">
                            </div>
                        </div>-->
                        <div class="verification-body">
                            <div class="form-group pmd-textfield pmd-textfield-floating-label verification-code-width">
                                <label for="inputError1" class="control-label pmd-input-group-label">密保邮箱</label>
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">email</i></div>
                                    <input type="text" class="form-control" name="email" id="regist-email">
                                </div>
                            </div>
                            <div class="div-email">
                                <a href="javascript:;" class="send1" >发送验证码</a><!-- onclick="sends.send();" -->
                            </div>
                        </div>

                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">验证码</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">comment</i></div>
                                <input type="text" class="form-control" id="exampleInputAmount" name="yzm">
                            </div>
                        </div>
                        <!--数字加法验证-->
                        <div class="form-group pmd-textfield">
                            <label class="col-lg-3 control-label" id="captchaOperation"></label>
                            <div class="col-lg-2 input-group">
                                <input type="text" class="form-control captcha-input" name="captcha" style="font-size: 18px;padding-bottom: 0px;" />
                            </div>
                        </div>

                    </div>

                    <div class="pmd-card-footer card-footer-no-border card-footer-p16 text-center">
                        <input  type="submit" class="btn pmd-ripple-effect btn-primary btn-block" value="注册">
                        <p class="redirection-link">已经有账户了？ <a href="javascript:void(0);" class="register-login">登录</a>. </p>
                    </div>

                </form>
            </div>
            <!--忘记密码-->
            <div class="forgot-password-card">

                <div class="pmd-card-title card-header-border text-center">
                    <div class="loginlogo">
                        <a href="javascript:void(0);"><img src="../assets/images/logo-computer.jpg" alt="Logo"></a>
                    </div>
                    <h3>找回密码<br><span>请提交您的电子邮件地址，我们会向您发送一个链接来重置密码.</span></h3>
                </div>
                <form id="defaultFormm" action = "ResetPassServlet" method="post" >
                    <div class="pmd-card-body">
<!--                     用户角色
                        <div class="form-group  login_select">
                            <label class="control-label select_role">用户角色 : </label>

                            Inline Radio button
                            <label class="radio-inline pmd-radio pmd-radio-ripple-effect">
                            <input type="radio" name="role"  checked="1" id="inlineRadio1" value="1">
                            <span for="inlineRadio1">企业</span></label>
                            <label class="radio-inline pmd-radio pmd-radio-ripple-effect">
                            <input type="radio" name="role" id="inlineRadio2" value="2">
                            <span for="inlineRadio2">学生</span></label>
                            <label class="radio-inline pmd-radio pmd-radio-ripple-effect">
                            <input type="radio" name="role" id="inlineRadio3" value="9">
                             <span for="inlineRadio3">管理员</span></label>
                        </div> -->
                        <div class="verification-body">
                            <div class="form-group pmd-textfield pmd-textfield-floating-label verification-code-width">
                                <label for="inputError1" class="control-label pmd-input-group-label">密保邮箱地址</label>
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">email</i></div>
                                    <input type="text" class="form-control" name="mbemail" id="forgot-email">
                                </div>
                            </div>
                            <div class="div-email">
                                <a href="javascript:void(0);" class="send2" >发送验证码</a>
                            </div>
                        </div>
                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label" >验证码</label>
                            <!-- 于曦添加 -->
                            <input type="hidden" id="rvchidden" name="rvchidden">
                            
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">comment</i></div>
                                <input type="text" class="form-control" id="exampleInputAmount" name="rvcinAction">
                            </div>
                        </div>

                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">用户密码</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">lock</i></div>
                                <input type="password" class="form-control" name="newpassword" id="exampleInputAmount">
                            </div>
                        </div>

                        <div class="form-group pmd-textfield pmd-textfield-floating-label">
                            <label for="inputError1" class="control-label pmd-input-group-label">再次输入密码</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="material-icons md-dark pmd-sm">lock_outline</i></div>
                                <input type="password" class="form-control" name="newconfirmPassword" id="exampleInputAmount">
                            </div>
                        </div>
                    </div>
                    <div class="pmd-card-footer card-footer-no-border card-footer-p16 text-center">
                        <button type="submit" class="btn pmd-ripple-effect btn-primary btn-block">提交</button>
                        <p class="redirection-link">已经找回密码？<a href="javascript:void(0);" class="register-login">返回登录</a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <footer class="footer">
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
                            <h3 class="pmd-card-title-text">By 信工学院同创工作室</h3>
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
    <!-- Scripts Starts -->
    <script src="../assets/js/jquery-1.12.2.min.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script src="../assets/js/propeller.min.js"></script>
    <script src="../assets/js/bootstrapValidator.js"></script>
    <script>
        $(document).ready(function() {
            var sPath = window.location.pathname;
            var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
            $(".pmd-sidebar-nav").each(function() {
                $(this).find("a[href='" + sPage + "']").parents(".dropdown").addClass("open");
                $(this).find("a[href='" + sPage + "']").parents(".dropdown").find('.dropdown-menu').css("display", "block");
                $(this).find("a[href='" + sPage + "']").parents(".dropdown").find('a.dropdown-toggle').addClass("active");
                $(this).find("a[href='" + sPage + "']").addClass("active");
            });
        });
    </script>
    <!-- login page sections show hide -->
    <script type="text/javascript">
        $(document).ready(function() {
            $('.app-list-icon li a').addClass("active");
            $(".login-for").click(function() {
                $('.login-card').hide()
                $('.forgot-password-card').show();
            });
            $(".signin").click(function() {
                $('.login-card').show()
                $('.forgot-password-card').hide();
            });
        });
    </script>
    <!--控制三个面板的显示和隐藏-->
    <script type="text/javascript">
        $(document).ready(function() {
            $(".login-register").click(function() {
                $('.login-card').hide()
                $('.forgot-password-card').hide();
                $('.register-card').show();
            });
            $(".register-login").click(function() {
                $('.register-card').hide()
                $('.forgot-password-card').hide();
                $('.login-card').show();
            });
            $(".forgot-password").click(function() {
                $('.login-card').hide()
                $('.register-card').hide()
                $('.forgot-password-card').show();
            });
        });
    </script>
	<script type="text/javascript">
		$(document).ready(function(){
			  htmlobj=$.ajax({url:"/practiceSystem/Login/IndetifyCodeServlet",async:false});
			  $("#vcinAction").html(htmlobj.responseText);
			  $("#vchidden").val(htmlobj.responseText);
		});
	</script>
		<script type="text/javascript">
		$(document).ready(function(){
			$(".send2").click(function(){
// 				获取页面输入的email，将其作为参数传递到后台servlet中进行处理，得到的验证码在页面存放起来。
				var mbemail = $("#fotgot-email").val();
				 htmlobj=$.ajax({url:"/practiceSystem/Login/IdentifyCodeByEmailServlet?mbemail="+mbemail,async:false});
				  $("#rvchidden").val(htmlobj.responseText);
			});
		});
	</script>
    <!--<script src="../assets/js/style.js"></script>-->
    <script src="../assets/js/login.js"></script>
</body>

</html>