package com.code.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Description: 通过 HTTP 的方式读取云服务的文件，我们也可以把配置文件放到 GitHub 或者
 * Gitee
 * Create by blacktom on 2021/08/15
 **/
public class UrlResource implements Resource {

	private final URL url;

	public UrlResource(URL url) {
		Assert.notNull(url, "URL must not be null");
		this.url = url;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		URLConnection connection = this.url.openConnection();

		try {
			return connection.getInputStream();
		} catch (IOException ex) {
			if (connection instanceof HttpURLConnection) {
				((HttpURLConnection) connection).disconnect();
			}
			throw ex;
		}
	}
}
