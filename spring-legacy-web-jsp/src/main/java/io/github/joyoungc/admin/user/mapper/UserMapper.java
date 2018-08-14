/**
 * UserMapper
 *
 * @author	Joyoungc
 * @date	2017.10.20
 */
package io.github.joyoungc.admin.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.github.joyoungc.admin.user.model.User;

@Mapper
public interface UserMapper {

	List<User> selectUser(User user);

	User getUser(String userId);

	int updateUser(User user);

	int createUser(User user);

	int deleteUser(String userId);

}
