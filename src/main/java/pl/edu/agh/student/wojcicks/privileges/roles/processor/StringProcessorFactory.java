package pl.edu.agh.student.wojcicks.privileges.roles.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * //@todo class description
 * <p/>
 * Created on: 2009-07-30 15:18:38 <br/>
 *
 * @author Pawel Machowski
 */
public class StringProcessorFactory implements ProcessorFactory {

  private static Log log = LogFactory.getLog(StringProcessorFactory.class);

  public Processor getProcessor(Object processingSource, Collection<GrantedAuthority> authorities, ProcessingStrategy strategy) {
    StringProcessor processor = new StringProcessor(processingSource);
    processor.setProcessingStrategy(strategy);
    processor.setAuthorities(authorities);
    return processor;
  }
}