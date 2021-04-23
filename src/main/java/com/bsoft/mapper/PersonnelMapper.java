package com.bsoft.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bsoft.model.Personnel;

/**
 * @author mabaoying
 * @ClassName: PersonnelMapper
 * @Description: 用户管理dao层
 * @date 2019年03月12日
 * @最后修改人:
 * @最后修改时间:
 */
public interface PersonnelMapper {
	/**
	 * 查询全部人员
	 */
	List<Personnel> getPersonnel(Map<String, Object> paramMap);

	/**
	 * 删除人员
	 */
	int deletePersonnel(@Param("personId") String personId);

}
