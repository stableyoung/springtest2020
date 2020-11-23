package com.mycompany.testproj.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mycompany.testproj.domain.BaseSC;
import com.mycompany.testproj.domain.PageList;

public class JSONUtil {
	
	/**
	 * jqGrid 페이징 목록용 Json 데이타를 위한 Map 객체 생성 후 리턴
	 * @param list
	 * @param baseSC
	 * @return
	 */	
	public static Map<String, Object> getPagingListJsonMap(List<?> list, BaseSC baseSC){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows",     list); //조회결과 목록 list
	    map.put("page",     baseSC.getPaging().getCurrentPage()); // 조회할 페이지
	    map.put("total",      baseSC.getPaging().getPageCount()); //전체 페이지 수
	    map.put("records", baseSC.getPaging().getItemCount()); //검색 총 건수
	    
		return map;
	}
	/**
	 * jqGrid 페이징 목록용 Json 데이타를 위한 Map 객체 생성 후 리턴
	 * @param list
	 * @param page
	 * @param total
	 * @param records
	 * @return
	 */
	public static Map<String, Object> getPagingListJsonMap(List<?> list, int page, int total, int records){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows",    list); //조회결과 목록 list
	    map.put("page",    page); // 조회할 페이지
	    map.put("total",   total); //전체 페이지 수
	    map.put("records", records); //검색 총 건수
	    
		return map;
	}
	/**
	 * jqGrid 페이징 목록용 Json 데이타를 위한 Map 객체 생성 후 리턴
	 * @param list
	 * @return
	 */
	public static Map<String, Object> getPagingListJsonMap(PageList<?> list){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows",     list.getItemList()); //조회결과 목록 list
	    map.put("page",     list.getPaging().getCurrentPage()); // 조회할 페이지
	    map.put("total",    list.getPaging().getPageCount()); //전체 페이지 수
	    map.put("records",  list.getPaging().getItemCount()); //검색 총 건수
	    
		return map;
	}
	/**
	 * 페이징 없는 전제 목록용 Json 데이타를 위한 Map 객체 생성 후 리턴
	 * @param list
	 * @return
	 */
	public static Map<String, Object> getAllListJsonMap(List<?> list){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows",     list); //조회결과 목록 list
	    map.put("page",     1); // 조회할 페이지
	    map.put("total",    1); //전체 페이지 수
	    map.put("records",  list.size()); //검색 총 건수
	    
		return map;
	}
	
	/**
	 * jqGrid 페이징 목록용 Json 데이타를 위한 Map 객체 생성 후 리턴
	 * @param list
	 * @param baseSC
	 * @return
	 */	
	public static Map<String, Object> getPagingListJsonVOMap(List<?> list, BaseSC baseSC, Object vo){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows",     list); //조회결과 목록 list
	    map.put("page",     baseSC.getPaging().getCurrentPage()); // 조회할 페이지
	    map.put("total",      baseSC.getPaging().getPageCount()); //전체 페이지 수
	    map.put("records", baseSC.getPaging().getItemCount()); //검색 총 건수
	    map.put("vo", vo); // 화면에서 필요한 VO
	    
		return map;
	}
	
	
    /**
     * FuncName : MapToJson()
     * author :	KYOUNGDO LEE
     * FuncDesc : Json String 변환
     * Param    : sData : String
     * Return   : String
    */
    public static String MapToJson(Map<String, Object> map) {
        Gson gson = new Gson();

        return gson.toJson(map);
    }
    
    
    /**
     * FuncName : JsonToMap()
     * author :	KYOUNGDO LEE
     * FuncDesc : Json String -> Map 형태 변환
     * Param    : param : Json String
     * Return   : Map<String, Object>
    */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> JsonToMap(String param) {
        Gson gson = new Gson();

        return gson.fromJson(param, new HashMap<String,Object>().getClass());
    }    
}
