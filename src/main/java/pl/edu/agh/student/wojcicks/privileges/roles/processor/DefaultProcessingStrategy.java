package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Base class for all user role procesing strategies
 * <p/>
 * Created on: 2009-07-30 23:26:04 <br/>
 *
 * @author Pawel Machowski
 */
public abstract class DefaultProcessingStrategy implements ProcessingStrategy {

  private static Log log = LogFactory.getLog(DefaultProcessingStrategy.class);

  public boolean getProcessingResult(String[] sourceRoles, Collection<GrantedAuthority> authorities) {
    return processRole(sourceRoles, authorities);
  }

  public boolean processRole(String[] sourceRoles, Collection<GrantedAuthority> authorities) {
    if (sourceRoles == null) {
      return BaseRoleProcessor.ROLE_NOT_MATCH;
    }

    int i = 0;
    for (String sourceRole : sourceRoles) {
      if (validatePrivileges(sourceRole, authorities)) {
        i++;
      }
    }
    if (sourceRoles.length == i) {
      return BaseRoleProcessor.ROLE_MATCH;
    }
    return BaseRoleProcessor.ROLE_NOT_MATCH;
  }

}