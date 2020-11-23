package com.mycompany.testproj.domain;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;

/**
 * <PRE>
 * VO의 기본모델
 * <br />
 * Author : "Jang Yongjae"
 * <br />
 * Date   : 2012. 6. 13. 오후 4:30:05
 * <br />
 * History
 * ------------------------------------------------------
 * 2012. 6. 13. / Jang Yongjae : 신규 개발.
 * </PRE>
 */
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 1862507089306874088L;

    /**
     * 언어 구분	MAIN/LANG/ALL
     */
    int		langClassification;

    /**
     * 등록자 ID
     */
    String	registantId;

    String registantMemberId;

    String registantMemberName;

    String registantDisplayName;

    /**
     * 등록된 시간
     */
    Date	timeOfRegistered;

    /**
     * 변경자 ID
     */
    String	modifierId;

    String modifierMemberId;

    String modifierMemberName;

    String modifierDisplayName;

    /**
     * 변경된 시간
     */
    Date	timeOfLastModified;

    /**
     * 조회날짜
     */
    Date	searchDate;

    /**
     * 기본날짜
     */
    Date	defaultDate;

    /**
     * 기본변경사유
     */
    String	defaultChangeReason;

    /**
     * 회원번호
     */
    String baseMemberNumber;

    /**
     * 회원구분유형
     */
    String baseMemberSectionType;


    long storeNumber ;
    long siteNumber ;
    long channelNumber ;

    String searchLang;
    ;
    /**
     * 스토어, 사이트, 채널 필터링 옵션
     */
    int filteringOption;
    /**
     * 환율테이블 select시 locale정책에 등록된 통화코드가아닌 환율테이블에 있는 통화코드적용하기 위한변수
     */

    /**
     * 저장유형
     */
    String	saveType;

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

    protected String currencyCode;

    public BaseVO(){
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
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


    public String getRegistantMemberId() {
        return registantMemberId;
    }
    public void setRegistantMemberId(String registantMemberId) {
        this.registantMemberId = registantMemberId;
    }
    public String getRegistantMemberName() {
        return registantMemberName;
    }
    public void setRegistantMemberName(String registantMemberName) {
        this.registantMemberName = registantMemberName;
    }
    public String getRegistantDisplayName() {
        String name = "";
        String id = "";

        if(this.registantMemberName == null || "null".equals(this.registantMemberName.toLowerCase())){
            name = "";
        }else{
            name = this.registantMemberName;
        }

        if(this.registantMemberId == null || "null".equals(this.registantMemberId.toLowerCase())){
            id = "";
        }else{
            id = this.registantMemberId;
        }

        if(StringUtils.isEmpty(id) && StringUtils.isEmpty(name)){
            return this.registantId;
        } else {
            return name + "(" + id + ")";
        }
    }
    public void setRegistantDisplayName(String registantDisplayName) {
        this.registantDisplayName = registantDisplayName;
    }
    public String getModifierMemberId() {
        return modifierMemberId;
    }
    public void setModifierMemberId(String modifierMemberId) {
        this.modifierMemberId = modifierMemberId;
    }
    public String getModifierMemberName() {
        return modifierMemberName;
    }
    public void setModifierMemberName(String modifierMemberName) {
        this.modifierMemberName = modifierMemberName;
    }
    public String getModifierDisplayName() {
        String name = "";
        String id = "";

        if(this.modifierMemberName == null || "null".equals(this.modifierMemberName.toLowerCase())){
            name = "";
        }else{
            name = this.modifierMemberName;
        }

        if(this.modifierMemberId == null || "null".equals(this.modifierMemberId.toLowerCase())){
            id = "";
        }else{
            id = this.modifierMemberId;
        }

        if(StringUtils.isEmpty(id) && StringUtils.isEmpty(name)){
            return this.modifierId;
        } else {
            return name + "(" + id + ")";
        }
    }
    public void setModifierDisplayName(String modifierDisplayName) {
        this.modifierDisplayName = modifierDisplayName;
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
        if(object == null) { return null; }

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



    public String getBaseMemberNumber() {
        return baseMemberNumber;
    }

    public void setBaseMemberNumber(String baseMemberNumber) {
        this.baseMemberNumber = baseMemberNumber;
        this.registantId      = baseMemberNumber;
        this.modifierId       = baseMemberNumber;
    }

    public String getSaveType() {
        return saveType;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    public String getBaseMemberSectionType() {
        return baseMemberSectionType;
    }

    public void setBaseMemberSectionType(String baseMemberSectionType) {
        this.baseMemberSectionType = baseMemberSectionType;
    }

}
