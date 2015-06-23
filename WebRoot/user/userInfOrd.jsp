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
	<title>消息提醒</title>
	    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="../css/evtcar.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../css/userInf.css">
	<link rel="stylesheet" href="../font-awesome-4.3.0/css/font-awesome.min.css">
	 <script src="../js/jquery-2.1.3.min.js"></script>
	<script src="../js/staticinfo.js" type="text/javascript"></script>
	<script src="../js/UserCheck.js"></script> 
	<script type="text/javascript">
	<%
		HttpSession sess = request.getSession();
		usInformation usInf = (usInformation)sess.getAttribute("usInf");
	%>
	window.MAINURL = "<%=basePath%>";
	STATICINFO.USERINFO.URL = "<%=basePath%>";
	STATICINFO.USERINFO.name = "<%= usInf==null ? "&nbsp;" : usInf.getUsId()%>";
	if(STATICINFO.USERINFO.name==" "){ 
		window.location.href="login.jsp";
	}
	</script>
</head>

<body>

	<div class="contain back-color">
		<!--顶部导航栏开始 -->
<header>
<div class="top">
	<div class="wp">
		<div class="logo">
			<a href="/" class="icon_img_logo"></a>
		</div>
		<div class="menu">
			<div class="xl">
				<ul>
					<li><a>客户端下载</a></li>
					<li><a>运营商加盟</a></li>
				</ul>
			</div>
			<div class="loader">
				<a href="../register.html" class="btn btn-success btn-lg"><span>注册</span></a>
				<a href="../login.jsp" class="btn btn-success btn-lg"><span>登录</span></a>
			</div>
		</div>
	</div>
</div>
<!--下面是中部导航栏的代码-->
<div class="nav-green nav-head" id="J_m_nav">
	<div class="nav-content">
		<div class="nav-btn"><a href="../index.html">首页</a></div>
		<div class="nav-btn"><a href="../searchCS.jsp">我要充电</a></div>
		<div class="nav-btn"><a href="../inq_sta.jsp">充电站分布</a></div>
		<div class="nav-btn active"><a href="../userInf.jsp">用户管理</a></div>
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
							<div class="img"><img src="../pic/user1.jpg"><img src="../pic/load2.gif" style=";" class="load">
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
						$(function(){ 
							 $(".user-inf .head-line .name").html(STATICINFO.USERINFO.name);
						})
						</script>
						<div class="bindings"> 
							<div class="fast-item">
									<a href="userInfMsg.jsp" class="btn">
											<div class="img"><i class="fa fa-calendar fa-3x"></i></div>消息提醒
									</a>
									<a href="userInfOrd.jsp" class="btn">
											<div class="img">	<i class="fa fa-shopping-cart fa-3x"></i></div>预约订单
									</a>
									<a href="" class="btn">
											<div class="img"><i class="fa fa-desktop fa-3x"></i></div>最新活动
									</a>
									<a href="" class="btn">
											<div class="img"><i class="fa fa-credit-card fa-3x"></i></div>我的收藏
									</a>
									
									<a href="" class="btn">
											<div class="img"><i class="fa fa-comments fa-3x"></i></div>分享地址
									</a>
									<a href="" class="btn without-side">
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
				<div id="study-record">
					<div class="title">订单管理</div>
					<div class="inf">
						<div class="containbox">
						<div class="bar"> 
							<div role="tabpanel">
							  <!-- Nav tabs -->
							  <ul class="nav nav-tabs" role="tablist">
							    <li role="presentation" class="active"><a href="#neworder" aria-controls="neworder" role="tab" data-toggle="tab">最新订单</a></li>
							    <li role="presentation"><a href="#oldorder" aria-controls="oldorder" role="tab" data-toggle="tab">历史订单</a></li>
							  </ul>

							  <!-- Tab panes -->
							  <div class="tab-content">
							    <div role="tabpanel" class="tab-pane active" id="neworder">
							    	<table class="table table-striped" id="newOrderContent">
									  <th>订单编号</th>
									  <th>充电站名</th>
									  <th>充电地点</th>
									  <th>起始时间</th>
									  <th>结束时间</th>
									  <th>操作</th>
									</table>
							    </div>
							    <div role="tabpanel" class="tab-pane" id="oldorder">
							    	<table class="table table-striped">
									  <th>订单编号</th>
									  <th>充电站名</th>
									  <th>充电地点</th>
									  <th>起始时间</th>
									  <th>结束时间</th>
									  <th>订单状态</th>
									  <th>操作</th>
									  <tr>
									  	<td>1213123123</td>
									  	<td>北京交大充电站</td>
									  	<td>北京市海淀区北下关北京交通大学电气工程楼后面</td>
									  	<td>2015-4-16 16:20:20</td>
									  	<td>2015-4-16 20:20:50</td>
									  	<td>已完成</td>
									  	<td><a href="">删除</a>&nbsp;&nbsp;<a href="">收藏</a>&nbsp;&nbsp; 
									  	<a href="">预约</a>&nbsp;&nbsp;<a href="">点评</a></td>
									  </tr>
									  <tr>
									  	<td>1213123123</td>
									  	<td>北京交大充电站</td>
									  	<td>北京市海淀区北下关北京交通大学电气工程楼后面</td>
									  	<td>2015-4-16 16:20:20</td>
									  	<td>2015-4-16 20:20:50</td>
									  	<td>已完成</td>
									  	<td><a href="">删除</a>&nbsp;&nbsp;<a href="">收藏</a>&nbsp;&nbsp; 
									  	<a href="">预约</a>&nbsp;&nbsp;<a href="">点评</a></td>
									  </tr>
									  <tr>
									  	<td>1213123123</td>
									  	<td>北京交大充电站</td>
									  	<td>北京市海淀区北下关北京交通大学电气工程楼后面</td>
									  	<td>2015-4-16 16:20:20</td>
									  	<td>2015-4-16 20:20:50</td>
									  	<td>已取消</td>
									  	<td><a href="">删除</a>&nbsp;&nbsp;<a href="">收藏</a>&nbsp;&nbsp; 
									  	<a href="">预约</a>&nbsp;&nbsp;<a href="">点评</a></td>
									  </tr>
									  <tr>
									  	<td>1213123123</td>
									  	<td>北京交大充电站</td>
									  	<td>北京市海淀区北下关北京交通大学电气工程楼后面</td>
									  	<td>2015-4-16 16:20:20</td>
									  	<td>2015-4-16 20:20:50</td>
									  	<td>已取消</td>
									  	<td><a href="">删除</a>&nbsp;&nbsp;<a href="">收藏</a>&nbsp;&nbsp; 
									  	<a href="">预约</a>&nbsp;&nbsp;<a href="">点评</a></td>
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
		<footer>
			<div class="company-footer nav-green">
				<div class="footer-content">
					<div class="footer-content-text">
						<img src="../pic/footer-telephone-icon.png" alt="phone">
						<p>
							
							<span class="contents">Tel:123456789123</span>
						</p>
					</div>
					<div class="footer-content-text">
						<img src="../pic/footer-smartphone-icon.png" alt="smartphone">
						<p>
							
							<span class="contents">Mobile:123456789123</span>
						</p>
					</div>
					<div class="footer-content-text">
						<img src="../pic/footer-mail-icon.png" alt="mail">
						<p>
							<span class="contents">Mail:bilinghc@163.com</span>
						</p>
					</div>
					<div>
					<p class="copyright"> &nbsp;&nbsp;© 2015 京ICP备15002253号
			&nbsp;&nbsp;|&nbsp;&nbsp;北京交通大学交通运输学院系统工程与控制研究所&nbsp;&nbsp;|&nbsp;&nbsp;充电站数据来自政府有关部门</p>
					</div>
				</div>
			</div>
		</footer>
		
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>

    <script type="text/javascript">
		$('#myTab a').click(function (e) {
			e.preventDefault()
			$(this).tab('show')
		})

		
		$(function(){ 
			USERCheck.checkNewOrder(function(isok,error){ 
				if(isok){ 
					data = JSON.parse(error);
					$.each(data, function(i, content){
						var msg = "<tr><td>"+data[i].USOrdId+
								"</td><td>"+data[i].USOrdCsName+
								"</td><td>"+data[i].USOrdCsAddr+
								"</td><td>"+data[i].USOrdStartTime+
								"</td><td>"+data[i].USOrdEndTime+
								"</td><td><a href=''>取消</a>&nbsp;&nbsp;<a href=''>修改</a></td></tr>"
				        $("#newOrderContent").append(msg);
					})
				}else{ 
					alert("false")
				}

			},window.MAINURL)
		})
	</script>
</body>
</html>

