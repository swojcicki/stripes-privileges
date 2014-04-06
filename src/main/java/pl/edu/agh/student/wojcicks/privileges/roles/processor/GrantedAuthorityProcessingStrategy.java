package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * //@todo class description
 * <p/>
 * Created on: 2009-10-08 22:34:08 <br/>
 *
 * @author Pawel Machowski
 */
public class GrantedAuthorityProcessingStrategy extends DefaultProcessingStrategy {

  private static Log log = LogFactory.getLog(GrantedAuthorityProcessingStrategy.class);

  public boolean validatePrivileges(String sourceRole, Collection<GrantedAuthority> authorities) {

    for (GrantedAuthority grantedAuthority : authorities) {
      if (grantedAuthority.getAuthority().equals(sourceRole)) { //user has grantedAuthority
        return BaseRoleProcessor.ROLE_MATCH;
      }
    }
    return BaseRoleProcessor.ROLE_NOT_MATCH;
  }
}