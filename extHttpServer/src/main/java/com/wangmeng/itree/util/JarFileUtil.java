package com.wangmeng.itree.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JarFileUtil {

    /**
     * 读取已有页面的内容
     *
     * @param filePathInJar
     * @return
     * @throws IOException
     */
    public String readJarFileIndex(String filePathInJar) throws IOException {
        InputStream stream =
                ClassLoader.getSystemResourceAsStream(filePathInJar);
        return readResources(stream);
    }

    /**
     * 配合readJarFileIndex使用
     *
     * @param path
     * @return
     * @throws IOException
     */
    private String readResources(String path) throws IOException {
        InputStream is = this.getClass().getResourceAsStream(path);
        String result = readResources(is);
        return result;
    }

    /**
     * 读取maven的resouce底下的内容
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String readResources(InputStream is) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0) {
                break;
            }
            out.append(buffer, 0, rsz);
        }
        is.close();
        return out.toString();
    }
}