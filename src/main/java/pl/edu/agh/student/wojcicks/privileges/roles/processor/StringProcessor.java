package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pl.edu.agh.student.wojcicks.privileges.roles.Privileges;
import pl.edu.agh.student.wojcicks.privileges.roles.exceptions.UnknownRoleException;

/**
 * //@todo class description
 * <p/>
 * Created on: 2009-07-30 15:08:18 <br/>
 *
 * @author Pawel Machowski
 */
public class StringProcessor extends BaseRoleProcessor {

  private static Log log = LogFactory.getLog(StringProcessor.class.getName());

  private String roles;
  public static final String DELIMITER = ",";

  public StringProcessor(Object roles) {
    this.roles = (String) roles;
  }

  public String[] createSourceRoles() throws UnknownRoleException {
    if (roles == null) {
      return null;
    }
    String[] sourceRoles = roles.split(DELIMITER);
    for (int i = 0; i < sourceRoles.length; i++) {
      sourceRoles[i] = sourceRoles[i].trim();
    }
    spellcheckRoles(sourceRoles);
    return sourceRoles;
  }

  private void spellcheckRoles(String[] sourceRoles) throws UnknownRoleException {
    for (String role : sourceRoles) {
      try {
        Privileges.valueOf(role);
      } catch (IllegalArgumentException e) {
        throw new UnknownRoleException(role);
      }
    }
  }
}