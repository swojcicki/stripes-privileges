package pl.edu.agh.student.wojcicks.privileges.roles.exceptions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Exception thrown when roles framework can't find used UserRole
 * <p/>
 * Created on: 2009-07-28 12:05:37 <br/>
 *
 * @author Pawel Machowski
 */
public class UnknownRoleException extends Exception {

  private static Log log = LogFactory.getLog(UnknownRoleException.class);

  public UnknownRoleException() {
  }

  public UnknownRoleException(String message) {
    super(message);
  }

  public UnknownRoleException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnknownRoleException(Throwable cause) {
    super(cause);
  }
}