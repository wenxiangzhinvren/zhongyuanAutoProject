package ${packagePath}.domain${packageNameSuffix};


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mims.csms.common.domain.BaseDomain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(catalog = "xyh", schema = "css", name = "${tableName}")
public class ${className} extends BaseDomain {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	${fields}
	
}
