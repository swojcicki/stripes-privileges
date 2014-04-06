package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import pl.edu.agh.student.wojcicks.privileges.roles.exceptions.UnknownRoleException;

import java.util.Collection;

/**
 * //@todo class description
 * <p/>
 * Created on: 2009-07-28 15:41:47 <br/>
 *
 * @author Pawel Machowski
 */
public abstract class BaseRoleProcessor implements Processor {

  private static Log log = LogFactory.getLog(BaseRoleProcessor.class);

  public static final boolean ROLE_MATCH = true;
  public static final boolean ROLE_NOT_MATCH = false;

  private Collection<GrantedAuthority> authorities;

  private ProcessingStrategy processingStrategy;

  public boolean doProcess() throws UnknownRoleException {
    return processingStrategy.getProcessingResult(createSourceRoles(), getAuthorities());
  }

  public abstract String[] createSourceRoles() throws UnknownRoleException;


  public Collection<GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  public void setProcessingStrategy(ProcessingStrategy processingStrategy) {
    this.processingStrategy = processingStrategy;
  }
}