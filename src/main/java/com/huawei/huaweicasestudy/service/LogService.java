package com.huawei.huaweicasestudy.service;

import com.huawei.huaweicasestudy.entity.Log;
import com.huawei.huaweicasestudy.mapper.LogMapper;
import com.huawei.huaweicasestudy.payload.request.log.LogSearchRequest;
import com.huawei.huaweicasestudy.payload.response.log.LogResponse;
import com.huawei.huaweicasestudy.repository.LogRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final LogMapper logMapper;

    @Value("${spring.data.web.pageable.default-page-size}")
    private int defaultPageSize;

    public Page<LogResponse> getLogs(LogSearchRequest logSearchRequest) {
        Specification<Log> spec = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (logSearchRequest.operationType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("operationType"), logSearchRequest.operationType()));
            }
            if (logSearchRequest.start() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdTime"), logSearchRequest.start()));
            }
            if (logSearchRequest.end() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdTime"), logSearchRequest.end()));
            }
            if (logSearchRequest.methodName() != null) {
                predicates.add(criteriaBuilder.like(root.get("methodName"), "%" + logSearchRequest.methodName() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        // Paging operations
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        int page = logSearchRequest.page() == null ? 0 : logSearchRequest.page();
        Pageable pageable = PageRequest.of(page, defaultPageSize, sort);

        Page<Log> logs = logRepository.findAll(spec, pageable);
        return logs.map(logMapper::mapToLogResponse);
    }
}