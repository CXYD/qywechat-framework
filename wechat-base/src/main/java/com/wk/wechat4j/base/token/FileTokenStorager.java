package com.wk.wechat4j.base.token;

import com.wk.wechat4j.base.exception.WeixinException;
import com.wk.wechat4j.base.model.Token;
import com.wk.wechat4j.base.util.FileUtil;
import com.wk.wechat4j.base.xml.XmlStream;

import java.io.*;


/**
 * 用FILE保存TOKEN
 *
 * @className FileTokenStorager
 * @author jy
 * @date 2015年1月9日
 * @since JDK 1.6
 */
public class FileTokenStorager implements TokenStorager {

	private final String cachePath;

	public FileTokenStorager(String cachePath) {
		this.cachePath = cachePath;
	}

	@Override
	public Token lookup(String cacheKey) throws WeixinException {
		File token_file = new File(String.format("%s/%s.xml", cachePath,
				cacheKey));
		try {
			if (token_file.exists()) {
				Token token = XmlStream.fromXML(
						new FileInputStream(token_file), Token.class);
				if (token.getCreateTime() < 0) {
					return token;
				}
				if ((token.getCreateTime() + (token.getExpiresIn() * 1000l) - 2) > System
						.currentTimeMillis()) {
					return token;
				}
			}
			return null;
		} catch (IOException e) {
			throw new WeixinException(e);
		}
	}

	@Override
	public void caching(String cacheKey, Token token) throws WeixinException {
		try {
			XmlStream.toXML(
					token,
					new FileOutputStream(new File(String.format("%s/%s.xml",
							cachePath, cacheKey))));
		} catch (IOException e) {
			throw new WeixinException(e);
		}
	}

	@Override
	public Token evict(String cacheKey) {
		Token token = null;
		File token_file = new File(String.format("%s/%s.xml", cachePath,
				cacheKey));
		try {
			if (token_file.exists()) {
				token = XmlStream.fromXML(new FileInputStream(token_file),
						Token.class);
				token_file.delete();
			}
		} catch (IOException e) {
			; // ingore
		}
		return token;
	}

	@Override
	public void clear() {
		File[] files = new File(cachePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isFile()
						&& "xml".equals(FileUtil.getFileExtension(file
						.getName()));
			}
		});
		for (File token : files) {
			token.delete();
		}
	}
}
