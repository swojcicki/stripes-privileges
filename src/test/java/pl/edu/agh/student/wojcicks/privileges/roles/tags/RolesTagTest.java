package pl.edu.agh.student.wojcicks.privileges.roles.tags;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.agh.student.wojcicks.privileges.roles.Privileges;
import pl.edu.agh.student.wojcicks.privileges.mocks.MockFactory;
import pl.edu.agh.student.wojcicks.privileges.roles.exceptions.UnknownRoleException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created on: 2009-07-28 19:24:15 <br/>
 *
 * @author Pawel Machowski
 */
public class RolesTagTest {

  private static Log log = LogFactory.getLog(RolesTagTest.class.getName());

  private PrivilegesTag rolesTag;
  private MockFactory mockFactory;
  private UserDetails user;

  @Before
  public void setUp() throws Exception {
    rolesTag = new PrivilegesTag();

    mockFactory = new MockFactory();

    user = mockFactory.prepareUser();


    SecurityContext mockSecurityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(mockSecurityContext);

    when(SecurityContextHolder.getContext()).thenReturn(mockSecurityContext);

    Authentication authentication = mock(Authentication.class);
    when(mockSecurityContext.getAuthentication()).thenReturn(authentication);

    when(authentication.getPrincipal()).thenReturn(user);
  }

  @Test
  @Ignore
  public void testShowTagBodySuccessful() throws UnknownRoleException, ClassNotFoundException {
    rolesTag.setValue(Privileges.READ);
    boolean result = rolesTag.showTagBody();
    assertTrue(result);
  }

  @Test
  @Ignore
  public void testShowTagBodyFailure() throws UnknownRoleException, ClassNotFoundException {
    rolesTag.setValue(MockPrivileges.MOCK_PRIVILAGE);
    try {
      rolesTag.showTagBody();
      fail();
    } catch (UnknownRoleException e) {
      assertTrue(true);
    }
  }

  @Test
  @Ignore
  public void testDoStartTagSkipBody() throws JspException {
    rolesTag.setValue(MockPrivileges.MOCK_PRIVILAGE);
    try {
      rolesTag.doStartTag();
      fail();
    } catch (JspTagException e) {
      assertTrue(true);
    }
  }

  @Test
  @Ignore
  public void testDoStartTagShowBody() throws JspException {
    rolesTag.setValue(Privileges.READ);
    int result = rolesTag.doStartTag();
    assertEquals(result, TagSupport.EVAL_PAGE);
  }

  @Test
  @Ignore
  public void testDoEndTag() {
    assertEquals(rolesTag.doEndTag(), TagSupport.EVAL_PAGE);
  }

  enum MockPrivileges {
    MOCK_PRIVILAGE
  }
}