package com.mycompany.testproj.dao;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("SELECT sysdate FROM dual")
	public String getTime();

	public String getTime2();

}