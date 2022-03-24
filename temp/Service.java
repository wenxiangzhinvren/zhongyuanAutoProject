package com.mims.csms.ky.salary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mims.csms.ky.salary.mapper.${className2}.${className1}Resource;
import com.mims.csms.ky.salary.repository.${className1}Repository;

@Service
public class ${className1}Service {
	
    @Autowired
    private ${className1}Repository ${className2}Repository;
    
    @Autowired
	private ${className1}Resource ${className2}Resource;
}

