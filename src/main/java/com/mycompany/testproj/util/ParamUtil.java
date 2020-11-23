package com.mycompany.testproj.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParamUtil {
	private static final Log log = LogFactory .getLog(ParamUtil.class);
	/**
	 * request로 넘어온 parameter를 HashMap에 담아 리턴 , 배열로 넘어올 경우 배열로 담는다(i.e checkbox, radio)
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getReqParamHashMap(HttpServletRequest request ,boolean ... escape)throws Exception{
		HashMap hm = new HashMap();
		

		Map map = request.getParameterMap();

		Iterator it = map.keySet().iterator();

		Object key = null;
		String[] value = null;

		while (it.hasNext()){
			key = it.next();
			value = (String[]) map.get(key);

			String[] values = request.getParameterValues((String) key);

			// 체크박스 또는 라디오버튼등 같은 name에 여러값이 있을경우 - 배열로 해쉬맵에 셋팅한다.
			if (values.length > 1){
				ArrayList ar = new ArrayList();

				for (int i = 0; i < value.length; i++){
					if(escape !=null && escape.length >0 && escape[0]) {
						ar.add(i, getParameterEscape(value[i]));
					}else {
						ar.add(i, value[i]);
					}
				}

				hm.put(key, ar);
			} else	{
				if(escape !=null && escape.length >0 && escape[0]) {
					hm.put(key, getParameterEscape(value[0]));
				}else {
					hm.put(key, value[0]);
				}
			}
		}
		
		return hm;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getReqParamEscapeMap(Map<String,Object> map ,boolean ... escape)throws Exception{
		HashMap hm = new HashMap();

		Iterator it = map.keySet().iterator();

		Object key = null;
		String value = null;

		while (it.hasNext()){
			key = it.next();
			value = (String) map.get(key);

				if(escape !=null && escape.length >0 && escape[0]) {
					hm.put(key, getParameterEscape(value));
				}else {
					hm.put(key, value);
				}
			
		}
		
		return hm;
	}

	public static String getParameterEscape(String parameter) {

		String value = parameter;

		if (value == null) {
			return null;
		}

		StringBuilder strBuff = new StringBuilder();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
				case '<':
					strBuff.append("&lt;");
					break;
				case '>':
					strBuff.append("&gt;");
					break;
				case '&':
					strBuff.append("&amp;");
					break;
				case '"':
					strBuff.append("&quot;");
					break;
				case '\'':
					strBuff.append("&apos;");
					break;
				default:
					strBuff.append(c);
					break;
			}
		}

		return strBuff.toString();
	}

}
