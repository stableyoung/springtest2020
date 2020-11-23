package com.mycompany.testproj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;







import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String 관련 기능을 제공하는 클래스
 */
public class StringUtil extends StringUtils{

    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
//    public static final String ARRAY_DELIMETER = "|";
//    public static final String KEYVALUE_DELIMETER = "=";
//
    public static String convertHtml(String str) {
        String resultStr = "";
        if(str==null||str.equals("")) {
            return str;
        }

        resultStr = str.replaceAll("<", "&lt;");
        resultStr = str.replaceAll(">", "&gt;");
        resultStr = str.replaceAll("\"", "&quot;");
        return resultStr;
    }

    public static String removeNewLine(String str) {
        String resultStr = "";
        if(str==null||str.equals("")) {
            return str;
        }

        resultStr = str.replaceAll("\n", "");
        return resultStr;
    }

    /**
     * textarea 내에서 개행 시
     * 강제개행문자와 개행문자를 화면출력을 위한 문자열로 변환
     * @param str
     * @return
     */
    public static String convertTextAreaEnter(String str){
        String resultStr = "";
        if(str==null||str.equals("")) {
            return str;
        }

        resultStr = str.replaceAll("\r\n", "<br>");
        return resultStr;
    }
    public static String convertTextAreaEnter10(String str){
        String resultStr = "";
        if(str==null||str.equals("")) {
            return str;
        }

        resultStr = str.replaceAll("\n", "<br>");
        return resultStr;
    }

    public static String convertTextAreaEnter1(String str){
        String resultStr = "";
        if(str==null||str.equals("")) {
            return str;
        }

        resultStr = str.replaceAll("\n", "");
        return resultStr;
    }

    public static String convertTextAreaEnter2(String str){
        String resultStr = "";
        if(str==null||str.equals("")) {
            return str;
        }

        resultStr = str.replaceAll("\n", "<br>");
        return resultStr;
    }

    public static String convertTextAreaEnter3(String str){
        String resultStr = "";
        if(str==null||str.equals("")) {
            return str;
        }

        resultStr = str.replaceAll("\r\n", "");
        resultStr = resultStr.replaceAll("\n", "");
        resultStr = resultStr.replaceAll("<br>", "\\\\n");
        resultStr = resultStr.replaceAll("<br/>", "\\\\n");
        resultStr = resultStr.replaceAll("<BR>", "\\\\n");
        resultStr = resultStr.replaceAll("<BR/>", "\\\\n");

        return resultStr;
    }

    public static String reconvertHtml(String str) {
        String resultStr = "";
        if(str==null||str.equals("")) {
            return str;
        }

        resultStr = str.replaceAll("&lt;", "<");
        resultStr = str.replaceAll("&gt;", ">");
        resultStr = str.replaceAll("&quot;", "\"");
        return resultStr;
    }

    public static String byteToHex(byte[] src){
        if(src==null){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String str = null;
        for(int i=0;i<src.length;i++){
            str = Integer.toHexString( src[i]&0xff );
            sb.append(str.length()==1?"0"+str:str);
        }
        return sb.toString();
    }

    public static byte[] hexToByte(String src){
        if(src==null) {
            return null;
        }

        if(src.length()%2!=0){
            src = "0"+src;
        }

        if(src.length() < 0) {
            return null;
        }
        byte[] result = new byte[src.length()/2];
        for(int i=0;i<result.length;i++){
            result[i] = (byte)Integer.parseInt(src.substring(2*i,2*i+2),16);
        }
        return result;
    }

    public static HashMap<String, Object> strToMap(String str, String arrayDelim, String keyValueDelim){
        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        if(str!=null){
            String[] params = str.split("\\"+arrayDelim);
            for(int i=0;i<params.length;i++){
                String[] strs = params[i].split(keyValueDelim);
                if(strs.length==2){
                    String key = strs[0].trim();
                    String value = strs[1].trim();
                    paramMap.put(key, value);
                }
            }
        }

        return paramMap;
    }

    /**
     * value를 String으로 변환해 줌.
     *
     * @param value
     * @return
     */
    public static String valueOf(Object value) {
        return valueOf(value, String.valueOf((Object)null));
    }

    /**
     * value를 String으로 변환해 줌. vaule가 null이면 defaultValue를 리턴함.
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static String valueOf(Object value, String defaultValue) {
        return value == null ? defaultValue : String.valueOf(value);
    }

    /**
     * value를 String으로 변환해 줌. vaule가 null이거나 공백이면 defaultValue를 리턴함.
     *
     * <pre>
     * StringUtil.valueIfEmpty(null, &quot;NULL&quot;)  = &quot;NULL&quot;
     * StringUtil.valueIfEmpty(&quot;&quot;, &quot;NULL&quot;)    = &quot;NULL&quot;
     * StringUtil.valueIfEmpty(&quot;bat&quot;, &quot;NULL&quot;) = &quot;bat&quot;
     * </pre>
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static String valueIfEmpty(Object value, String defaultValue) {
        return defaultIfEmpty(valueOf(value, ""), defaultValue);
    }

    /**
     * value값을 boolean형으로 리턴함. value가 유효하지 않은 경우 false를 리턴함.<br>
     * value 값이 대소문자 구분 없는 "true"일 때만 true 리턴함.
     *
     * @param value
     * @return
     */
    public static boolean parseBoolean(Object value) {
        return Boolean.valueOf(valueOf(value)).booleanValue();
    }

    /**
     * value값을 boolean형으로 리턴함. value가 유효하지 않은 경우 false를 리턴함.<br>
     * value 값이 대소문자 구분 없는 "true"일 때만 true 리턴함.
     *
     * @param value
     * @param defaultValue value가 null 일때 기본값.
     * @return
     */
    public static boolean parseBoolean(Object value, boolean defaultValue) {
        return value == null ? defaultValue : Boolean.valueOf(valueOf(value)).booleanValue();
    }

    /**
     * value값을 int형으로 리턴함. value가 유효하지 않은 경우 0을 리턴함.
     *
     * @param value
     * @return
     */
    public static int parseInt(Object value) {
        return parseInt(value, 0);
    }

    /**
     * value값을 int형으로 리턴함. value가 유효하지 않은 경우 defaultValue를 리턴함.
     *
     * @param value
     * @return
     */
    public static int parseInt(Object value, int defaultValue) {
        Number number = parseNumber(value);
        return number == null ? defaultValue : number.intValue();
    }

    /**
     * value값을 long형으로 리턴함. value가 유효하지 않은 경우 0을 리턴함.
     *
     * @param value
     * @return
     */
    public static long parseLong(Object value) {
        return parseLong(value, 0L);
    }

    /**
     * value값을 long형으로 리턴함. value가 유효하지 않은 경우 defaultValue를 리턴함.
     *
     * @param value
     * @return
     */
    public static long parseLong(Object value, long defaultValue) {
        Number number = parseNumber(value);
        return number == null ? defaultValue : number.longValue();
    }

    /**
     * value값을 float형으로 리턴함. value가 유효하지 않은 경우 0.0을 리턴함.
     *
     * @param value
     * @return
     */
    public static float parseFloat(Object value) {
        return parseFloat(value, 0.0f);
    }

    /**
     * value값을 float형으로 리턴함. value가 유효하지 않은 경우 defaultValue를 리턴함.
     *
     * @param value
     * @return
     */
    public static float parseFloat(Object value, float defaultValue) {
        Number number = parseNumber(value);
        return number == null ? defaultValue : number.floatValue();
    }

    /**
     * value값을 double형으로 리턴함. value가 유효하지 않은 경우 0.0을 리턴함.
     *
     * @param value
     * @return
     */
    public static double parseDouble(Object value) {
        return parseDouble(value, 0.0d);
    }

    /**
     * value값을 double형으로 리턴함. value가 유효하지 않은 경우 defaultValue를 리턴함.
     *
     * @param value
     * @return
     */
    public static double parseDouble(Object value, double defaultValue) {
        Number number = parseNumber(value);
        return number == null ? defaultValue : number.doubleValue();
    }

    /**
     * value값을 Number형으로 리턴함. value가 유효하지 않은 경우 null를 리턴함.
     *
     * @param value
     * @return
     */
    public static Number parseNumber(Object value) {
        try {
            return NumberFormat.getInstance().parse(valueOf(value));
        }
        catch(ParseException e) {
            return null;
        }
    }

    private static int byteChar(char chr) {
        if(Character.getType(chr)==5) {
            return 2;
        } else if(chr>0x007f) {
            return 2;
        } else {
            return 1;
        }
    }

    public static String leftB(String str, int max) {
        int subLen = 0;
        StringBuilder sf = new StringBuilder();

        if(str != null) {
            for(int i=0;i<str.length();i++) {
                subLen+=byteChar(str.charAt(i));
                if(subLen<=max) {sf.append(str.charAt(i));}
            }
        }

        return sf.toString();
    }

    public static String leftB(StringBuffer str, int max) {
        int subLen = 0;
        StringBuilder sf = new StringBuilder();

        if(str != null) {
            for(int i=0;i<str.length();i++) {
                subLen+=byteChar(str.charAt(i));
                if(subLen<=max){ sf.append(str.charAt(i));}
            }
        }

        return sf.toString();
    }

    public static String rightB(String str, int max) {
        int subLen = 0;
        StringBuilder sf = new StringBuilder();

        if(str != null) {
            for(int i=str.length();i>0;i--) {
                subLen+=byteChar(str.charAt(i-1));
                if(subLen<=max){ sf.append(str.charAt(i-1));}
            }
        }

        return sf.reverse().toString();
    }

    public static String rightB(StringBuilder str, int max) {
        int subLen = 0;
        StringBuilder sf = new StringBuilder();

        if(str != null) {
            for(int i=str.length();i>0;i--) {
                subLen+=byteChar(str.charAt(i-1));
                if(subLen<=max) {sf.append(str.charAt(i-1));}
            }
        }

        return sf.reverse().toString();
    }

    public static String substrB(String str, int start, int len) {
        return leftB(substrB(str, start),len);
    }

    public static String substrB(StringBuffer str, int start, int len) {
        return leftB(substrB(str, start),len);
    }

    public static String substrB(String str, int start) {
        int subLen = 0;
        StringBuilder sf = new StringBuilder();

        if(str != null) {
            for(int i=0;i<str.length();i++) {
                subLen+=byteChar(str.charAt(i));
                if(subLen>=start){ sf.append(str.charAt(i));}
            }
        }

        return sf.toString();
    }

    public static String substrB(StringBuffer str, int start) {
        int subLen = 0;
        StringBuilder sf = new StringBuilder();

        if(str != null) {
            for(int i=0;i<str.length();i++) {
                subLen+=byteChar(str.charAt(i));
                if(subLen>=start) {sf.append(str.charAt(i));}
            }
        }

        return sf.toString();
    }

    public static int lengthB(String str) {
        int subLen = 0;

        if(str != null) {
            for(int i=0;i<str.length();i++) {
                subLen+=byteChar(str.charAt(i));
            }
        }

        return subLen;
    }

    public static int lengthB(StringBuffer str) {
        int subLen = 0;

        if(str != null) {
            for(int i=0;i<str.length();i++) {
                subLen+=byteChar(str.charAt(i));
            }
        }

        return subLen;
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String reverse(StringBuffer str) {
        if(str != null) {
            return new StringBuilder(str.toString()).reverse().toString();
        }
        return null;
    }

    public static String getCommaFormat(String dec){
        double result = Double.parseDouble(dec);
        NumberFormat fmat = NumberFormat.getInstance();

        if(fmat instanceof DecimalFormat){
            ((DecimalFormat)fmat).setDecimalSeparatorAlwaysShown(false);
        }

        return fmat.format((int)result);
    }

    public static String getCommaFormat(double dec){
        double result = dec;
        NumberFormat fmat = NumberFormat.getInstance();

        if(fmat instanceof DecimalFormat){
            ((DecimalFormat)fmat).setDecimalSeparatorAlwaysShown(false);
        }

        return fmat.format((int)result);
    }

    public static String getCommaFormat(int dec){
        double result = (double)dec;
        NumberFormat fmat = NumberFormat.getInstance();

        if(fmat instanceof DecimalFormat){
            ((DecimalFormat)fmat).setDecimalSeparatorAlwaysShown(false);
        }

        return fmat.format((int)result);
    }

    public static String makeChar(String str, int cnt){
        StringBuilder sf = new StringBuilder("");

        for(int i=0; i<cnt; i++){
            sf.append(str);
        }

        return sf.toString();
    }

    public static String makeZero(int cnt){
        return makeChar("0", cnt);
    }

    public static String fillZero(String str, int max){
        return rightB(makeZero(max) + str, max);
    }

    public static String fillZero(int i, int max){
        return rightB(makeZero(max) + StringUtil.valueOf(i,""), max);
    }

    public static String fillZero(long i, int max){
        return rightB(makeZero(max) + StringUtil.valueOf(i,""), max);
    }

    public static String fillZeroR(String str, int max){
        return leftB(str + makeZero(max), max);
    }

    public static String fillZeroR(int i, int max){
        return leftB(StringUtil.valueOf(i,"") + makeZero(max), max);
    }

    public static String fillZeroR(long i, int max){
        return leftB(StringUtil.valueOf(i,"") + makeZero(max), max);
    }

    public static String makeSpace(int cnt){
        return makeChar(" ", cnt);
    }

    public static String fillSpace(String str, int max){
        return rightB(makeSpace(max) + str, max);
    }

    public static String fillSpaceR(String str, int max){
        return leftB(str + makeSpace(max), max);
    }

    public static String toBasicDate(String str){
        String tStr = leftB(StringUtil.replace(StringUtil.replace(StringUtil.replace(str, "/", ""), "-", ""), ".", ""),8);
        if(lengthB(tStr)==8) {
            int y = StringUtil.parseInt(leftB(tStr,4));
            int m = StringUtil.parseInt(substrB(tStr, 5, 2));
            int d = StringUtil.parseInt(substrB(tStr, 7, 2));

            if(y<1900 || y>2100) {
                return null;
            }
            if(m<1 || m>12) {
                return null;
            }
            if(d<1 || d>31) {
                return null;
            }

            return tStr;
        }
        else {
            return null;
        }
    }

    /**
     * 문자열에서 숫자만 골라내는 함수
     * @param str
     * @return
     */
    public static String extractNumber(String str){
        return extractNumber(str,false,false);
    }

    /**
     * 문자열에서 숫자,+,-만 골라내는 함수
     * @param str
     * @return
     */
    public static String extractTelNumber(String str){
        return extractNumber(str,true,true);
    }

    /**
     * 문자열에서 숫자만 골라내는 함수 (음수포함)
     * @param str
     * @return
     */
    public static String extractNumber(String str, boolean plus, boolean minus){
        StringBuilder numeral = new StringBuilder("");
        String temp = "";

        if(str == null){
            return null;
        }else{
            for(int i=0; i<str.length(); i++){
                temp = str.substring(i,i+1);

                if(i==0) {//첫글자 체크
                    if( (plus && temp.equals("+")) || (minus && temp.equals("-")) ) {
                        numeral.append(temp);
                    }
                }

                if(Character.isDigit(str.charAt(i))){
                    numeral.append(temp);
                }
            }
        }
        return numeral.toString();
    }

    public static String thumbnailExt(String contentType, String ext) {
        String returnExt = "";

        if(contentType != null && !contentType.equals("video")) {
            if(ext != null) {
                int index = ext.lastIndexOf(".");
                if(index<0) {
                    returnExt = ext;
                }
                else {
                    returnExt = ext.substring(index+1);
                }
            }
        }
        return valueIfEmpty(returnExt,"jpg");
    }

    /**
     * 파일명에서 확장자 조회
     * @param contentType
     * @param ext
     * @return
     */
    public static String contentExt(String contentType, String ext) {
        String returnExt = "";

        if(ext != null) {
            int index = ext.lastIndexOf(".");
            if(index<0) {
                returnExt = ext;
            }
            else {
                returnExt = ext.substring(index+1);
            }
        }

        return valueIfEmpty(returnExt,"jpg");
    }

    /**
     * 숫자로만 이루어진 문자인지 확인.
     * @param str
     * @return
     */
    public static boolean isNumberic(String str) {
        if(str != null) {
            return str.matches("-?\\d+");
        }
        return false;
    }

    /**
     * 숫자와 (-)로만 이루어진 문자인지 확인.
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str) {
        if(str != null) {
            return str.matches("[-+\\d]+");
        }
        return false;
    }

    /**
     * 영문 숫자인지 확인.
     * @param str
     * @return
     */
    public static boolean isAlphaNum(String str) {
        if(str != null) {
            return str.matches("[a-zA-Z\\d]+");
        }
        return false;
    }

    /**
     * Multiple Value Convert List
     * value1,value2,value3,... -> List<String>
     * @param value
     * @return
     */
    public static List<String> getListOfMultipleValues(String... values) {
        List<String> resultList = new ArrayList<String>();
        for(String value : values) {
            resultList.add(value);
        }
        return resultList;
    }

    /**
     * Multiple Value Convert List
     * value1,value2,value3,... -> List<String>
     * @param value
     * @return
     */
    public static List<String> getListOfMultipleValues(String value) {
        return getListOfMultipleValues(StringUtil.split(value, ","));
    }

        /**
         * Multiple Value Convert List
         * value1,value2,value3,... -> List<String>
         * @param value
         * @return
         */
        public static Map<String, String> getMapOfMultipleValues(String... values) {
            if ( values == null || values.length <= 0 ){
                return Collections.emptyMap();
            }

                Map<String, String> map = new HashMap<String, String>(values.length);
                for(String value : values) {
                    map.put(value, value);
                }
                return map;
        }

    /**
     * Multiple Value Convert List
     * value1,value2,value3,... -> List<String>
     * @param value
     * @return
     */
    public static Map<String, String> getMapOfMultipleValues(String value) {
        return getMapOfMultipleValues(StringUtil.split(value, ","));
    }

    /**
     * 여러개의 문자열을 하나로 붙여러 리턴한다.
     * @param str0 첫번째 기본 파라메터
     * @param str 두번째 옵션 파라메터
     * @return 하나로 만들어진 문자열
     */
    public static String addString(Object str0, Object ... str){

        StringBuilder buf = new StringBuilder(1000);
        buf.append(str0);

        if(str != null && str.length > 0 ) {
            for (int k = 0; k < str.length; k++) {
                buf.append(str[k]);
            }
        }
        return buf.toString();
    }

    public static Object[] param(Object ... str){
        return str;
    }

    public static String[] paramString(String ... str){
        return str;
    }

    public static String sumInt(String x, String y){
        int ix = StringUtil.parseInt(x);
        int iy = StringUtil.parseInt(y);
        return String.valueOf( ix + iy );
    }

    public static boolean isEmpty(int[] arr) {
        return (arr == null || arr.length <= 0);
    }

    public static boolean isEmpty(Object[] arr) {
        return (arr == null || arr.length <= 0);
    }

    public static boolean isEmptyStr(String p) {
        return (p == null || p.trim().equals(""));
    }

    public static boolean isNotEmptyStr(String p) {
        return !isEmptyStr(p);
    }

    /**
     * Returns the byte length of string
     *
     * @param targetStr
     * @param ch
     */
    public static int getByteLength(String str) {
        return (str == null || str.equals("")) ? 0 : str.getBytes().length;
    }

    /**
     * String length in bytes of the specified crop function
     *
     * @param str
     * @param maxbytelen
     */
    public static String getCutString(String str, int maxbytelen) {


        if (getByteLength(str) <= maxbytelen) {
            return str;
        }

        StringBuffer sb = new StringBuffer();
        int nCurrBytes = 0;
        for (int i = 0; i < str.length(); i++) {
            String ch = str.substring(i, i + 1);
            if ((nCurrBytes + ch.getBytes().length) <= maxbytelen) {
                sb.append(ch);
                nCurrBytes += ch.getBytes().length;
            } else {
                break;
            }
        }
        sb.setLength(sb.length() - 1);
        sb.append("..");
        return sb.toString();
    }

    public static void getReflectionToStringQuotation(Object object) {
        if(object == null) {return;}

        @SuppressWarnings("rawtypes")
        Class clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for ( Field field : fields ) {

            if (Modifier.isStatic(field.getModifiers())){ continue; }

            field.setAccessible(true);

            try {
                  Object type = field.getType();

                  if("class java.lang.String".equals(type.toString())){
                      Object oldValue = field.get(object);

                      if(oldValue!=null){
                          String value = oldValue.toString().replace("\"" , "\\\"");
                          value = value.toString().replace("'" , "\\\'");
                          field.set(object, value);
                      }
                   }

            } catch (IllegalArgumentException e) {
                logger.error(Utils.getMethodName(), e);
            } catch (IllegalAccessException e) {
                logger.error(Utils.getMethodName(), e);
            }

        } // for

}

    public static void insertReflectionToStringQuotation(Object object) {
        if( object != null) {
            @SuppressWarnings("rawtypes")
            Class clazz = object.getClass();

            Field[] fields = clazz.getDeclaredFields();

            for ( Field field : fields ) {

                if (Modifier.isStatic(field.getModifiers())) { continue;}

                field.setAccessible(true);

                try {
                    Object type = field.getType();

                    if("class java.lang.String".equals(type.toString())){
                        Object oldValue = field.get(object);

                        if(oldValue!=null){
                            String value = oldValue.toString().replace("\\\"" , "\"");
                            value = value.toString().replace("\\\'" , "'");
                            field.set(object, value);
                        }
                    }


                } catch (IllegalArgumentException e) {
            
                    logger.error(Utils.getMethodName(), e);
                } catch (IllegalAccessException e) {
                    logger.error(Utils.getMethodName(), e);
                }

            } // for
        }

    }

    public static List<Map<String, Object>> getFileParse(String fileName, String dateTimeStr) {

        File file = new File(fileName);
        
        List<Map<String, Object>> myList = new ArrayList<Map<String,Object>>();        
        String[] dateTimeArr = dateTimeStr.split(" "); 
        
        try {
            // BufferedReader 변수에 file을 넣는다
            BufferedReader reader = new BufferedReader(new FileReader(file));
 
            // 파일을 한줄씩 읽어 넣기 위한 변수 line
            String line = null;
 
            // 하나의 line을 split 하여 넣을 배열 splitedStr
            String[] splitedStr = null;

            //System.out.println("READING FILE...............................");            
            
            // 한 줄씩 읽어서 line에 넣은 후 null이 아니면 실행
            while( (line = reader.readLine()) != null ) {
 
                // 초기화
                splitedStr = null;
 
                // 탭을 기준으로 잘라서 splitedStr 에 넣는다
                splitedStr = line.split("\\|");

                
                //System.out.println(line);                
                
                Map<String,Object> myMap = new HashMap<String, Object>();            
                myMap.put("server", splitedStr[2].trim());
                myMap.put("serial", splitedStr[1].trim());                
                myMap.put("status", splitedStr[3].trim());
                myMap.put("yy", dateTimeArr[0]);                
                myMap.put("mms", dateTimeArr[1]);                
                myMap.put("dd", dateTimeArr[2]);                
                myMap.put("hh", dateTimeArr[3]);
                myMap.put("mm", dateTimeArr[4]);                
                                
                myList.add(myMap);                
            }
            
            reader.close();
 
        } catch (FileNotFoundException fnf) {
            //fnf.printStackTrace();
            logger.error(Utils.getMethodName(), fnf);
        } catch( IOException e) {
            //e.printStackTrace();
            logger.error(Utils.getMethodName(), e);
        }

        //return result.toString();
        return myList;

      }    
    
}
