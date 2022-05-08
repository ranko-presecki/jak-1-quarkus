package jak.meetup.logging.interceptor;

import io.quarkus.arc.log.LoggerName;
import org.jboss.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@LogPerformance
@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 200)
public class LogPerformanceInterceptor {

    @LoggerName("FILE")
    Logger fileLog;

    @LoggerName("PERFORMANCE")
    Logger performanceLog;

    @AroundInvoke
    public Object logMethodCall(InvocationContext invocationContext) throws Exception {
        String className = invocationContext.getMethod().getDeclaringClass().getSimpleName();
        String methodName = invocationContext.getMethod().getName();

        fileLog.infof("[%s.%s] Request received", className, methodName);

        long startTime = System.currentTimeMillis();
        Object ret = invocationContext.proceed();
        long stopTime = System.currentTimeMillis();

        fileLog.infof("[%s.%s] Response sent", className, methodName);
        performanceLog.debugf("[%s.%s] [performance in ms]: %s", className, methodName, (stopTime - startTime));
        return ret;
    }
}