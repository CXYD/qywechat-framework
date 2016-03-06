package com.wk.wechat4j.base.http.entity;

import com.wk.wechat4j.base.http.ContentType;

import java.io.*;


public class FileEntity implements HttpEntity {

	private final File file;
	private final ContentType contentType;

	public FileEntity(File file) {
		this(file, ContentType.DEFAULT_BINARY);
	}

	public FileEntity(File file, ContentType contentType) {
		this.file = file;
		this.contentType = contentType;
	}

	@Override
	public ContentType getContentType() {
		return contentType;
	}

	@Override
	public long getContentLength() {
		return this.file.length();
	}

	@Override
	public InputStream getContent() throws IOException {
		return new FileInputStream(this.file);
	}

	@Override
	public void writeTo(OutputStream outstream) throws IOException {
		InputStream instream = new FileInputStream(this.file);
		try {
			byte[] tmp = new byte[4096];
			int l;
			while ((l = instream.read(tmp)) != -1) {
				outstream.write(tmp, 0, l);
			}
			outstream.flush();
		} finally {
			instream.close();
		}
	}
}
