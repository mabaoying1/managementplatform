package com.bsoft.service.impl;

import com.bsoft.mapper.IndbDimMapper;
import com.bsoft.model.Dim;
import com.bsoft.model.Indb;
import com.bsoft.model.Theme;
import com.bsoft.service.IndbDimService;
import com.bsoft.util.PagePojo;
import com.bsoft.util.PageUtil;
import com.bsoft.util.XmlToTheme;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author mabaoying
 * @ClassName: IndbDimServiceImpl
 * @Description: 指标维度业务逻辑层
 * @date: 2019/9/23
 * @最后修改人:
 * @最后修改时间:
 */
@Service
@Transactional
public class IndbDimServiceImpl implements IndbDimService {

	@Autowired
	private IndbDimMapper indbDimMapper;

	/**
	 * 动态分页查询指标
	 */
	@Override
	public PagePojo getIndbWithPage(Integer pageNo, Integer pageSize, Map<String, Object> paramMap) {
		PageHelper.startPage(pageNo, pageSize);
		List<Indb> list = indbDimMapper.getIndb(paramMap);
		return PageUtil.ObjectPageInfo(list);
	}

	/**
	 * 保存指标
	 */
	@Override
	public void saveIndb(Indb indb) {
		indbDimMapper.saveIndb(indb);
	}

	/**
	 * 更新动态指标
	 */
	@Override
	public void updateIndb(Indb indb) {
		indbDimMapper.updateIndb(indb);
	}

	/**
	 * 删除通过指标id指标
	 */
	@Override
	public void deleteIndb(String indbId) {
		// 删除指标维度关联
		indbDimMapper.deleteIndbDimByIndbId(indbId);
		// 删除维度
		indbDimMapper.deleteIndb(indbId);
	}

	/**
	 * 查询该指标的维度id
	 */
	@Override
	public List<String> getIndbDim(String indbId) {
		return indbDimMapper.getIndbDimByIndbId(indbId);
	}

	/**
	 * 保存指标维度
	 */
	@Override
	public void saveIndbDim(String indbId, String[] dimIds) {
		// 先全部清空指标id的维度
		indbDimMapper.deleteIndbDimByIndbId(indbId);
		if (dimIds.length > 0) {
			indbDimMapper.saveIndbDim(indbId, dimIds);
		}
	}

	/**
	 * 动态分页查询维度
	 */
	@Override
	public PagePojo getDimWithPage(Integer pageNo, Integer pageSize, Map<String, Object> paramMap) {
		PageHelper.startPage(pageNo, pageSize);
		List<Dim> list = indbDimMapper.getDim(paramMap);
		return PageUtil.ObjectPageInfo(list);
	}

	/**
	 * 删除维度
	 */
	@Override
	public void deleteDim(String dimId) {
		// 删除指标维度关联
		indbDimMapper.deleteIndbDimByDimId(dimId);
		// 删除维度
		indbDimMapper.deleteDim(dimId);
	}

	/**
	 * 动态更新维度
	 */
	@Override
	public void updateDim(Dim dim) {
		indbDimMapper.updateDim(dim);
	}

	/**
	 * 保存维度
	 */
	@Override
	public void saveDim(Dim dim) {
		indbDimMapper.saveDim(dim);
	}

	/**
	 * 查询全部维度
	 */
	@Override
	public List<Dim> getDim() {
		return indbDimMapper.getDim(null);
	}

	@Override
	public Map<String, Object> getAllIndbDim() {
		List<Theme> themeList=indbDimMapper.getAllIndbDim();
		//return XmlToTheme.themeListToMap(themeList);
		return null;
	}

	@Override
	public Theme getIndbDimByIndbKey(String indbKey) {
		return indbDimMapper.getIndbDimByIndbKey(indbKey);
	}


}
