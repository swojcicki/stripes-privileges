package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import pl.edu.agh.student.wojcicks.privileges.roles.Privileges;
import pl.edu.agh.student.wojcicks.privileges.mocks.MockFactory;
import pl.edu.agh.student.wojcicks.privileges.roles.exceptions.UnknownRoleException;

import java.util.Collection;

/**
 * Created on: 2009-07-31 11:36:54 <br/>
 *
 * @author Pawel Machowski
 */
public class StringProcessorTest extends TestCase {

  private static Log log = LogFactory.getLog(StringProcessorTest.class.getName());

  private StringProcessor stringProcessor;
  private ProcessorFactory processorFactory;
  private String sourceRoles;
  private Collection<GrantedAuthority> authorities;
  private MockFactory mockFactory;

  @Override
  public void setUp() {
    mockFactory = new MockFactory();
    authorities = mockFactory.prepareAuthorities();
    sourceRoles = MockFactory.samplePrivilage.toString();
    processorFactory = new StringProcessorFactory();
  }

  public void testCreateSourceRoles() throws UnknownRoleException {
    stringProcessor = (StringProcessor) processorFactory.getProcessor(sourceRoles, authorities, new GrantedAuthorityProcessingStrategy());

    String[] expectedRoles = {MockFactory.samplePrivilage.toString()};
    String[] createdRoles = stringProcessor.createSourceRoles();

    assertEquals(expectedRoles[0], createdRoles[0]);
  }

  public void testCreateSourceRolesWithNullRoles() throws UnknownRoleException {
    sourceRoles = null;
    stringProcessor = (StringProcessor) processorFactory.getProcessor(sourceRoles, authorities, new GrantedAuthorityProcessingStrategy());
    stringProcessor.createSourceRoles();
    assertNull(stringProcessor.createSourceRoles());
  }

  public void testWrongSpelledRole() {
    String misspelledRoleSign = "${";
    sourceRoles = Privileges.READ.toString() + misspelledRoleSign;
    stringProcessor = (StringProcessor) processorFactory.getProcessor(sourceRoles, authorities, new GrantedAuthorityProcessingStrategy());
    try {
      stringProcessor.createSourceRoles();
      fail("Unknown exception should be thrown");
    } catch (UnknownRoleException e) {
      assertEquals(e.getMessage(), sourceRoles);
    }
  }
}