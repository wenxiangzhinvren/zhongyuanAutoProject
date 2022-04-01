package ${packagePath}.service${packageNameSuffix};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packagePath}.mapper${packageNameSuffix}.${className2}.${className1}Resource;
import ${packagePath}.repository${packageNameSuffix}.${className1}Repository;

@Service
public class ${className1}Service {
	
    @Autowired
    private ${className1}Repository ${className2}Repository;
    
    @Autowired
	private ${className1}Resource ${className2}Resource;
}

