/*
 * Created: liushen@Dec 1, 2008 3:24:47 PM
 */
package com.cloud.test.controller;

import java.io.*;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP响应工具类.<br>
 * 
 * @author TRS信息技术有限公司
 */
public class ResponseUtil {
	/**
	 *
	 */
	private final static Logger LOG = LoggerFactory.getLogger(ResponseUtil.class);

	/**
	 * 构造函数私有.
	 */
	protected ResponseUtil() {
	}

	/**
	 * 给HTTP响应头添加清空缓存标记.
	 */
	public static void clearCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store"); // FireFox
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1);
		response.setDateHeader("max-age", 0);
	}
	/**
	 * 以二进制流的方式下载文件。
	 * 
	 * @param response
	 *            HTTP响应
	 * @param fileName
	 *            显示给客户端的文件名
	 * @param file
	 *            要下载的文件
	 * @throws IOException
	 * @since liushen @ Mar 15, 2010
	 */
	public static void download(HttpServletResponse response, String fileName,
			String path) throws IOException {
		File file = new File(path);
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		// TODO: liushen@Mar 15, 2010: 1)大于2G如何处理; 2)文件名编码
		response.setContentLength((int) file.length());

		OutputStream os = response.getOutputStream();
		copy(file, os);
	}
	
	static final int BUF_SIZE = 4096;
	public static void copy(File file, OutputStream os) throws IOException {
		InputStream is = new FileInputStream(file);
		int bufSize = BUF_SIZE;
		long totalSize = 0;
//		copy(is, os);
		long totalTransferred = 0;
		BufferedInputStream bis = new BufferedInputStream(is, bufSize);
		BufferedOutputStream bos = new BufferedOutputStream(os, bufSize);
		int len = 0;
		if (bufSize < 1024) {
			bufSize = 1024;
		}
		byte[] buf = new byte[bufSize];
		try {
			while ((len = bis.read(buf)) > 0) {
				bos.write(buf, 0, len);
				totalTransferred += len;
			}
		} finally {
			bos.flush();
		}
	}
}
