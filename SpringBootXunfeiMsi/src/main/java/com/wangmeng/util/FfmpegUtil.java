package com.wangmeng.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ffmpeg把PCM转为MP3
 * 创建者 科帮网 https://blog.52itstyle.com
 * 创建时间	2017年3月6日
 */
public class FfmpegUtil {
	public static boolean processAudio(String oldfilepath,String newfilepath,String type) {  
        List<String> commend = new ArrayList<String>();  
        commend.add("D:\\develop tools\\ffmpeg64\\bin\\ffmpeg.exe");//win
        //commend.add("ffmpeg");//linux Docker 
        commend.add("-f");  
        commend.add("s16le"); 
        commend.add("-ar");
        commend.add("8000");
        commend.add("-ac");
        commend.add("2");
        commend.add("-i");  
        commend.add(oldfilepath);
        commend.add("-acodec");//音频选项， 一般后面加copy表示拷贝
        commend.add("mp3");
        commend.add("-y");//表示覆盖旧文件
        commend.add(newfilepath+"."+type);
        try {  
            Process process = new ProcessBuilder(commend).redirectErrorStream(true).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();// 加上这句，系统会等待转换完成。不加，就会在服务器后台自行转换。
            System.out.println("exitCode = "+exitCode);
            Boolean flag = exitCode==0?true:false;
            return flag;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }
	private static final String URL = "jdbc:mysql://192.168.1.66:3306/cat?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowMultiQueries=true";
	private static final String USERNAME = "root";
    private static final String PASS = "root";
    public static void main(String[] args) throws IOException {
    	List<Object[]> list =  readList();
 	   System.out.println("总数数量:"+list.size());
 	   for(Object[] obj:list){
 		   System.out.println(obj[0]);
 		  String pcmPath = "D:\\mp3\\"+obj[0]+".pcm";
 		  File file = new File(pcmPath);
 		  if(file.exists()){
 			 FfmpegUtil.processAudio(pcmPath,"D:\\mp3\\ffmepg\\"+obj[0],"mp3");  
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
			String sql = "SELECT cid,TEXT  FROM typecho_contents WHERE TYPE='post'";
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
