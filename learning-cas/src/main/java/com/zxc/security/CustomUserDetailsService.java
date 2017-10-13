package com.zxc.security;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/9/22.
 * <p>
 * ------------------ _ooOoo_-------------------
 * ------------------o8888888o------------------
 * ------------------88" . "88------------------
 * ------------------(| -_- |)------------------
 * ------------------O\  =  /O------------------
 * ---------------____/`---'\____---------------
 * -------------.'  \\|     |//  `.-------------
 * ------------/  \\|||  :  |||//  \------------
 * -----------/  _||||| -:- |||||-  \-----------
 * -----------|   | \\\  -  /// |   |-----------
 * -----------| \_|  ''\---/''  |   |-----------
 * -----------\  .-\__  `-`  ___/-. /-----------
 * ---------___`. .'  /--.--\  `. . __----------
 * ------."" '<  `.___\_<|>_/___.'  >'"".-------
 * -----| | :  `- \`.;`\ _ /`;.`/ - ` : | |-----
 * -----\  \ `-.   \_ __\ /__ _/   .-` /  /-----
 * ======`-.____`-.___\_____/___.-`____.-'======
 * -------------------`=---='-------------------
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * -----------佛祖保佑 BUG DIE DIE DIE-----------
 * =============================================
 */
public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        String login = token.getPrincipal().toString();
        String username = login.toLowerCase();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new AppUserDetails(username, grantedAuthorities);
    }
}
