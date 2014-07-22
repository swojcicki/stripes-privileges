package pl.edu.agh.student.wojcicks.privileges.roles.interceptors;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.agh.student.wojcicks.privileges.roles.annotations.UseRights;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.AnnotationProcessorFactory;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.GrantedAuthorityProcessingStrategy;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.Processor;
import pl.edu.agh.student.wojcicks.privileges.roles.processor.ProcessorFactory;

import java.lang.reflect.Method;


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

    if (!actionBean.getClass().isAnnotationPresent(UseRights.class)) {
      boolean flag = false;
      for (Method m : actionBean.getClass().getMethods()) {
        if (m.isAnnotationPresent(UseRights.class)) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        return resolution;
      }
    }

    String eventName = context.getActionBeanContext().getEventName();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new AccessDeniedException("Access Denied");
    }

    if (authentication.getCredentials().equals("")) {
      return resolution;
    }

    ProcessorFactory processorFactory = new AnnotationProcessorFactory(eventName);
    Processor processor = processorFactory.getProcessor(actionBean.getClass(), authentication.getAuthorities(), new GrantedAuthorityProcessingStrategy());

    boolean isRoleValid = processor.doProcess();

    if (isRoleValid) {
      return resolution;
    } else {
      throw new AccessDeniedException("Access Denied");
    }
  }
}