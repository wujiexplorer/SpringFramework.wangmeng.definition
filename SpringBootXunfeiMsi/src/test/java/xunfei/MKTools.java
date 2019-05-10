package xunfei;


import com.wangmeng.util.CommonUtil;
import com.wangmeng.util.JDBCConnect;
import com.wangmeng.util.XunfeiConvertUtil;
import com.youbenzi.mdtool.tool.MDTool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//mk转html转text 生成音频
public class MKTools {
   private static final String URL = "jdbc:mysql://192.168.1.66:3306/cat?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true";
   private static final String USERNAME = "root";
   private static final String PASS = "root";
   public static void main(String[] args) throws InterruptedException {
	  /* String html = MDTool.markdown2Html("<!--more--> ## 演示地址http://xunfei.52itstyle.com/xufei_msc/ (https://blog.52itstyle.com/usr/uploads/2018/03/1902142097.jpg) ");
	   System.out.println(html);
	   String text = CommonUtil.trimHtml2Txt(html, null);
	   System.out.println(text);*/
	   List<Object[]> list =  readList();
	   System.out.println("总数数量:"+list.size());
	   for(Object[] obj:list){
		    //讯飞转PCM
		   if(XunfeiConvertUtil.flag){
			   XunfeiConvertUtil.flag = false;
			   System.out.println("数量:"+obj[0]);
			   String pcmPath = "D:\\mp3\\"+obj[0]+".pcm";
			   XunfeiConvertUtil util = new XunfeiConvertUtil();
			   util.convert(CommonUtil.trimHtml2Txt(MDTool.markdown2Html(obj[1].toString()), null), pcmPath);
		   }else{
			   Thread.sleep(10000);
		   }
	   }
   }
   
	public static List<Object[]> readList(){
		List<Object[]> readSceneList = new ArrayList<Object[]>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = JDBCConnect.getConnect(URL,USERNAME,PASS);
			stmt = conn.createStatement();
			String sql = "SELECT cid,TEXT  FROM typecho_contents WHERE TYPE='post' and cid>750";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int cid = rs.getInt(1);
				String text = rs.getString(2);
				Object[] obj = new Object[]{cid,text};
				readSceneList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return readSceneList;
	}
}
