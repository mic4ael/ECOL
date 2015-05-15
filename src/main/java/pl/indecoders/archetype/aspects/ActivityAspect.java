package pl.indecoders.archetype.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.activity.Activity;
import pl.indecoders.archetype.repository.activity.ActivityRepository;
import pl.indecoders.archetype.security.SecurityUserContext;

@Aspect
@Component
public class ActivityAspect {
	
	@Autowired
	ActivityRepository repo;
	
	@Autowired
	SecurityUserContext ctx;
	
	@Pointcut("within(pl.indecoders.archetype.controller..*)")
	public void userActivities() {
		// POINTCUT
	}
	
	@Before("userActivities()")
	public void logUserActivity(JoinPoint jp) {
		if (ctx.getSignedUser() != null) {
			String className = jp.getSignature().getDeclaringTypeName();
			String controllerName = className.substring(className.lastIndexOf(".") + 1);
			
			Activity activity = new Activity();
			activity.setActivity(jp.getSignature().getName() + "@" + controllerName);
			activity.setUser(ctx.getSignedUser());
			
			repo.save(activity);
			repo.flush();
		}
	}
}
