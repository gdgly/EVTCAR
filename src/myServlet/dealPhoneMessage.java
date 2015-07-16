package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myBean.usInformation;
import myBean.usOrder;
import myTools.dataBase;
import myTools.dbUtil;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dealPhoneMessage extends HttpServlet {
	private static  final transient Logger log = Logger.getLogger(dealPhoneMessage.class);
	private static String appVersion;
	/**
	 * Constructor of the object.
	 */
	public dealPhoneMessage() {
		super();
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		appVersion = getServletConfig().getInitParameter("appVersion");
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	private static void checkLogin(HttpServletRequest request, HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		HttpSession ss = request.getSession();
		JSONObject ms = new JSONObject();
		
		log.info("进入登录检测");
		//log.info(ss.getId());
		boolean isLogin =  ss.getId().equals((String)ss.getAttribute("usSessId"));
		log.info("isLogin:"+isLogin);
		if(isLogin){
			try {
				ms.append("isSuccess", true);
				ms.append("message", "已经登录");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				ms.append("isSuccess", false);
				ms.append("message", "用户未登录");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println(ms.toString());
		out.flush();
		out.close();
	}
	/**
	 * 手机用户退出
	 * @param request 
	 * @param response
	 * @throws IOException
	 */
	private static void userLogout(HttpServletRequest request, HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		HttpSession ss = request.getSession();
		JSONObject ms = new JSONObject();
		//usInformation usInf = (usInformation)ss.getAttribute("usInf");
		String usSessId = (String) ss.getAttribute("usSessId");
		if(usSessId != null){
			ss.removeAttribute("usSessId");
			try {
				ms.append("isSuccess", true);
				ms.append("message", "成功退出");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				ms.append("isSuccess", false);
				ms.append("message", "用户未登录，退出失败");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println(ms.toString());
		out.flush();
		out.close();
	}
	/**
	 * 消息中心 信息查询
	 * @param request username;
	 * @param response
	 * @param MsgState 1:最新消息；2：历史消息
	 * @throws IOException
	 */
	private static void checkMsg(HttpServletRequest request, HttpServletResponse response , int MsgState)throws IOException{
		PrintWriter out = response.getWriter();
		HttpSession ss = request.getSession();
		JSONObject ms = new JSONObject();
		
		String username = (String)request.getAttribute("username");
		//usInformation usInf = (usInformation)ss.getAttribute("usInf");
		//String usSessId = (String) request.getAttribute("usSessId");
		String sql ="SELECT * from UserMessageInf where MsgType = 'createOrd' and USid = ? and MsgState= ?";
		String pras[] = new String[]{username,String.valueOf(MsgState)};
		dbUtil db = new dbUtil();
		ResultSet rs = db.query(sql, pras);
		JSONArray msgInf = new JSONArray();
		try {
			while(rs.next()){
				JSONObject data = new JSONObject();
				try {
					data.put("msgid", rs.getString(1));
					data.put("msgCreatTime",rs.getString(4));
					data.put("msgValue", rs.getString(3));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msgInf.put(data);
			}
			db.closeAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out.println(msgInf.toString());
		out.flush();
		out.close();
	}
	/**
	 * 手机端用户对充电站纠错
	 * @param request changedata i;username
	 * @param response
	 * @throws IOException
	 */
	
	private static void csCorrect(HttpServletRequest request, HttpServletResponse response)throws IOException{
		
		
		PrintWriter out = response.getWriter();
		HttpSession ss = request.getSession();
		JSONObject ms = new JSONObject();
		
		String username = (String)request.getAttribute("username");
		//usInformation usInf = (usInformation)ss.getAttribute("usInf");
		//String usSessId = (String) request.getAttribute("usSessId");
		
		
		String  CSId,CSName,CSAddr,CSDate,CSMode,CSFast,CSlow,Operator,ParkFee,CSPub,CSState,CSPhone,CSNotes;
		//System.out.println("进入dealCorrect");
		
		CSId=new String( request.getParameter("changedata0"));		
		CSName=new String( request.getParameter("changedata1"));
		CSAddr=new String( request.getParameter("changedata2"));
		CSDate=new String( request.getParameter("changedata3"));
		CSMode=new String( request.getParameter("changedata4"));
		CSFast=new String( request.getParameter("changedata5"));
		CSlow=new String( request.getParameter("changedata6"));
		Operator=new String( request.getParameter("changedata7"));
		ParkFee=new String( request.getParameter("changedata8"));
		CSPub=new String( request.getParameter("changedata9"));
		CSState=new String( request.getParameter("changedata10"));
		CSPhone=new String( request.getParameter("changedata11"));
		CSNotes=new String( request.getParameter("changedata12"));
		//String usId = new String( request.getParameter("username"));
		//System.out.println(CSId+CSName+CSAddr+CSDate+CSMode+CSFast+CSlow+Operator+ParkFee+CSPub+CSState+CSPhone+CSNotes);
		boolean isError = false;
		String sql="insert into CS_correction(CSID,CSName,CSAddr,CSDate,CSMode,CSFast,CSlow,Operator,ParkFee,CSPub,CSState,CSPhone,CSNotes,USID) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    String pras[] = new String[]{CSId,CSName,CSAddr,CSDate,CSMode,CSFast,CSlow,Operator,ParkFee,CSPub,CSState,CSPhone,CSNotes,username};
		 try {
	     	   dbUtil db = new dbUtil();
	     	   db.update(sql, pras);
		       //System.out.print(sql);
		       if(db.getResu()!=0)  
		       {
		    	   // System.out.println("提交信息成功");
				    ms.append("isSuccess", true);
					ms.append("message", "提交信息成功");
					isError = false;
					//request.setAttribute("message", "true");
		       }else{
				    ms.append("isSuccess", false);
					ms.append("message", "提交信息失败");
					isError = true;
					//request.setAttribute("message", "false");
		       }
		       db.closeAll();
	           //con.close();      
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		out.println(ms.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 根据充电站ID查询评价信息
	 * @param request CSId
	 * @param response
	 * @throws IOException
	 */
	private static void checkCommentinfo(HttpServletRequest request, HttpServletResponse response)throws IOException{
		
		
		PrintWriter out = response.getWriter();
		HttpSession ss = request.getSession();
		JSONObject ms = new JSONObject();
		
		String CSId=new String(request.getParameter("CSId"));
		log.info("查询评价信息的充电站: "+CSId);
		//System.out.println("查询评价信息的充电站"+CSId);
		JSONArray commentInf = new JSONArray();
		
		dbUtil db = new dbUtil();
		String sql ="Select USId,Time,Star,Content from CS_Comments  where CSID= ? ";
		String pras = CSId;
		ResultSet rs = db.query(sql, pras);
		try {
			while (rs.next()) {
				JSONObject data = new JSONObject();
				data.put("USId", rs.getString(1).trim());
				data.put("Time", rs.getString(2).trim());
				data.put("Star", rs.getString(3).trim());
				data.put("Content", rs.getString(4).trim());
				commentInf.put(data);
				//System.out.println(commentInf);
			}
		db.closeAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(commentInf);
		out.println(commentInf);
		out.flush();
		out.close();
	}
	
	/**
	 * 提交评价信息
	 * @param request starsum;comment;username;csid
	 * @param response
	 * @throws IOException
	 */
	private static void comment(HttpServletRequest request, HttpServletResponse response)throws IOException{
		
		
		PrintWriter out = response.getWriter();
		JSONObject ms = new JSONObject();
		
		
	    String Star=new String( request.getParameter("starsum"));
	    String Content=new String( request.getParameter("comment"));
	    String USId=request.getParameter("username");
	    String CSID=new String( request.getParameter("csid"));
		//System.out.println("点评"+starsum+comments);
		String sql="insert into CS_Comments(Star,Content,USId,CSID) values (?,?,?,?)";
		String pras[]=new String[]{Star,Content,USId,CSID};
		dbUtil db = new dbUtil();
		db.update(sql, pras);
		 try {
		       if(db.getResu()!=0)  
		       {
		    	   // System.out.println("提交信息成功");
		    	   log.info("提交信息成功");
				    ms.append("isSuccess", true);
					ms.append("message", "提交信息成功");
					//request.setAttribute("message", "true");
		       }else{
					//request.setAttribute("message", "false");
		    	   // System.out.println("提交信息失败");
		    	   log.info("提交信息失败");
		    	   ms.append("isSuccess", false);
		    	   ms.append("message", "提交信息失败");
		       } 
		       db.closeAll();
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 out.println(ms.toString());
		 out.flush();
		 out.close();

	}
	/**
	 * 查询全国充电站信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void searchCityCS(HttpServletRequest request, HttpServletResponse response)throws IOException{
		
		
		PrintWriter out = response.getWriter();
		JSONObject ms = new JSONObject();
		
		
		String cityname = request.getParameter("cityname");
		log.info("要查询的城市名："+ cityname);
		//System.out.println(cityname);
		JSONArray csInf = new JSONArray();
		dataBase db=new dataBase();
		Connection con =db.getConnection();
		String condition ;
		if(cityname.equals("全国")){ 
			condition ="Select * from CS_BasicInformation ";
		}
		else condition ="Select * from CS_BasicInformation where CSCity LIKE '"+cityname+"%'";
		PreparedStatement sql;
		try {
		sql = con.prepareStatement(condition);
		ResultSet rs = sql.executeQuery();
		while (rs.next()) {
			JSONObject data = new JSONObject();
			data.put("CSId", rs.getString(1));
			data.put("CSName", rs.getString(2).trim());
			data.put("CSAddr", rs.getString(3).trim());
			data.put("CSProvince", rs.getString(4).trim());
			data.put("CSCity", rs.getString(5).trim());
			data.put("CSArea", rs.getString(6).trim());
			data.put("Datetime",rs.getDate(7));
			data.put("CSLatiValue", rs.getFloat(8));
			data.put("CSLongValue", rs.getFloat(9));
			data.put("CSMode",rs.getFloat(10));
			if(rs.getFloat(11)<0){
				data.put("CSFast", "未知");
				data.put("CSSlow", "未知");
				data.put("CSSum", "未知");
			}else{
				data.put("CSFast", rs.getFloat(11));
				data.put("CSSlow", rs.getFloat(12));
				data.put("CSSum", rs.getFloat(13));
			}
			data.put("OperatorID",rs.getString(14));
			data.put("CSIsOrder",rs.getFloat(15));
			data.put("ParkID",rs.getString(16));
			data.put("ChargeFee", rs.getFloat(17));
			data.put("ServiceFee", rs.getFloat(18));
			if(rs.getString(19)!=null) data.put("Feenotes", rs.getString(19).trim());
			else data.put("Feenotes", "暂无信息");
			data.put("CSPub", rs.getFloat(20));
			data.put("CSState", rs.getFloat(21));
			data.put("CSTime", rs.getString(22));
			if(rs.getString(23)!=null) data.put("CSPhone", rs.getString(23).trim());
			else data.put("CSPhone", "暂无信息");
			if(rs.getString(24)!=null) data.put("CSNotes", rs.getString(24).trim());
			else data.put("CSNotes", "暂无消息");
		//	data.put("CSFeeDay", rs.getFloat(24));
			csInf.put(data);
		}
		db.close(rs, sql, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //System.out.print(csInfPos.get(0).getCsName()+","+csInfPos.get(0).getCsAddr()+"   ");
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 out.println(csInf);
		 out.flush();
		 out.close();

	}
	/**
	 * app版本检测
	 * @param request	version
	 * @param response
	 * @throws IOException
	 */
	private static void checkAPPUpdate(HttpServletRequest request, HttpServletResponse response)throws IOException{
		
		
		PrintWriter out = response.getWriter();
		JSONObject ms = new JSONObject();
		String appNowVision = request.getParameter("version");
		if(!appNowVision.equals(appVersion)){
			 try {
			    	    log.info("app版本不一致，需要更新。版本号："+appNowVision);
					    ms.append("isSuccess", true);
						ms.append("message", "http://test.ezchong.com/app/EVTCAR.apk");//APP下载地址
				}catch (JSONException e) {
					e.printStackTrace();
				}
		}else {
			try {
	    	    log.info("app版本不需要更新。版本号："+appNowVision);
			    ms.append("isSuccess", false);
				ms.append("message", "版本一致");//APP下载地址
			}catch (JSONException e) {
				e.printStackTrace();
			}
		}

		 out.println(ms);
		 out.flush();
		 out.close();
	}
	
	/**
	 * 彻底删除历史消息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void deleteMsg(HttpServletRequest request, HttpServletResponse response,int MsgState)throws IOException{
		
		
		PrintWriter out = response.getWriter();
		
		String UsId= request.getParameter("username");
		String CSId=new String( request.getParameter("msgid"));
		response.setContentType("text/html");
		JSONArray Msg = new JSONArray();
		dataBase db=new dataBase();
		Connection con =db.getConnection();
		String sql;
		sql="update UserMessageInf set MsgState='"+MsgState+"' where USid='"+UsId+"' and id='"+CSId+"'";
		PreparedStatement ps;
		try {
			//System.out.println(sql);
			ps = con.prepareStatement(sql);
			int rs = ps.executeUpdate();
			if(rs!=0) {
				JSONObject data = new JSONObject();
				data.put("isSucess", "true");
				Msg.put(data);
			}
			//rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(Msg);
		out.println(Msg);
		out.flush();
		out.close();
	}
	/**
	 * 统计评星信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void analysiscminfo(HttpServletRequest request, HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		
		String CSId=new String( request.getParameter("CSId"));		
		response.setContentType("text/html");
		JSONArray analysisCmInf = new JSONArray();
		dataBase db=new dataBase();
		Connection con =db.getConnection();
		String sql;
		sql="select count(USId) as 'ussum',"+
		"isnull(CAST(AVG(cast(Star as DECIMAL(2,1))) AS DECIMAL(2,1)),0) as 'avg',"+
		"isnull(sum(case when Star in ('1') then 1 else 0 end),0) as '1star',"+
		"isnull(sum(case when Star in ('2') then 1 else 0 end),0) as '2star',"+
		"isnull(sum(case when Star in ('3') then 1 else 0 end),0) as '3star',"+
		"isnull(sum(case when Star in ('4') then 1 else 0 end),0) as '4star',"+
		"isnull(sum(case when Star in ('5') then 1 else 0 end),0) as '5star'"+
		" from CS_Comments where CSID='"+CSId+"'";
		PreparedStatement ps;
		try {
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				JSONObject data = new JSONObject();
				data.put("ussum", rs.getString(1).trim());
				data.put("StarAvg", rs.getString(2).trim());
				data.put("Star1", rs.getString(3).trim());
				data.put("Star2", rs.getString(4).trim());
				data.put("Star3", rs.getString(5).trim());
				data.put("Star4", rs.getString(6).trim());
				data.put("Star5", rs.getString(7).trim());
				analysisCmInf.put(data);
				
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(analysisCmInf);
		out.println(analysisCmInf);
		out.flush();
		out.close();
	}
	
	/**
	 * 检测验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void checkCode(HttpServletRequest request, HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		JSONObject ms = new JSONObject();
		
		HttpSession sess = request.getSession();
		String code = (String)sess.getAttribute("rand");
		String ccode = (String)request.getParameter("code");
		if(code.equals(ccode)){
			try {
				ms.append("isSuccess", true);
				ms.append("message", "success");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				ms.append("isSuccess", false);
				ms.append("message", "fail");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println(ms.toString());
		out.flush();
		out.close();
	}
	/**
	 * 生产预约订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void produceOrder(HttpServletRequest request, HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		JSONObject ms = new JSONObject();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
		//�����õ����ڸ�ʽ����'20010-03-13',�ͱ���Ҫ��ʽ��һ������  
		  String dateBegin = request.getParameter("dateBegin"); 
		  String dateStop = request.getParameter("dateStop");
		  boolean isError = false;
		  System.out.println(dateBegin+" "+dateStop);
		  
		  if(java.sql.Date.valueOf(dateBegin).after(java.sql.Date.valueOf(dateStop))){ 
		   
			  try {
				ms.append("isSuccess", false);
				ms.append("message", "充电结束时间小于预约时间 ,请重新输入");
				isError = true;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		  } 
		  StringBuffer timeBegin = new StringBuffer(); 
		  StringBuffer timeStop   = new StringBuffer(); 
		  
		  timeBegin.append(request.getParameter("timeBegin")); 
		  timeStop.append(request.getParameter("timeStop")); 
		  System.out.println(timeBegin.toString()+" "+timeStop.toString());
		  if(java.sql.Date.valueOf(dateBegin).equals(java.sql.Date.valueOf(dateStop))){  //��ʼ���ڵ��ڽ������� 
			   if(java.sql.Time.valueOf(timeBegin.toString()).equals(java.sql.Time.valueOf(timeStop.toString()))){ //����ʱ����ͬ
				   try {
					ms.append("isSuccess", false);
					ms.append("message", "两次输入时间相等，请重新输入");
					isError = true;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
			   else if(java.sql.Time.valueOf(timeBegin.toString()).after(java.sql.Time.valueOf(timeStop.toString()))){
				   try {
						ms.append("isSuccess", false);
						ms.append("message", "充电结束时间小于预约时间 ,请重新输入");
						isError = true;
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   }
		  }
		  if(!isError){
			  //ʱ���ʽ��ȷ�����涩����Ϣ
			  try {	
			  /*csId csName csAddr csType*/
			  String csId = request.getParameter("csId");
			  String csName = request.getParameter("csName");
			  String csAddr = request.getParameter("csAddr");
			  String csType = request.getParameter("csType");
			  String USOrdStratTime = dateBegin+" "+timeBegin;
			  String USOrdEndTime = dateStop+" "+timeStop;
			  String usId = request.getParameter("username");
			  String orderStatue = "1";//确认订单
			  usOrder usord =  new usOrder(usId,csId,USOrdStratTime,USOrdEndTime,orderStatue,csType,csName,csAddr);
			  if(usord.saveOrder())
				  {
				  	usord.saveMsg();//保存订单信息
				    ms.append("isSuccess", true);
					ms.append("message", "订单生成成功");
					isError = false;
			  }else{
				    ms.append("isSuccess", false);
					ms.append("message", "订单生成失败");
					isError = true;
			  };
			//  dbEntity db = new dbEntity();
     	    //  db.SaveOrder(USid,USOrdId,USOrdDate,USOrdStartTime,USOrdEndTime,USOrdStatue,csId);
			  } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  out.println(ms.toString());
		  out.flush();
		  out.close();
	}
	
	/**
	 * 查询预约订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void checkOrder(HttpServletRequest request, HttpServletResponse response, int USOrdStatue)throws IOException{
		PrintWriter out = response.getWriter();
		JSONObject ms = new JSONObject();
		dataBase db=new dataBase();
		Connection con =db.getConnection();
		String USid=request.getParameter("username");
		String condition ;
		//condition ="SELECT * from UserOrderInf where USOrdStatue = '1' and USid = '"+usInf.getUsId()+"' ORDER BY USOrdId DESC";
		condition ="SELECT * from UserOrderInf where USOrdStatue = '"+USOrdStatue+"' and USid ='"+USid+"'and DATEDIFF(DAY,USOrdDate,GETDATE())<=7 ORDER BY USOrdId DESC";
		System.out.println(condition);
		PreparedStatement sql;
		JSONArray msgInf = new JSONArray();
		try {
			sql = con.prepareStatement(condition);
			ResultSet rs = sql.executeQuery();
			while(rs.next()){
				JSONObject data = new JSONObject();
				try {
					data.put("USOrdId",rs.getString(1));
					data.put("USOrdCsName", rs.getString(9));
					data.put("USOrdCsAddr", rs.getString(10));
					data.put("USOrdStartTime", rs.getString(5));
					data.put("USOrdEndTime", rs.getString(6));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msgInf.put(data);
			}
			db.close(rs, sql, con);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		out.println(msgInf.toString());
		out.flush();
		out.close();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String act = request.getParameter("act");
		JSONObject ms = new JSONObject();

		HttpSession ss = request.getSession();
		usInformation usInf = (usInformation)ss.getAttribute("usInf");
		
		log.info("---------act:______"+act);
		
		switch(act){
			case "checkLogin"	: this.checkLogin(request, response);//检测登录
			case "userLogout"	: this.userLogout(request, response);//用户退出登录
			case "csCorrect" 	: this.csCorrect(request, response); //纠错
			case "checkMsg"	 	: this.checkMsg(request, response, 1);//查询最新消息
			case "checkOldMsg"	: this.checkMsg(request, response, 2);//查询历史消息
			case "commentinfo"	: this.checkCommentinfo(request, response);//查询评价信息
			case "comment"		: this.comment(request, response);//提交评价信息
			case "searchCityCS" : this.searchCityCS(request, response);// 查询全国充电站信息
			case "checkAPPUpdate":this.checkAPPUpdate(request, response);//app版本检测
		
			/****以下仍需修改***/
			case "deleteMsg"	: this.deleteMsg(request, response, 2);//删除消息提醒的最新消息，放入历史消息中
			case "deleteOldMsg"	: this.deleteMsg(request, response,3);//删除消息提醒的最新消息，放入历史消息中
			case "analysiscminfo":this.analysiscminfo(request, response);//统计评星信息
			case "checkCode"	: this.checkCode(request, response);//统计评星信息
			case "produceOrder" : this.produceOrder(request, response);//生产订单信息
			case "checkNewOrder" : this.checkOrder(request, response,1);//查询最新订单信息
			case "checkOldOrder" : this.checkOrder(request, response,2);//查询历史订单信息
			default : break;
		}
	}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	
}