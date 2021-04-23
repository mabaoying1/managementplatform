package com.bsoft.mapper;

import com.bsoft.model.Dim;
import com.bsoft.model.Indb;
import com.bsoft.model.Theme;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface IndbDimMapper {

	List<Indb> getIndb(Map<String, Object> paramMap);

	List<Dim> getDim(Map<String, Object> paramMap);

	int deleteDim(@Param("dimId") String dimId);

	int updateDim(Dim dim);

	int saveDim(Dim dim);

	int deleteIndbDimByDimId(@Param("dimId") String dimId);

	int deleteIndbDimByIndbId(@Param("indbId") String indbId);

	int deleteIndb(@Param("indbId") String indbId);

	int saveIndb(Indb indb);

	int updateIndb(Indb indb);

	List<String> getIndbDimByIndbId(@Param("indbId") String indbId);

	int saveIndbDim(@Param("indbId") String indbId, @Param("dimIds") String[] dimIds);

	Theme getIndbDimByIndbKey(@Param("indbKey") String indbKey);

	List<Theme> getAllIndbDim();
}
