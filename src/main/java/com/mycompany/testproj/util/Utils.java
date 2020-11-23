package com.mycompany.testproj.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class
 *
 * @author hongga
 *
 */
public class Utils {

    static Properties properties = null;

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    
    /**
     * Validate properties
     *
     * @param propertiesClazz
     * @param properties
     * @return Map with invalid properties
     */
    public static Map<String, String> validateProperties(final Class<?> propertiesClazz, final Properties properties) {
        Field[] fields = propertiesClazz.getDeclaredFields();
        Map<String, String> validatedProperties = new HashMap<String, String>();

        try {
            for (Field field : fields) {
                if (properties.getProperty(String.valueOf(field.get(propertiesClazz))) == null
                        || "".equals(properties.getProperty(String.valueOf(field.get(propertiesClazz))))) {
                    validatedProperties.put(field.getName(), String.valueOf(field.get(propertiesClazz)));
                }
            }
        } catch (IllegalArgumentException iare) {
            logger.error("Exception msg : ", iare);
        } catch (IllegalAccessException iace) {
            logger.error("Exception msg : ", iace);
        }

        return validatedProperties;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static Properties getProperites() throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Loading properties using Class Loader ...");
        }

        return properties;
    }


    private static byte skUserKey[]   = "SK PICKING AND PACKING".getBytes();

	//Round keys for encryption or decryption
	private static int pdwRoundKey[] = new int[32];

	public static String getMethodName() {
		String name = "";

		if(Thread.currentThread() != null
			&& 	Thread.currentThread().getStackTrace() != null
			&& 	Thread.currentThread().getStackTrace().length >= 2
				) {
			name = Thread.currentThread().getStackTrace()[1].getMethodName();
		}
		return name;
	}

}
