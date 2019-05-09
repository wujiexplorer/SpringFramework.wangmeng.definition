package com.wangmeng.doc.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理工具类
 * 创建者 张志朋 
 * 创建时间 2018年3月16日
 */
public class FileUtil {
	/**
	 * 新建文件夹
	 * @Author  张志朋
	 * @param folderPath
	 * @throws Exception  void
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static void newFolder(String folderPath) throws Exception {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新建文件
	 * @Author  张志朋
	 * @param filePathAndName
	 * @param fileContent
	 * @throws Exception  void
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static void newFile(String filePathAndName, String fileContent) throws Exception {
		FileWriter resultFile = null;
		PrintWriter myFile = null;
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			resultFile = new FileWriter(myFilePath);
			myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultFile != null) {
					resultFile.close();
				}
				if (myFile != null) {
					myFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 删除文件夹
	 * @Author  张志朋
	 * @param folderPath
	 * @throws IOException  void
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static void delFolder(String folderPath) throws IOException {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /**
     * 删除文件夹里面的所有文件
     * @Author  张志朋
     * @param path
     * @throws IOException  void
     * @Date	2018年3月16日
     * 更新日志
     * 2018年3月16日  张志朋  首次创建
     *
     */
	public static void delAllFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * @Author  张志朋
	 * @param oldPath
	 * @param newPath
	 * @throws IOException  void
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static void copyFile(String oldPath, String newPath) throws IOException {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {// 文件存在时
				inStream = new FileInputStream(oldPath);// 读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * @Author  张志朋
	 * @param oldPath
	 * @param newPath
	 * @throws IOException  void
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static void copyFolder(String oldPath, String newPath) throws IOException {
		FileInputStream input = null;
		FileOutputStream output = null;
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					input = new FileInputStream(temp);
					output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 移动文件到指定目录
	 *
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) throws Exception {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 获取文件夹下所有文件名称
	 * @Author  张志朋
	 * @param realpath
	 * @return
	 * @throws Exception  List<String>
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static List<String> getFileList(String realpath) throws Exception {
		List<String> fileList = new ArrayList<String>();
		try {
			File f = new File(realpath);
			if (!f.exists()) {
				return fileList;
			}

			File[] fa = f.listFiles();
			for (int i = 0; i < fa.length; i++) {
				File fs = fa[i];
				if (fs.isDirectory()) {
					fileList.add(fs.getName());
				} else {
					fileList.add(fs.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileList;
	}

	/**
	 * 删除文件
	 *
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			if (myDelFile.isFile() && myDelFile.exists()) {
				myDelFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 新建多层目录
	 * @Author  张志朋
	 * @param folderPath
	 * @throws IOException  void
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static void newFolders(String folderPath) throws IOException {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取txt文件的内容
	 * @Author  张志朋
	 * @param file
	 * @return  String
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 修改文件名称
	 * @Author  张志朋
	 * @param oldFileName
	 * @param newFileName  void
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
	public static void renameFile(String  oldFileName,String newFileName){
		File oldFile =  new File(oldFileName);
		File newFile =  new File(newFileName);
		oldFile.renameTo(newFile);
	}
	/**
	 * 获取文件后缀名
	 * @Author  张志朋
	 * @param fileName
	 * @return  String
	 * @Date	2018年3月16日
	 * 更新日志
	 * 2018年3月16日  张志朋  首次创建
	 *
	 */
    public static String getFileType(String fileName) {
        if(fileName!=null && fileName.indexOf(".")>=0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
    /**
     * 判断文件是否为音频
     * @Author  张志朋
     * @param fileName
     * @return  Boolean
     * @Date	2018年3月16日
     * 更新日志
     * 2018年3月16日  张志朋  首次创建
     *
     */
    public static Boolean isAudioFile(String fileName) {
        String [] img_type = new String[]{".mp3", ".ogg"};
        if(fileName==null) {return false;}
        fileName = fileName.toLowerCase();
        for(String type : img_type) {
            if(fileName.endsWith(type)) {return true;}
        }
        return false;
    }
}