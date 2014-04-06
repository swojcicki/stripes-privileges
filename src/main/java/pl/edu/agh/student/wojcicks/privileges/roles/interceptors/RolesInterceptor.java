package pl.edu.agh.student.wojcicks.privileges.roles.interceptors;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.AnnotationProcessorFactory;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.GrantedAuthorityProcessingStrategy;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.Processor;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.ProcessorFactory;

import javax.servlet.http.HttpServletResponse;


/**
 * Interceptor used to validate if user has right to invoke current action
 * <p/>
 * Created on: 2009-07-30 20:16:27 <br/>
 *
 * @author Pawel Machowski
 */
@Intercepts(LifecycleStage.BindingAndValidation)
public class RolesInterceptor implements Interceptor {

  private static Log log = LogFactory.getLog(RolesInterceptor.class);

  public Resolution intercept(ExecutionContext context) throws Exception {
    Resolution resolution = context.proceed();
    ActionBean actionBean = context.getActionBean();
    String eventName = context.getActionBeanContext().getEventName();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getCredentials();

    ProcessorFactory processorFactory = new AnnotationProcessorFactory(eventName);
    Processor processor = processorFactory.getProcessor(actionBean.getClass(), user.getAuthorities(), new GrantedAuthorityProcessingStrategy());

    boolean isRoleValid = processor.doProcess();

    if (isRoleValid) {
      return resolution;
    } else {
      return new ErrorResolution(HttpServletResponse.SC_FORBIDDEN);
    }
  }
}