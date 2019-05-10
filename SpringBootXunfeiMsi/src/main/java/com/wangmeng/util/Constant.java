package com.wangmeng.util;

public class Constant {
	/**
	 * 分割符
	 * File.separator才是用来分隔同一个路径字符串中的目录的 例如C:\Program Files\Common Files
	 * line.separator在这个平台下行与行之间的分隔符 相当于“\n”
	 * path.separator与系统有关的路径分隔符。此字段被初始为包含系统属性 path.separator 值的第一个字符。
	 * 此字符用于分隔以路径列表形式给定的文件序列中的文件名。在 UNIX 系统上，此字段为 ':'；在 Microsoft Windows 系统上，它为 ';'。
	 */
	public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");//文件分隔符
	public static final String SF_LINE_SEPARATOR = System.getProperty("line.separator");//行分隔符
	public static final String SF_PATH_SEPARATOR = System.getProperty("path.separator");//路径分隔符
}
