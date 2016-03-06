package com.wk.wechat4j.base.http.entity;

import com.wk.wechat4j.base.http.ContentType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public interface HttpEntity {
	
	ContentType getContentType();

	long getContentLength();

	InputStream getContent() throws IOException;

	void writeTo(OutputStream outstream) throws IOException;
}
