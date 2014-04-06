package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import pl.edu.agh.student.wojcicks.privileges.roles.exceptions.UnknownRoleException;

/**
 * Base interface used to process user and firm roles
 * Is used to determine if user can see or use selected actions
 * <p/>
 * Created on: 2009-07-27 19:33:53 <br/>
 *
 * @author Pawel Machowski
 */
public interface Processor {

  /**
   * Process all user and firm roles to determine if user can invoke action
   *
   * @return true if user is allowed to invoke action, otherwise false
   * @throws UnknownRoleException if given roles is not implemented in application
   */
  boolean doProcess() throws UnknownRoleException;
}