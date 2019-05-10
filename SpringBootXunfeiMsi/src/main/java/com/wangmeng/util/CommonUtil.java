package com.wangmeng.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtil {
	/**
	* 把html内容转为文本
	* @param html 需要处理的html文本
	* @param filterTags 需要保留的html标签样式
	* @return
	*/
	public static String trimHtml2Txt(String html, String[] filterTags){
	    html = html.replaceAll("\\<head>[\\s\\S]*?</head>(?i)", "");//去掉head
	    html = html.replaceAll("\\(http[\\s\\S]*?\\)(?i)", "");//去掉图片地址
	    html = html.replaceAll("\\<!--[\\s\\S]*?-->", "");//去掉注释
	    html = html.replaceAll("\\<![\\s\\S]*?>", "");
	    html = html.replaceAll("\\<style[^>]*>[\\s\\S]*?</style>(?i)", "");//去掉样式
	    html = html.replaceAll("\\<script[^>]*>[\\s\\S]*?</script>(?i)", "");//去掉js
	    html = html.replaceAll("\\<w:[^>]+>[\\s\\S]*?</w:[^>]+>(?i)", "");//去掉word标签
	    html = html.replaceAll("\\<xml>[\\s\\S]*?</xml>(?i)", "");
	    html = html.replaceAll("\\<html[^>]*>|<body[^>]*>|</html>|</body>(?i)", "");
	    html = html.replaceAll("\\\r\n|\n|\r", " ");//去掉换行
	    html = html.replaceAll("\\<br[^>]*>(?i)", "\n\r");
	    List<String> tags = new ArrayList<String>();
	    List<String> s_tags = new ArrayList<String>();
	    List<String> halfTag = Arrays.asList(new String[]{"img","table","thead","th","tr","td"});//
	    if(filterTags != null && filterTags.length > 0){
	      for (String tag : filterTags) {
	        tags.add("<"+tag+(halfTag.contains(tag)?"":">"));//开始标签
	        if(!"img".equals(tag)) tags.add("</"+tag+">");//结束标签
	        s_tags.add("#REPLACETAG"+tag+(halfTag.contains(tag)?"":"REPLACETAG#"));//尽量替换为复杂一点的标记,以免与显示文本混合,如：文本中包含#td、#table等
	        if(!"img".equals(tag)) s_tags.add("#REPLACETAG/"+tag+"REPLACETAG#");
	      }
	    }
	    html = StringUtils.replaceEach(html, tags.toArray(new String[tags.size()]), s_tags.toArray(new String[s_tags.size()]));
	    html = html.replaceAll("\\</p>(?i)", "\n\r");
	    html = html.replaceAll("\\<[^>]+>", "");
	    html = StringUtils.replaceEach(html,s_tags.toArray(new String[s_tags.size()]),tags.toArray(new String[tags.size()]));
	    html = html.replaceAll("\\ ", " ");
	    html = html.replaceAll("#", "");
	    return html.trim();
	}
}
