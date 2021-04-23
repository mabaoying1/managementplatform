package com.bsoft.service;

import com.bsoft.model.Dim;
import com.bsoft.model.Indb;
import com.bsoft.model.Theme;
import com.bsoft.util.PagePojo;

import java.util.List;
import java.util.Map;

public interface IndbDimService {
    PagePojo getIndbWithPage(Integer pageNo, Integer pageSize, Map<String, Object> paramMap);

    PagePojo getDimWithPage(Integer pageNo, Integer pageSize, Map<String, Object> paramMap);

    void deleteDim(String id);

    void updateDim(Dim dim);

    void saveDim(Dim dim);

    void deleteIndb(String indbId);

    void saveIndb(Indb indb);

    void updateIndb(Indb indb);

	List<Dim> getDim();

	List<String> getIndbDim(String indbId);

	void saveIndbDim(String indbId, String[] dimIds);


	Map<String, Object> getAllIndbDim();

    Theme getIndbDimByIndbKey(String indbKey);
}
