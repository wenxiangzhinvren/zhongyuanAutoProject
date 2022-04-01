package ${packagePath}.web.capi.v1${packageNameSuffix};

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mims.csms.common.core.facade.ResponsePayload;
import com.mims.csms.common.utils.PageableUtil;
import com.mims.csms.common.utils.RsqlCustUtils;
import com.mims.csms.common.utils.StringUtil;

import ${clazz};
import ${packagePath}.service${packageNameSuffix}.${className1}Service;
import ${packagePath}.repository${packageNameSuffix}.${className1}Repository;
import ${packagePath}.mapper${packageNameSuffix}.${className2}.${className1}Resource;

@RestController
@RequestMapping("/capi/v1${requestMappingSuffix}/${actionPath}")
public class ${className1}Controller {
	
	@Autowired
	private ${className1}Service ${className2}Service;
	
	@Autowired
	private ${className1}Repository ${className2}Repository;
	
	@Autowired
	private ${className1}Resource ${className2}Resource;
	
	@GetMapping
	public ResponsePayload list(String search, @RequestParam(required = false) HashMap<String, String> pageMap){
		Pageable pageable = PageableUtil.fromMap(pageMap);
		Page<${className1}> list = ${className2}Repository.findByRsql(search, pageable);
		return ResponsePayload.success(list);
	}

	@Transactional
	@PostMapping
	public ResponsePayload save(@RequestBody ${className1} ${className2}) {
		${className2}Repository.save(${className2});
		return ResponsePayload.success(${className2});
	}
	
	@GetMapping({"/load/{id}"})
	public ResponsePayload load(@PathVariable String id) {
		${className1} data = ${className2}Repository.findOne(id);
		return ResponsePayload.success(data);
	}

	@PutMapping
	public ResponsePayload update(@RequestBody ${className1} ${className2}) {
		${className2}Repository.save(${className2});
		return ResponsePayload.success(${className2});
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponsePayload delete(@PathVariable String id){
		${className2}Repository.deleteLogic(id);
		return ResponsePayload.success();
	}
	
	@Transactional
	@PostMapping("/bashSave")
	public ResponsePayload bashSave(@RequestBody List<${className1}> ${className2}) {
		${className2}Repository.bashSave(${className2});
		return ResponsePayload.success(${className2});
	}
	
	@Transactional
	@PutMapping("/bashUpdate")
	public ResponsePayload bashUpdate(@RequestBody List<${className1}> ${className2}) {
		${className2}Repository.bashUpdate(${className2});
		return ResponsePayload.success(${className2});
	}
	
	@Transactional
	@PostMapping("/batchDelete")
	public ResponsePayload batchDelete(@RequestBody List<${className1}> ${className2}) {
		List<String> itemIds = RsqlCustUtils.getIdByList(${className2});
		String search = String.format("id=in=(%s)", StringUtil.join(itemIds, ","));
		List<${className1}> delItems = ${className2}Repository.findByRsql(search);
		${className2}Repository.deleteLogic(delItems);
		return ResponsePayload.success();
	}
}
