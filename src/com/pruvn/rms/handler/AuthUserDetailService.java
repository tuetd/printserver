package com.pruvn.rms.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pruvn.rms.domain.GroupM;
import com.pruvn.rms.domain.UserM;
import com.pruvn.rms.service.UserLogService;
import com.pruvn.rms.service.UserMService;
import com.pruvn.rms.service.exceptions.UserException;
import com.pruvn.rms.utils.Constant.ParameterApplication;
import com.pruvn.rms.utils.DateUtils;

public class AuthUserDetailService implements UserDetailsService {

	private UserMService userMService;
	private UserLogService userLogService;
	

	public UserLogService getUserLogService() {
		return userLogService;
	}

	public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
	}

	
	
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {
		try {
 			arg0 = StringEscapeUtils.unescapeHtml(arg0);
			UserM login_user = userMService.getUserByUserName(arg0);
			
			if (login_user == null) {
				throw new UsernameNotFoundException("Credential is not found");
			}

			boolean enabled = false;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			
			//TODO 
			/*if (login_user.getLastChangedPw() == null || 
					(login_user.getLastChangedPw() != null && DateUtils.countDaysBetween(login_user.getLastChangedPw(), Calendar.getInstance().getTime()) > 90)) {
				credentialsNonExpired = false;
			}
			
			if (login_user != null) {
				credentialsNonExpired = login_user.getLastChangedPw() == null ? false : true;
			}*/
			
			if (login_user != null) {
				//enabled = login_user.getIsActived() == null ? true : (login_user.getIsActived().equalsIgnoreCase("0") ? false : true);
				enabled = login_user.getIsActived() != null && (login_user.getIsActived() == ParameterApplication.ACTIVE.getStatus());
			}
			
			User author=  new User(login_user.getUsername(), login_user.getPassword(), enabled, accountNonExpired,
					credentialsNonExpired, accountNonLocked,
					getAuthorities(login_user));
			return author;

		} catch (UserException e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical
	 * role
	 * 
	 * @param role
	 *            the numerical role
	 * @return a collection of {@link GrantedAuthority

	 */
	public Collection<? extends GrantedAuthority> getAuthorities(UserM user) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(user));
		
		return authList;
	}

	/**
	 * Converts a numerical role to an equivalent list of roles
	 * 
	 * @param role
	 *            the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(UserM user) {
		List<String> roles = new ArrayList<String>();
		
		List<GroupM> lst = userMService.getAllGroupByUser(user);
		
		for (GroupM groupM : lst) {
			roles.add(groupM.getGroupcode());
		}
		
		return roles;
	}

	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * 
	 * @param roles
	 *            {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new GrantedAuthorityImpl(role));
		}
		return authorities;
	}

	/**
	 * @return the userMService
	 */
	public UserMService getUserMService() {
		return userMService;
	}

	/**
	 * @param userMService
	 *            the userMService to set
	 */
	@Autowired
	public void setUserMService(UserMService userMService) {
		this.userMService = userMService;
	}

}