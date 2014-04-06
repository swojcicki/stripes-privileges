package pl.edu.agh.student.wojcicks.privileges.mocks;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.agh.student.wojcicks.privileges.roles.Privileges;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <p/>
 * Created on: 2009-04-13 17:09:58 <br/>
 *
 * @author Pawel Machowski
 */
public class MockFactory {

  private static Log log = LogFactory.getLog(MockFactory.class.getName());

  public static final Privileges samplePrivilage = Privileges.READ;


  public UserDetails prepareUser() {
    UserDetails user = mock(User.class);
    when(user.isEnabled()).thenReturn(true);
    when(user.getPassword()).thenReturn("samplePassword");
    when(user.getUsername()).thenReturn("user");
    when(user.getAuthorities()).thenReturn(prepareAuthorities());
    return user;
  }

  public Collection<GrantedAuthority> prepareAuthorities() {
    GrantedAuthority authority = mock(GrantedAuthorityImpl.class);
    when(authority.getAuthority()).thenReturn(Privileges.READ.toString());

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(authority);
    return authorities;
  }

}