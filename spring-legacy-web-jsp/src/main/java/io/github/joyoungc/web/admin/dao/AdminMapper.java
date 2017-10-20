package io.github.joyoungc.web.admin.dao;

import org.apache.ibatis.annotations.Mapper;

import io.github.joyoungc.web.admin.model.Admin;

@Mapper
public interface AdminMapper {
	
	Admin getAdmin(String adminId);

}
	