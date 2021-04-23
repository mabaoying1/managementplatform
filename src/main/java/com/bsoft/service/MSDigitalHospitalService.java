package com.bsoft.service;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MSDigitalHospitalService {
	
	List<HashMap<String, Object>> getEntranceAndExitMS(HashMap<String, Object> map);
	List<HashMap<String,Object>> getNursingMS(HashMap<String, Object> map);
	List<HashMap<String,Object>> getSurgicalArrangementList(Map<String, Object> map);
	List<HashMap<String, Object>> getTimeIntervalAnalysis(Map<String, Object> map);
    HashMap<String,Object> getOutpatientOrHospitalizationIncomeByYear(HashMap<String, Object> map);
	List<HashMap<String,Object>> getOutpatientOrHospitalizationIncomeByType(HashMap<String, Object> map);
	List<HashMap<String,Object>> getOutpatientOrHospitalizationIncomeByDept(HashMap<String, Object> map);
	List<HashMap<String,Object>> getOutpatientPersonTime(HashMap<String, Object> map);
	List<HashMap<String,Object>> getHospitalizationPersonTime(HashMap<String, Object> map);
	List<HashMap<String,Object>> getDrugOccupationRatioByOutpatient(Map<String, Object> requestMap);
	List<HashMap<String,Object>> getDrugOccupationRatioByHospitalization(Map<String, Object> requestMap);
	List<HashMap<String,Object>> getDrugOccupationRatio(Map<String, Object> requestMap);
	HashMap<String,Object> getAverageCost(Map<String, Object> requestMap);

    HashMap<String,Object> getCountOutpatientPersonTime(HashMap<String, Object> requestMap);

    HashMap<String,Object> getCountDrugOccupationRatioByOutpatient(Map<String, Object> requestMap);

    HashMap<String,Object> getCountDrugOccupationRatioByHospitalization(Map<String, Object> requestMap);

    HashMap<String,Object> getCountDrugOccupationRatio(Map<String, Object> requestMap);

	List<HashMap<String, Object>> getTodayAccessoryExaminationTotal(Map<String, Object> requestMap);
	
	List<HashMap<String, Object>> getInpatientAssistantExaminationMS(Map<String, Object> requestMap);
	
	HashMap<String,Object> getTodayPrescriptionTotal(Map<String, Object> requestMap);

	HashMap<String,Object> getTodayDoctorTotal(Map<String, Object> requestMap);
	
	HashMap<String,Object> getCountMaterialOccupationRatio(Map<String, Object> requestMap);

	HashMap<String,Object> getComprehensiveMonthlyReportPersonTime(Map<String, Object> requestMap);

	HashMap<String,Object> getComprehensiveMonthlyReportAdmissionsPersonTime(Map<String, Object> requestMap);

	HashMap<String,Object> getComprehensiveMonthlyReportDailyAverageOutpatientPersonTime(Map<String, Object> requestMap);

	HashMap<String,Object> getComprehensiveMonthlyReportMonthlyOutpatientPersonTime(Map<String, Object> requestMap);

	List<HashMap<String, Object>> getWardInformation(Map<String, Object> map);
	
	List<HashMap<String, Object>> getClzcByMonth(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getGlfyByMonth(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getMzsrByMonth(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getZysrByMonth(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getYpByMonth(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getZyypByMonth(HashMap<String, Object> requestMap);
	
	List<HashMap<String, Object>> getClzcByKs(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getGlfyByKs(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getMzsrByKs(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getZysrByKs(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getYpByKs(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getZyypByKs(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getAllKs(HashMap<String, Object> requestMap);
	List<HashMap<String, Object>> getKsMap();
	
	List<HashMap<String, Object>> getQyfyList(HashMap<String, Object> requestMap);
	
	List<HashMap<String,Object>> getOutpatientOrHospitalizationIncomeByDoctor(HashMap<String, Object> map);
	List<HashMap<String, Object>> getAverageCostByDeptAndMz(Map<String, Object> requestMap);
	List<HashMap<String, Object>> getAverageCostByDeptAndZy(Map<String, Object> requestMap);
	List<HashMap<String, Object>> getAverageCostByDeptAndSs(Map<String, Object> requestMap);
	List<HashMap<String, Object>> getAverageCostByDoctorAndMz(Map<String, Object> requestMap);
	List<HashMap<String, Object>> getAverageCostByDoctorAndZy(Map<String, Object> requestMap);
	List<HashMap<String, Object>> getAverageCostByDoctorAndSs(Map<String, Object> requestMap);
	
	
	List<HashMap<String, Object>> getCountOutpatientPersonTimeByDept(Map<String, Object> requestMap);
	List<HashMap<String, Object>> getCountOutpatientPersonTimeByDoctor(Map<String, Object> requestMap);
	
	
	List<HashMap<String, Object>> getCountDrugOccupationRatioByOutpatientByDeptMz(Map<String, Object> requestMap);
	List<HashMap<String, Object>> getCountDrugOccupationRatioByOutpatientByDeptZy(Map<String, Object> requestMap);
	
	List<HashMap<String, Object>> getCountDrugOccupationRatioByOutpatientAndDeptAndDrugMz(Map<String, Object> requestMap);
	List<HashMap<String, Object>> getCountDrugOccupationRatioByOutpatientAndDeptAndDrugZy(Map<String, Object> requestMap);
	
	List<HashMap<String, Object>> getTimeIntervalAnalysisByOutpatientAndDeptAndTime(Map<String, Object> map);

	List<HashMap<String, Object>> getTimeIntervalAnalysisByOutpatientAndDoctorAndTime(Map<String, Object> map);
	
	List<HashMap<String, Object>> getTodayPrescriptionTotalByMSAndDept(Map<String, Object> map);
	List<HashMap<String, Object>> getTodayPrescriptionTotalByMSAndDoctor(Map<String, Object> map);

}
