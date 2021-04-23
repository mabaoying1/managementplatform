package com.bsoft.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.bsoft.constant.Ind;
import com.bsoft.constant.Inds;
import com.bsoft.constant.Theme;
import com.bsoft.constant.ThemeList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class XmlToTheme {
	private static Logger logger = LoggerFactory.getLogger(XmlToTheme.class);
	private static Resource resource = new ClassPathResource("theme");
	private static Resource indResource = new ClassPathResource("ind");
	private static Map<String, Theme> themeCache = Maps.newHashMap();
	private static Map<String, Ind> indCache = Maps.newHashMap();

	public synchronized static boolean flushThemeList() throws Exception {
		themeCache.clear();
		indCache.clear();
		List<Ind> inds = xmlToInd();
		for (Ind ind : inds) {
			indCache.put(ind.getIndKey(), ind);
		}

		List<Theme> themes = xmlToThemes();
		for (Theme theme : themes) {
			Inds indzz = theme.getInds();
			List<Ind> indz = indzz.getInds();
			for(Ind ind:indz){
				Ind ind2 = indCache.get(ind.getIndKey());
				BeanUtils.copyProperties(ind2, ind, "title");
			}
			
			theme.setInds(indzz);
			themeCache.put(theme.getTheme_key(), theme);
		}
		return true;
	}

	/**
	 * 描述：得到xml转换后的所有Theme对象
	 * 
	 * @author yinxu
	 * @return List<Theme> 可能为null
	 * @throws Exception
	 * @Creating_Time 2019年2月25日
	 */
	public static List<Theme> xmlToThemes() throws Exception {
		List<Theme> themes = Lists.newArrayList();
		List<ThemeList> list = (List<ThemeList>) xmlToObject(ThemeList.class, resource);
		for (ThemeList tl : list) {
			themes.addAll(tl.getThemeNames());
		}
		return themes;
	}

	/**
	 * 描述：得到xml转换后的所有Theme对象
	 * 
	 * @author yinxu
	 * @return List<Theme> 可能为null
	 * @throws Exception
	 * @Creating_Time 2019年2月25日
	 */
	public static List<Ind> xmlToInd() throws Exception {
		List<Ind> inds = Lists.newArrayList();
		List<Inds> list = (List<Inds>) xmlToObject(Inds.class, indResource);
		for (Inds tl : list) {
			inds.addAll(tl.getInds());
		}
		return inds;
	}

	/**
	 * 描述：得到xml转换后的所有ind对象
	 * 
	 * @author yinxu
	 * @param <T>
	 * @param <T>
	 * @return List<Theme> 可能为null
	 * @throws Exception
	 * @Creating_Time 2019年2月25日
	 */
	public static <T> List<T> xmlToObject(Class<T> clazz, Resource r) throws Exception {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		List<T> list = Lists.newArrayList();
		File directory = new File(r.getURI());
		if (!directory.exists()) {
			throw new Exception("请检查以下路径是否正确：" + r.getURI());
		}
		File[] files = null;
		if (directory.isDirectory()) {
			files = directory.listFiles();
		} else {
			throw new Exception(r.getURI() + "文件不存在！");
		}
		if (null == files) {
			throw new Exception("路径" + r.getURI() + "下未找到文件");
		}
		for (File file : files) {
			try {
				Object object = unmarshaller.unmarshal(file);
				list.add((T) object);
			} catch (Exception e) {
				logger.error("路径：" + file.getAbsolutePath() + "。文件名：" + file.getName() + "。异常信息：" + e.getMessage());
			}
		}

		return list;
	}

	public static Theme getThemeByKey(String theme_key) {
		if (themeCache.size() == 0) {
			try {
				flushThemeList();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return themeCache.get(theme_key);
	}

	/**
	 * 描述：根据 indbAlias[指标别名]参数, 筛选出相应Theme
	 * 
	 * @param indbAlias
	 *            指标别名
	 * @param themes
	 *            所有Theme对象
	 * @throws JAXBException
	 * @throws IOException
	 * @author yinxu
	 * @return Map<String, Object> 单个主题
	 * @Creating_Time 2019年2月25日
	 */
	public static Map<String, Object> getTheme(String indbAlias) throws JAXBException, IOException {
		// if (themes != null && themes.size() > 0) {
		// for (int i = 0; i < themes.size(); i++) {
		// if (indbAlias.equals(themes.get(i).getTheme_key())) {
		// return objectToMap(themes.get(i));
		// }
		// }
		// }
		return null;
	}

	/**
	 * 描述：根据 indbAlias[指标别名]参数, 筛选出相应Theme
	 * 
	 * @param indbAlias
	 *            指标别名
	 * @throws JAXBException
	 * @throws IOException
	 * @author yinxu
	 * @return Map<String, Object> 单个主题
	 * @Creating_Time 2019年2月25日
	 */
	public static Map<String, Object> xmlToTheme(String indbAlias) throws JAXBException, IOException {
		// if (themes != null && themes.size() > 0) {
		// for (int i = 0; i < themes.size(); i++) {
		// if (indbAlias.equals(themes.get(i).getTheme_key())) {
		// return objectToMap(themes.get(i));
		// }
		// }
		// }
		return null;
	}

	/**
	 * 描述：对象转Map
	 * 
	 * @param obj
	 * @author yinxu
	 * @return Map<String,Object>
	 * @Creating_Time 2019年2月25日
	 */
	public static Map<String, Object> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 描述：Map转对象
	 * 
	 * @param map
	 * @param beanClass
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @author yinxu
	 * @return Object
	 * @Creating_Time 2019年2月25日
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)
			throws InstantiationException, IllegalAccessException {
		if (map == null)
			return null;
		Object obj = beanClass.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}
			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}

		return obj;
	}

	/**
	 * 把指标维度List转换为map
	 * 
	 * @param themeList
	 * @return
	 */
	public static Map<String, Object> themeListToMap(List<Theme> themeList) {

		if (null != themeList && themeList.size() > 0) {
			Map<String, Object> resultMap = new HashMap<>();
			Map<String, Object> map;
			for (Theme theme : themeList) {
				map = new HashMap<>();
				// map.put("indbName_value", theme.getIndbName_value());
				// // 封装维度
				// for (Dim dim : theme.getDims()) {
				// map.put(dim.getDimKey(), dim.getDimValue());
				// }
				// resultMap.put(theme.getIndbName_key(), map);
			}
			return resultMap;
		}
		return null;
	}

	/**
	 * 描述：写入xml 节点
	 * 
	 * @param theme
	 *            主题对象
	 * @throws Exception
	 * @author yinxu
	 * @return void
	 * @Creating_Time 2019年2月27日
	 */
	public static void writerXml(Theme theme) throws Exception {

		File file = resource.getFile();
		Document read = null;
		Element root = null;
		// 如果文件存在 就获得它的根节点
		if (file.exists()) {
			SAXReader reader = new SAXReader();
			read = reader.read(file);
			// 得到根节点
			root = read.getRootElement();
		} else {// 如果文件不存在
			read = DocumentHelper.createDocument();
			// 创建一个根节点
			root = read.addElement("indbs");
		}
		// 在根节点下创建一个新节点student节点
		Element student = root.addElement("indbName");
		// student.addAttribute("indbName_key", theme.getIndbName_key());
		// student.addAttribute("indbName_value", theme.getIndbName_value());
		// // 在student下创建节点 并给它们赋值
		// Element dimTime = student.addElement("dimTime");
		// dimTime.setText(theme.getDimTime());
		// Element dimOrganization = student.addElement("dimOrganization");
		// dimOrganization.setText(theme.getDimOrganization());
		// Element dimDept = student.addElement("dimDept");
		// dimDept.setText(theme.getDimDept());
		// Element dimDoctor = student.addElement("dimDoctor");
		// dimDoctor.setText(theme.getDimDoctor());
		// Element dimCategory = student.addElement("dimCategory");
		// dimCategory.setText(theme.getDimCategory());
		OutputFormat outputFormat = new OutputFormat("\t", true, "UTF-8");
		outputFormat.setTrimText(true);
		XMLWriter xmlWriter = new XMLWriter(new FileWriter(file), outputFormat);// 写入XML文件的位置
		xmlWriter.write(read);// 开始写入XML文件 写入Document对象
		xmlWriter.close();
	}

}
