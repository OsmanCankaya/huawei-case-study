package com.huawei.huaweicasestudy.aop;

import com.huawei.huaweicasestudy.entity.OperationType;
import com.huawei.huaweicasestudy.entity.Log;
import com.huawei.huaweicasestudy.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final LogRepository logRepository;

    /**
     * Defines a pointcut for moethod of service classes annotated with @Loggable
     */
    @Pointcut("execution(* com.huawei.huaweicasestudy.service.*.*(..)) && @annotation(com.huawei.huaweicasestudy.aop.annotation.Loggable)")
    public void loggableServiceMethods() {
    }

    @AfterReturning(value = "loggableServiceMethods()", returning = "result")
    public void logAfterCrudOperations(JoinPoint joinPoint, Object result) {
        // Get method infos
        String methodName = joinPoint.getSignature().getName();
        // Determine operation Type (create, read, update, delete,..)
        OperationType operationType = determineOperationType(methodName);

        Log log = new Log(operationType, methodName);
        logRepository.save(log);
    }

    private OperationType determineOperationType(String methodName) {
        if (methodName.startsWith("create") || methodName.startsWith("save")) {
            return OperationType.CREATE;
        } else if (methodName.startsWith("update")) {
            return OperationType.UPDATE;
        } else if (methodName.startsWith("delete")) {
            return OperationType.DELETE;
        } else if (methodName.startsWith("find") || methodName.startsWith("get")) {
            return OperationType.READ;
        }
        return OperationType.OTHER;
    }
}