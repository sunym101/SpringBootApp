package com.sunym.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sunym.model.UserModel;

/**
 * dao层接口
 * 
 * @author Sunyongmeng
 * @since 2019-01-03
 */
@Mapper
public interface UserMapper {

	/**
	 * 取得最大的密码履历序号
	 * 
	 * @param userid
	 * @return
	 */
	UserModel getUserByName(@Param("username") String username);

}
