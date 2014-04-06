package pl.edu.agh.student.wojcicks.privileges.roles.annotations;

import pl.edu.agh.student.wojcicks.privileges.roles.Privileges;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to mark action methods and action beans for user roles
 * <p/>
 * Created on: 2009-07-27 12:23:25 <br/>
 *
 * @author Pawel Machowski
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface UseRights {
  Privileges[] value();
}
