<%@ page language="java" import="java.util.*" import="myBean.usInformation" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<title>用户管理</title>
	    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="css/evtcar.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/userInf.css">
	<link rel="stylesheet" href="font-awesome-4.3.0/css/font-awesome.min.css">
	<script src="js/staticinfo.js" type="text/javascript"></script>
	<script src="js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript">
	<%
		HttpSession sess = request.getSession();
		usInformation usInf = (usInformation)sess.getAttribute("usInf");
	%>
	window.MAINURL = "<%=basePath%>";
	STATICINFO.USERINFO.URL = "<%=basePath%>";
	STATICINFO.USERINFO.name = "<%= usInf==null ? "" : usInf.getUsId()%>";
	if(STATICINFO.USERINFO.name==""){
		alert("您好，请先登录！点击关闭跳转至登录界面");
		window.location ="login.jsp";
	}
	</script>
</head>

<body class="back-color">
	<div class="contain ">
<!--顶部导航栏开始 -->
<%@include file="head.jsp" %>
<div class="nav-green nav-head" id="J_m_nav">
	<div class="nav-content">
		<div class="nav-btn "><a href="index.jsp">首页</a></div>
		<div class="nav-btn "><a href="searchCS.jsp">我要充电</a></div>
		<div class="nav-btn"><a href="inq_sta.jsp">充电站分布</a></div>
		<div class="nav-btn active"><a href="userInf.jsp">用户管理</a></div>
		<div class="nav-btn"><a href="#">关于我们</a></div>
	</div>
</div>
</header>

<!--顶部导航栏结束 -->
		<div class="main">
			<div class="newcontainer">
				<div id="user-card">
					<div class="maozi"></div>
					<div class="inners clearfix without-side">
						<div class="avatar-unit">
							<div class="img"><img src="pic/user1.jpg"><img src="pic/load2.gif" style=";" class="load">
								<div class="change-avatar">
									<a class="btn wbtn btn12"><span class="text">更换头像</span></a>
									<div style="position: absolute; opacity: 0.01; overflow: hidden;">
										<iframe src="javascript:'<html></html>'" frameborder="no" border="0" name="IFrame_i8i1vmjl" id="IFrame_i8i1vmjl" style="display: none;">
										</iframe>
										<input type="file" name="file" size="1" style="position: absolute; top: 0px; left: 0px; border: 0px;">
									</div>
								</div>
							</div>
							<div class="counts clearfix">
								<a href="/yclovezyw/followers/" rel="nofollow" class="followers">
								<div class="num">0</div><div class="sub">粉丝</div></a>
								<a href="/yclovezyw/following/" class="follows">
								<div class="num">1</div><div class="sub">关注</div></a>
							</div>
						</div>
						<div class="user-inf">
							<div class="head-line"><div class="name">碎步行</div></div>
							<ul class="introduction">
								<li><i class="fa fa-location-arrow"></i>
								<em>来自北京</em></li>
								<li><i class="fa fa-suitcase"></i><em>电动车爱好者</em></li>
							</ul>
							<div class="about clearfix">自从用了乐充，充电从此无忧</div>
						</div>
						<script type="text/javascript">
						     $(".user-inf .head-line .name").html(STATICINFO.USERINFO.name);
						</script>
						<div class="bindings"> 
							<div class="fast-item">
									<a href="user/userInfMsg.jsp" class="btn">
											<div class="img"><i class="fa fa-calendar fa-3x"></i></div>消息提醒
									</a>
									<a href="user/userInfOrd.jsp" class="btn">
											<div class="img">	<i class="fa fa-shopping-cart fa-3x"></i></div>预约订单
									</a>
									<a href="" class="btn">
											<div class="img"><i class="fa fa-desktop fa-3x"></i></div>最新活动
									</a>
									<a href="" class="btn">
											<div class="img"><i class="fa fa-credit-card fa-3x"></i></div>我的收藏
									</a>
									
									<a href="user/userCsSubmit.jsp" class="btn">
											<div class="img"><i class="fa fa-comments fa-3x"></i></div>分享地址
									</a>
									<a href="user/UserInfManage.jsp" class="btn without-side">
											<div class="img"><i class="fa fa-info-circle fa-3x"></i></div>更新个人资料
									</a>
							</div>
							<div class="message">
								<ul class="clearfix">
									<li><i class="fa fa-clock-o"></i>已预约时间：
									<span>80</span>小时
									</li>
									<li><i class="fa fa-question-circle"></i>充电地址分享：
									<span>0</span>条
									</li>
									<li><i class="fa fa-pencil"></i>金币：
									<span>80</span>枚
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="user-menu">
						
						<div class="btn-group l">
							<a href="" class="dropdown-toggle  tab" data-toggle="dropdown" aria-expanded="false">个人信息 <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
							    <li><a href="">基本信息</a></li>
							    <li><a href="">我的订单</a></li>
							    <li><a href="">账户金额</a></li>
							    <li><a href="">充值提现</a></li>
							    <li><a href="">积分</a></li>
							 </ul>
						</div>
						<div class="btn-group l">
							<a href="" class="dropdown-toggle  tab" data-toggle="dropdown" aria-expanded="false">客户服务 <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
							    <li><a href="">投诉</a></li>
							    <li><a href="">申诉</a></li>
							    <li><a href="">消息</a></li>
							    <li><a href="">评价</a></li>
							 </ul>
						</div>
						
					</div>
				</div>
			
				</div>

		    </div>
		</div>
		<br><br><br><br><br><br><br><br>
		<%@include file="footer.jsp" %>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

    <script type="text/javascript">
		$('#myTab a').click(function (e) {
							  e.preventDefault()
							  $(this).tab('show')
							})
	</script>
</body>
</html>

