package pl.edu.agh.student.wojcicks.privileges.roles.processor;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Base interface for procesing user roles and priviledges given to them
 * <p/>
 * Created on: 2009-07-30 23:30:27 <br/>
 *
 * @author Pawel Machowski
 */
public interface ProcessingStrategy {

  /**
   * Method true if user is allowed to invoke action, otherwise false
   * Compares source roles (roles of given action) and user role to determine if user is alllowed to invoke action
   *
   * @param sourceRoles roles that given action has
   * @param userRole    roles of user
   * @return true if user is allowed to invoke action, otherwise false
   */
  boolean getProcessingResult(String[] sourceRoles, Collection<GrantedAuthority> authorities);

  boolean validatePrivileges(String sourceRole, Collection<GrantedAuthority> authorities);


}