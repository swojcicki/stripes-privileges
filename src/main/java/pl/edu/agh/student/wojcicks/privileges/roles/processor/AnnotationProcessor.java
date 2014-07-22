package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import net.sourceforge.stripes.action.HandlesEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pl.edu.agh.student.wojcicks.privileges.roles.Privileges;
import pl.edu.agh.student.wojcicks.privileges.roles.annotations.UseRights;

import java.lang.reflect.Method;

/**
 * Class used to process classes anotated with UserRights.
 * AnnotationProcessor looks for anotatation on type name and methods
 * then creates source roles based on found annotations.
 * If there is no method with specyfied event name, search for HandlessEvent Stripes annotation.
 * Annotations on methods have higher priority then anotations on type.
 * <p/>
 * <p/>
 * Created on: 2009-07-30 15:08:35 <br/>
 *
 * @author Pawel Machowski
 */
public class AnnotationProcessor extends BaseRoleProcessor {

  private static Log log = LogFactory.getLog(AnnotationProcessor.class);

  private Class<?> beanClass;
  private String eventName;

  public AnnotationProcessor(Object beanClass) {
    this.beanClass = (Class<?>) beanClass;
  }

  public AnnotationProcessor(Object beanClass, String eventName) {
    this.beanClass = (Class<?>) beanClass;
    this.eventName = eventName;
  }

  public String[] createSourceRoles() {
    UseRights useRights;
    Method methodWihUseRights = null;
    if (eventName != null) {
      methodWihUseRights = findMethodWithUseRights();
    }

    if (methodWihUseRights != null) {  //get annotation from method
      useRights = methodWihUseRights.getAnnotation(UseRights.class);
    } else { //get annotation from type
      useRights = beanClass.getAnnotation(UseRights.class);
    }

    if (useRights == null) {
      return null;
    }

    return getPrivilagesFromUseRights(useRights);
  }

  private String[] getPrivilagesFromUseRights(UseRights useRights) {
    Privileges[] enumPrivilages = useRights.value();
    String[] stringPrivilages = new String[enumPrivilages.length];
    //convert enum array to string array
    for (int i = 0; i < enumPrivilages.length; i++) {
      stringPrivilages[i] = enumPrivilages[i].toString();
    }

    return stringPrivilages;
  }

  private Method findMethodWithUseRights() {
    Method method = findMethodWithEventName();
    boolean methodWithEvenNameExist = method != null;
    if (!methodWithEvenNameExist) {
      return null;
    }

    boolean methodHasUseRights = method.getAnnotation(UseRights.class) != null;
    if (methodHasUseRights) {
      return method;
    }

    return null;
  }

  private Method findMethodWithEventName() {
    try {
      Method method = beanClass.getMethod(eventName);
      return method;

    } catch (NoSuchMethodException e) {
      //if there is no method with such event name, look for @HandlesEvent
      Method[] methods = beanClass.getDeclaredMethods();
      HandlesEvent handlesEvent;
      for (Method method : methods) {
        handlesEvent = method.getAnnotation(HandlesEvent.class);
        if (handlesEvent != null && handlesEvent.value().equals(eventName)) {
          return method;
        }
      }
    }
    return null;
  }
}