package edu.icet.ecom.service.impl;

import edu.icet.ecom.model.entity.AuditLog;
import edu.icet.ecom.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl {

    private final AuditLogRepository auditLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLogInNewTransaction(AuditLog log) {
        auditLogRepository.save(log);
    }
}