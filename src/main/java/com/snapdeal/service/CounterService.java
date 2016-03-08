package com.snapdeal.service;

import org.springframework.stereotype.Service;

@Service
public interface CounterService {

	public void createCounter(Long liquidationId);
}
