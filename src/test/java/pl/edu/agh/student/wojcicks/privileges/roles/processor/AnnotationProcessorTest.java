package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import pl.edu.agh.student.wojcicks.privileges.mocks.MockFactory;
import pl.edu.agh.student.wojcicks.privileges.mocks.annotations.ClientRoleAnnotatedClass;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created on: 2009-07-31 12:25:45 <br/>
 *
 * @author Pawel Machowski
 */
public class AnnotationProcessorTest {

  private static Log log = LogFactory.getLog(AnnotationProcessorTest.class);

  private ProcessorFactory processorFactory;
  private Collection<GrantedAuthority> authorities;
  private MockFactory mockFactory;

  @Before
  public void setUp() {
    mockFactory = new MockFactory();
    processorFactory = new AnnotationProcessorFactory();
    authorities = mockFactory.prepareAuthorities();
  }

  @Test
  public void testCreateSourceRoles() {
    AnnotationProcessor annotationProcessor = (AnnotationProcessor) processorFactory
      .getProcessor(
        ClientRoleAnnotatedClass.class,
        authorities, new GrantedAuthorityProcessingStrategy());

    String[] expectedRoles = {MockFactory.samplePrivilage.toString()};
    String[] createdRoles = annotationProcessor.createSourceRoles();

    assertEquals(expectedRoles[0], createdRoles[0]);
  }
}