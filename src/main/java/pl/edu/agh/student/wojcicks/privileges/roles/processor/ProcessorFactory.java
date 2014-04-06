package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Factory interface used to produce user role processors
 * <p/>
 * Created on: 2009-07-30 15:18:59 <br/>
 *
 * @author Pawel Machowski
 */
public interface ProcessorFactory {

  /**
   * Gets procesor based on procesing source (object that has use rights),
   * role of the user and processing strategy that is used to determine if user has same right as
   * given processing source
   *
   * @param processingSource object that has use rights
   * @param role             role
   * @param strategy         strategy used to determine if user has same right as given processing source
   * @return processor of use rights and user role that uses given strategy
   */
  Processor getProcessor(Object processingSource, Collection<GrantedAuthority> authorities, ProcessingStrategy strategy);
}