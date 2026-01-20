package edu.icet.ecom.aop;

import edu.icet.ecom.model.entity.AuditLog;
import edu.icet.ecom.service.impl.AuditLogServiceImpl; // Import the new service
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogServiceImpl auditLogService;

    @AfterThrowing(pointcut = "@annotation(edu.icet.ecom.aop.AuditFailure)", throwing = "ex")
    public void logFailure(JoinPoint joinPoint, Exception ex) {
        AuditLog log = AuditLog.builder()
                .action(joinPoint.getSignature().getName())
                .details("Failure: " + ex.getMessage())
                .timestamp(LocalDateTime.now())
                .userId(1L)
                .build();


        auditLogService.saveLogInNewTransaction(log);
        System.out.println("AUDIT SHADOW: Saved log to DB in separate transaction.");
    }
}