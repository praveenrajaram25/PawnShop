package com.source.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LendService {
    ResponseEntity<Resource> processRecord(Map<String, String> customerData);
}
