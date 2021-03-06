package com.yanger.generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

import org.apache.commons.lang3.StringUtils; 

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WordFileUtils {
	public static final String WORD_ROOT_FILE = "words/wordroot.properties";
	public static final String WORD_ROOT_TABLE_FILE = "words/wordroot-table.properties";
	private static Map<String, Word> wordMap = new HashMap<>();
	public static boolean hasShowHelp = false; 

	private WordFileUtils() {

	} 

	public synchronized static void initWordMap() {
		if (!hasShowHelp) {
			log.info("**************************************************************");
			log.info("**开始加载词根表，词根优先级如下： ");
			log.info("**1：以下划线分隔的命名");
			log.info("**2：自带标准词根表(wordroot.properties)");
			log.info("**3：自带标准词根表业务表(wordroot-table.properties)");
			log.info("**4：数据库中的大小写");
			log.info("**5：全部改成小写");
			log.info("**************************************************************");
			hasShowHelp = true;
		}
		try {
			init(WORD_ROOT_TABLE_FILE);
			init(WORD_ROOT_FILE);
		} catch (IOException e) {
			log.warn("{}", e);
		}
	}

	private static String getClassNameWithoutPackage(Class<?> cl) {
		String className = cl.getName();
		int pos = className.lastIndexOf('.') + 1;
		if (pos == -1) {
			pos = 0;
		}
		return className.substring(pos);
	}

	public static String getRealPathName(Class<?> cl) {
		java.net.URL url = cl.getResource(getClassNameWithoutPackage(cl) + ".class");
		if (url != null) {
			return url.getPath();
		}
		return null;
	}

	private static void init(String fileName) throws IOException {
		InputStream is = WordFileUtils.class.getClassLoader().getResourceAsStream(fileName);
		if (is != null) {
			try {
				log.info("Load wordfile " + fileName);
				PropertyResourceBundle bundle = new PropertyResourceBundle(is);
				Enumeration<String> ration = bundle.getKeys();
				while (ration.hasMoreElements()) {
					String objKey = ration.nextElement();
					if (objKey != null) {
						String name = (String) objKey;
						String key = name.toLowerCase();
						String value = bundle.getString(name);
						if (StringUtils.isNotEmpty(value)) {
							value = new String(value.getBytes("UTF-8"));
							Word word = new Word(name, value);
							wordMap.put(key, word);
						}
					}
				}
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
	}

	/**
	 * 得到对象名，首字母大写。其他规则如下：
	 * <ol>
	 * <li>包含下划线的，每个下划线后面的第1字符大写，删除下划线后连接作为Name
	 * <li>否则：Wordfile.properties有定义，则用Wordfile.properties对应行中等于号之前的内容作为Name
	 * <li>否则：传入内容包含大写和小写的，传入内容作为Name
	 * <li>否则：传入内容全部小写作为Name
	 * </ol>
	 * 
	 * @param name
	 *            传入名称（表名或字段名）
	 * @return 类实例名或属性名
	 */
	public static String getBeautyObjectName(String name) {
		if(name==null || name.isEmpty()) {
			throw new IllegalArgumentException("name must have value.");
		}
		String key = name.toLowerCase();
		key = key.replace("_", "");
		String beautyName = null;
		if (name.contains("_")) {
			StringBuilder sb = new StringBuilder();
			String[] fields = name.split("_");
			sb.append(fields[0].toLowerCase());
			for (int i = 1; i < fields.length; i++) {
				String temp = fields[i];
				sb.append(temp.substring(0, 1).toUpperCase());
				sb.append(temp.substring(1).toLowerCase());
			}
			beautyName = sb.toString();
		} else if (wordMap.containsKey(key)) {
			beautyName = ((Word) wordMap.get(key)).getName();
			beautyName = upperCaseFirstChar(beautyName);
		} else if (!name.toLowerCase().equals(name) && !name.toUpperCase().equals(name)) {
			beautyName = name;
		} else {
			beautyName = name.toLowerCase();
		}

		beautyName = upperCaseFirstChar(beautyName);

		// log.info("getBeautyObjectName(\"" + name + "\")=" +
		// beautyName);

		return beautyName;
	}

	/**
	 * 得到实例名，如果第前两个字符均为大写，则直接返回，否则返回首字母小写的内容 。其他规则如下：
	 * <ol>
	 * <li>包含下划线的，每个下划线后面的第1字符大写，删除下划线后连接作为Name
	 * <li>否则：Wordfile.properties有定义，则用Wordfile.properties对应行中等于号之前的内容作为Name
	 * <li>否则：传入内容包含大写和小写的，传入内容作为Name
	 * <li>否则：传入内容全部小写作为Name
	 * </ol>
	 * 
	 * @param name
	 *            传入名称（表名或字段名）
	 * @return 类实例名或属性名
	 */
	public static String getBeautyInstanceName(String name) {
		String beautyName = getBeautyObjectName(name);
		// 如果第前两个字符均为大写，则直接返回，否则返回首字母小写的内容
		if (beautyName.length() > 1 && Character.isUpperCase(beautyName.charAt(1))) {
			// Do nothing
		} else {
			beautyName = lowerCaseFirstChar(beautyName);
		}
		// log.info("getBeautyInstanceName(\"" + name + "\")=" +
		// beautyName);
		return beautyName;
	}

	public static String getBeautyDesc(String name) {
		String beautyDesc = "";
		String key = name.toLowerCase();
		key = key.replace("_", "");
		if (wordMap.containsKey(key)) {
			beautyDesc = ((Word) wordMap.get(key)).getDesc();
		} else {
			beautyDesc = name;
		}

		return beautyDesc;
	}

	/**
	 * 将字符串的第一个字符小写. <br>
	 * <br>
	 * <b>示例 </b> <br>
	 * StringUtils.lowerCaseFirstChar(&quot;ABc&quot;) 返回 &quot;aBc&quot;
	 * 
	 * @param iString
	 *            传入字符串
	 * @return 传出字符串
	 */
	public static String lowerCaseFirstChar(String iString) {
		String newString;
		newString = iString.substring(0, 1).toLowerCase() + iString.substring(1);
		return newString;
	}

	/**
	 * 将字符串的第一个字符大写. <br>
	 * <br>
	 * <b>示例 </b> <br>
	 * StringUtils.upperCaseFirstChar(&quot;aBc&quot;) 返回 &quot;ABc&quot;
	 * 
	 * @param iString
	 *            传入字符串
	 * @return 传出字符串
	 */
	public static String upperCaseFirstChar(String iString) {
		String newString;
		newString = iString.substring(0, 1).toUpperCase() + iString.substring(1);
		return newString;
	}

	public static void printString(String value) {
		try {
			String[] codes = new String[] { "GBK", "ISO8859-1", "UTF-8" };
			for (int i = 0; i < codes.length; i++) {
				for (int j = 0; j < codes.length; j++) {
					if (i != j) {
						log.info(codes[i] + "--" + codes[j] + "====" + new String(value.getBytes(codes[i]), codes[j]));
					}
				}
			}
			for (int i = 0; i < codes.length; i++) {
				log.info(codes[i] + "====" + new String(value.getBytes(codes[i])));
			}
			for (int i = 0; i < codes.length; i++) {
				log.info(codes[i] + "==----==" + new String(value.getBytes(), codes[i]));
			}
		} catch (Exception e) {
			log.warn("{}", e);
		}
	}
}

class Word {
	private String key = "";

	private String name = "";

	private String desc = "";

	public Word(String key, String lineContext) {

		this.name = key;
		if (StringUtils.isNotEmpty(lineContext)) {
			desc = lineContext;
		}

		this.key = key.toLowerCase();

	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "key=" + key + ",name=" + name + ",desc=" + desc;
	}
}