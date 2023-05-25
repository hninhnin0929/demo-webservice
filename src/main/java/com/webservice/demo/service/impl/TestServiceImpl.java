package com.webservice.demo.service.impl;

import com.webservice.demo.model.TestProduct;
import com.webservice.demo.repository.TestRepository;
import com.webservice.demo.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public String createTestData(TestProduct testdata) {
        testRepository.save(testdata);
        return "SUCCESS";
    }
}
