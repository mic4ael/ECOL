
package pl.indecoders.archetype.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilingAspect {
	
	@Autowired
	private Logger log;
	
	@Around("within(pl.indecoders.archetype.controller..*)")
	public Object profileMethodExecution(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object retValue = pjp.proceed();
		startTime = System.currentTimeMillis() - startTime;
		
		String className = pjp.getSignature().getDeclaringTypeName();
		String controllerName = className.substring(className.lastIndexOf(".") + 1);
		
		log.debug("Method executed: " 
			+ pjp.getSignature().getName()
			+ ", at controller: " 
			+ controllerName
			+ ", in " 
			+ startTime / 1000.0 
			+ " seconds"
		);
		
		return retValue;
	}
}
