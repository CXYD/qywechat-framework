/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.modules.test.entity;

import com.wk.common.persistence.DataEntity;
import com.wk.common.supcan.annotation.treelist.cols.SupCol;
import org.hibernate.validator.constraints.Length;

/**
 * 测试Entity
 * @author ThinkGem
 * @version 2013-10-17
 */

public class Test extends DataEntity<Test> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;// 登录名
	private String name; 	// 名称


	@SupCol(text="登录名", sort = 20, minWidth="125px")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@SupCol(text="姓名", sort = 30, minWidth="125px")
	@Length(min=1, max=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}


