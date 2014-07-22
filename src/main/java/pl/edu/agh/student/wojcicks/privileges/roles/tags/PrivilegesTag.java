package pl.edu.agh.student.wojcicks.privileges.roles.tags;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.agh.student.wojcicks.privileges.roles.exceptions.UnknownRoleException;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.GrantedAuthorityProcessingStrategy;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.Processor;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.ProcessorFactory;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.StringProcessorFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag used to evaluate if user has right to see content.
 * <p/>
 * Created on: 2009-07-27 13:01:03 <br/>
 *
 * @author Pawel Machowski
 */
public class PrivilegesTag extends TagSupport {

  private static Log log = LogFactory.getLog(PrivilegesTag.class);

  private String value;

  public int doStartTag() throws JspException {
    try {
      if (showTagBody()) {
        return EVAL_PAGE;
      } else {
        return SKIP_BODY;
      }
    } catch (UnknownRoleException e) {
      throw new JspTagException("Unknown usage role: " + e.getMessage());
    }
  }

  /**
   * Method evaluate if user has right to see content.
   *
   * @return true if user can see tag content, otherwise false
   * @throws UnknownRoleException if role can't be found
   */
  public boolean showTagBody() throws UnknownRoleException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    ProcessorFactory processorFactory = new StringProcessorFactory();
    Processor processor = processorFactory.getProcessor(this.getValue(), authentication.getAuthorities(), new GrantedAuthorityProcessingStrategy());
    return processor.doProcess();
  }

  public int doEndTag() {
    return EVAL_PAGE;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}