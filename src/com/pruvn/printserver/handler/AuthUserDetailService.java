package com.pruvn.printserver.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pruvn.printserver.entity.UserRoleM;
import com.pruvn.printserver.entity.Usermaster;
import com.pruvn.printserver.entity.UsermasterLog;
import com.pruvn.printserver.services.UserRoleMService;
import com.pruvn.printserver.services.UsermasterLogService;
import com.pruvn.printserver.services.UsermasterService;
import com.pruvn.printserver.utils.Constant;
import com.pruvn.printserver.utils.DateUtils;
import com.pruvn.printserver.utils.ParameterApplication;

public class AuthUserDetailService implements UserDetailsService {

	private UsermasterService usermasterServer;
	private UserRoleMService userRoleMService;
	private UsermasterLogService usermasterLogService;

	public UsermasterLogService getUsermasterLogService() {
		return usermasterLogService;
	}
	@Autowired
	public void setUsermasterLogService(UsermasterLogService usermasterLogService) {
		this.usermasterLogService = usermasterLogService;
	}

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {
		try {
			Usermaster login_user = usermasterServer.getUserByUserName(arg0);
			
			if (login_user == null) {
				throw new UsernameNotFoundException("Credential is not found");
			}
			
			boolean enabled = true;//User is disabled
			boolean accountNonExpired = true;//User account has expired
			boolean credentialsNonExpired = true;//User credentials have expired
			boolean accountNonLocked = true;//User account is locked
			
			if ((login_user.getLast_changed_pw() != null && DateUtils.countDaysBetween(login_user.getLast_changed_pw(), Calendar.getInstance().getTime()) > 90)) {
				accountNonExpired = false;
			}
				
			if (login_user != null) {
				accountNonLocked = login_user.getStatus() == null ? true : ((long)login_user.getStatus()==(long)ParameterApplication.BLOCK.getStatus() ? false : true);
				//save log
			}
			
			return new User(login_user.getUsername(), login_user.getPassword(), enabled, accountNonExpired,
					credentialsNonExpired, accountNonLocked,
					getAuthorities(login_user));

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
	public Collection<? extends GrantedAuthority> getAuthorities(Usermaster user) {
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
	public List<String> getRoles(Usermaster user) {
		List<String> roles = new ArrayList<String>();
		
		List<UserRoleM> lst = userRoleMService.getRole(user.getRole_id());
		
		for (UserRoleM role : lst) {
			roles.add(role.getRole_name());
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
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	public UsermasterService getUsermasterServer() {
		return usermasterServer;
	}
	@Autowired
	public void setUsermasterServer(UsermasterService usermasterServer) {
		this.usermasterServer = usermasterServer;
	}

	public UserRoleMService getUserRoleMService() {
		return userRoleMService;
	}
	@Autowired
	public void setUserRoleMService(UserRoleMService userRoleMService) {
		this.userRoleMService = userRoleMService;
	}

	/**
	 * @return the userMService
	 */

	/**
	 * @param userMService
	 *            the userMService to set
	 */
	

}