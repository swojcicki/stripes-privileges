package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import pl.edu.agh.student.wojcicks.privileges.roles.Privileges;
import pl.edu.agh.student.wojcicks.privileges.mocks.MockFactory;
import pl.edu.agh.student.wojcicks.privileges.roles.exceptions.UnknownRoleException;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * //@todo class description
 * <p/>
 * Created on: 2009-10-09 10:58:08 <br/>
 *
 * @author Pawel Machowski
 */
public class UserAndFirmRoleProcessingStrategyTest {

  private static Log log = LogFactory.getLog(UserAndFirmRoleProcessingStrategyTest.class.getName());

  private Collection<GrantedAuthority> authorities;
  private ProcessingStrategy strategy;

  @Before
  public void setUp() throws Exception {
    authorities = new MockFactory().prepareAuthorities();
  }

  @Test
  public void testValidatePrivilegesSuccess() throws UnknownRoleException {
    strategy = new GrantedAuthorityProcessingStrategy();
    boolean isRoleValid = strategy.validatePrivileges(Privileges.READ.toString(), authorities);
    assertTrue(isRoleValid);
  }

  @Test
  public void testValidatePrivilegesNoneHasRole() throws UnknownRoleException {
    strategy = new GrantedAuthorityProcessingStrategy();
    boolean isRoleValid = strategy.validatePrivileges(Privileges.CREATE.toString(), authorities);
    assertFalse("no one have role, validation should fail", isRoleValid);
  }

}