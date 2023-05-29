package com.webservice.demo.service.impl;

import com.webservice.demo.model.HashCode;
import com.webservice.demo.repository.HashCodeRepository;
import com.webservice.demo.service.HashCodeService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HashCodeServiceImpl implements HashCodeService {


    HashCodeRepository hashCodeRepository;
    private Logger logger = Logger.getLogger(HashCodeServiceImpl.class);
    public HashCodeServiceImpl(HashCodeRepository hashCodeRepository) {
        this.hashCodeRepository = hashCodeRepository;
    }

    @Override
    public String save(HashCode hashCode){

        try {
            if (hashCode.isBoIdRequired(hashCode.getBoId()))
                hashCode.setBoId("HASH" + hashCodeRepository.count());
            hashCodeRepository.save(hashCode);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            return "FAIL";
        }
        return "SUCCESS";
    }
}
