package io.github.joyoungc.admin.common.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.joyoungc.admin.common.model.UserPrincipal;
import io.github.joyoungc.admin.user.dao.UserDao;
import io.github.joyoungc.admin.user.model.User;
import io.github.joyoungc.admin.user.model.User.Authority;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		/**
		 * 로그인할 사용자 정보 조회
		 */
		User user = userDao.getUser(userId);

		if (user == null) {
			throw new UsernameNotFoundException("아이디가 일치하지 않습니다.");
		}

		/**
		 * 권한 체크
		 */
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(user.getAuthorities())) {
			for (Authority obj : user.getAuthorities()) {
				authorities.add(new SimpleGrantedAuthority(obj.getAuthCode()));
			}
		}

		return new UserPrincipal(user.getUserId(), user.getPassword(), user.getEnabled(), true, true, true, authorities,
				user.getUserName());

	}

}
