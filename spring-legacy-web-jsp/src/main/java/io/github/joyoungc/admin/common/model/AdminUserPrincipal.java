package io.github.joyoungc.admin.common.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class AdminUserPrincipal extends User {
	
	private static final long serialVersionUID = 3116022417318157299L;
	
	/**
	 * 추가 User 정보
	 */
	private String userId;
	private String userName;

	public AdminUserPrincipal(String userId, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String userName) {
		
		super(userId, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.userId = userId;
		this.userName = userName;
	}

}
