package com.mycompany.testproj.domain;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseSC implements Serializable {

    private static final long serialVersionUID = -3181502121460642107L;

    Hashtable<String, String> hashtable = new Hashtable<String, String>();



    Paging paging = new Paging();

    public BaseSC(){
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    /**
     * 페이징 ROWNUM 시작
     */
    int	rownumStart;

    /**
     * 페이징 ROWNUM 시작
     */
    int	rownumEnd;

    /**
     * 등록자 ID
     */
    String	registantId;

    /**
     * 등록된 시간
     */
    Date	timeOfRegistered;

    /**
     * 변경자 ID
     */
    String	modifierId;

    /**
     * 변경된 시간
     */
    Date	timeOfLastModified;

    /**
     * 조건절 Map
     * String : 비교컬럼명
     * String : 비교연산자
     */
    Map<String,String> conditionMap ;

    /**
     * 정렬 Map
     * String : 정렬 내용
     * 			[FIRST]		노출순서가 빠른 것
     * 			[MODIFY]	최근수정된것
     * 			[REGIST]	최근등록된것
     * 			[NAME]		명칭순(ASC)
     * String : [ASC]		오름차순
     * 			[DESC]		내림차순
     */
    String [][] orderArr ;

    /**
     * 언어 구분	MAIN/LANG/ALL
     */
    int	langClassification;

    /**
     * 현재 페이지
     */
    int 	currentPage;

    /**
     * 한 페이지 안에 포함될 아이템 수
     */
    int 	itemPerPage ;

    /**
     * 한 챕터 안에 포함될 페이지 수
     */
    int 	pagePerChapter = Paging.defaultPageSize();

    /**
     * 날짜조회 기준필드정의 : [REG]등록일시, [MOD]수정일시, [ALL]등록일시+수정일시
     */
    long storeNumber;
    long siteNumber;
    long channelNumber;

    String fieldDate;

    /**
     * 조회날짜
     */
    Date	searchDate;

    /**
     * 시작날짜
     */
    Date	startDate;

    /**
     * 종료날짜
     */
    Date 	endDate;

    /**
     * 검색항목
     */
    String 	searchItem;

    /**
     * 검색항목
     */
    String 	searchItem2;

    public String getSearchItem2() {
        return searchItem2;
    }

    public void setSearchItem2(String searchItem2) {
        this.searchItem2 = searchItem2;
    }

    /**
     * 검색문자
     */
    String 	searchStr;

    /**
     * 기본날짜
     */
    Date	defaultDate;

    /**
     * 기본변경사유
     */
    String	defaultChangeReason;

    /**
     * 기본 Language
     */
    String	searchLang;

    /**
     * 회원번호
     */
    String baseMemberNumber;

    /**
     * 회원구분유형
     */
    String baseMemberSectionType;


    Date    searchStartDate;

    Date    searchEndDate;

    /**
     * 국가코드
     */
    String countryCode;

    /**
     * 언어
     */
    String language;

    /**
     * jqGrid의 row Status
     */
    String rowStatus;

    public String getRowStatus() {
        return (rowStatus==null)?"R":rowStatus;
    }
    public void setRowStatus(String rowStatus) {
        this.rowStatus = rowStatus;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public int getRownumStart() {
        return paging.getFirstItemNo();
    }

    public void setRownumStart(int rownumStart) {
        this.rownumStart = rownumStart;
    }

    public int getRownumEnd() {
        return paging.getLastItemNo();
    }


    public void setRownumEnd(int rownumEnd) {
        this.rownumEnd = rownumEnd;
    }

    public String getModelValue(String key) {
        String value = "NONE";
        if(hashtable.containsKey(key)) {
            value = hashtable.get(key);
        }

        return value;
    }

    public void setModelValue(String key, String value) {
        hashtable.put(key, value);
    }

    public int getLangClassification() {
        return langClassification;
    }

    public void setLangClassification(int langClassification) {
        this.langClassification = langClassification;
    }

    public String getRegistantId() {
        return registantId;
    }

    public void setRegistantId(String registantId) {
        this.registantId = registantId;
    }

    public Date getTimeOfRegistered() {
        return timeOfRegistered;
    }

    public void setTimeOfRegistered(Date timeOfRegistered) {
        this.timeOfRegistered = timeOfRegistered;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public Date getTimeOfLastModified() {
        return timeOfLastModified;
    }

    public void setTimeOfLastModified(Date timeOfLastModified) {
        this.timeOfLastModified = timeOfLastModified;
    }

    public Map<String, String> getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Map<String, String> conditionMap) {
        this.conditionMap = conditionMap;
    }

    public String[][] getOrderArr() {
        return orderArr;
    }

    public void setOrderArr(String[][] orderArr) {
        this.orderArr = orderArr;
    }

    public String getFieldDate() {
        return fieldDate;
    }

    public void setFieldDate(String fieldDate) {
        this.fieldDate = fieldDate;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {

        this.itemPerPage = itemPerPage;
        getPaging().setPerPage(itemPerPage);
    }

    public int getPagePerChapter() {
        return pagePerChapter;
    }

    public String getBaseMemberNumber() {
        return baseMemberNumber;
    }


    public void setBaseMemberNumber(String baseMemberNumber) {
        this.baseMemberNumber = baseMemberNumber;
    }

    public String getBaseMemberSectionType() {
        return baseMemberSectionType;
    }

    public void setBaseMemberSectionType(String baseMemberSectionType) {
        this.baseMemberSectionType = baseMemberSectionType;
    }

    public void setPagePerChapter(int pagePerChapter) {
        this.pagePerChapter = pagePerChapter;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public Date getDefaultDate() {
        return defaultDate;
    }

    public void setDefaultDate(Date defaultDate) {
        this.defaultDate = defaultDate;
    }

    public String getDefaultChangeReason() {
        return defaultChangeReason;
    }

    public void setDefaultChangeReason(String defaultChangeReason) {
        this.defaultChangeReason = defaultChangeReason;
    }

    public String getSearchLang() {
        return searchLang;
    }

    public void setSearchLang(String searchLang) {
        this.searchLang = searchLang;
    }


    public long getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(long storeNumber) {
        this.storeNumber = storeNumber;
    }

    public long getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(long siteNumber) {
        this.siteNumber = siteNumber;
    }

    public long getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(long channelNumber) {
        this.channelNumber = channelNumber;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this,	ToStringStyle.MULTI_LINE_STYLE);
//		return getReflectionToString(this);
    }

    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }



    /**
     * Model class 의 field 를 문자열로 정리하여 반환.
     *
     * @param object
     * @return
     * @return String
     */
    public static String getReflectionToString(Object object) {
        if(object == null) return null;

        @SuppressWarnings("rawtypes")
        Class clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();

        StringBuilder toString = new StringBuilder();
        toString.append( "\n#### ReflectionToString :: " );


        for ( Field field : fields ) {

            if (Modifier.isStatic(field.getModifiers())) { continue; }

            field.setAccessible(true);

            toString.append( field.getName() );
            toString.append( " = " );

            try {
                toString.append( "[" + field.get(object) + "]" );
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                toString.append( " >>>> IllegalArgumentException occured .... " );
                toString.append( e.toString() );
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                toString.append( " >>>> IllegalAccessException occured .... " );
                toString.append( e.toString() );
            }

            toString.append( " ; " );


        } // for


        return toString.toString() ;
    }


    public Date getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(Date searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public Date getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(Date searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

}