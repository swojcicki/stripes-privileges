package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Class used to create
 * <p/>
 * Created on: 2009-07-27 19:32:42 <br/>
 *
 * @author Pawel Machowski
 */
public class AnnotationProcessorFactory implements ProcessorFactory {

  private String eventName;

  public AnnotationProcessorFactory() {
  }

  public AnnotationProcessorFactory(String eventName) {
    this.eventName = eventName;
  }

  public Processor getProcessor(Object source, Collection<GrantedAuthority> authorities, ProcessingStrategy strategy) {
    AnnotationProcessor processor;
    if (eventName == null) {
      processor = new AnnotationProcessor(source);
    } else {
      processor = new AnnotationProcessor(source, eventName);
    }

    processor.setProcessingStrategy(strategy);
    processor.setAuthorities(authorities);
    return processor;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }
}