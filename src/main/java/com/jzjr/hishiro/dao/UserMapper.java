package com.jzjr.hishiro.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {

    String selectUserNameById(@Param("id") int id);
}
