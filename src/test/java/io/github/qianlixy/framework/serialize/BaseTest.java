package io.github.qianlixy.framework.serialize;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.qianlixy.framework.serialize.bean.TestBean;
import io.github.qianlixy.framework.serialize.impl.AbstractWrapSerializer;
import io.github.qianlixy.framework.serialize.wrap.CacheClassSerializeWrapper;

public abstract class BaseTest {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	protected static TestBean testBean;

	protected static List<TestBean> testList = null;

	protected static Set<TestBean> testSet = null;

	protected static Map<String, TestBean> testMap = null;

	@BeforeClass
	public static void beforeClass() {
		List<String> list = new ArrayList<>();
		list.add("111111");
		list.add("222222");
		list.add("333333");
		Set<String> set = new HashSet<>();
		set.add("111111");
		set.add("222222");
		set.add("333333");
		Map<String, Object> map = new HashMap<>();
		map.put("111111", "111111");
		map.put("222222", "222222");
		map.put("333333", "333333");
		testBean = new TestBean("Dong", 22, list, set, map);
		
		testList = new ArrayList<>();
		((List<TestBean>) testList).add(new TestBean("Dong", 21));
		((List<TestBean>) testList).add(new TestBean("Dong1", 22));
		((List<TestBean>) testList).add(new TestBean("Dong2", 23));
		((List<TestBean>) testList).add(new TestBean("Dong3", 24));
		((List<TestBean>) testList).add(new TestBean("Dong4", 25));

		testSet = new HashSet<>();
		((Set<TestBean>) testSet).add(new TestBean("Dong", 21));
		((Set<TestBean>) testSet).add(new TestBean("Dong1", 22));
		((Set<TestBean>) testSet).add(new TestBean("Dong2", 23));
		((Set<TestBean>) testSet).add(new TestBean("Dong3", 24));
		((Set<TestBean>) testSet).add(new TestBean("Dong4", 25));
		((Set<TestBean>) testSet).add(new TestBean("Dong5", 26));

		testMap = new HashMap<>();
		((Map<String, TestBean>) testMap).put("1", new TestBean("Dong0", 21));
		((Map<String, TestBean>) testMap).put("2", new TestBean("Dong1", 22));
		((Map<String, TestBean>) testMap).put("3", new TestBean("Dong2", 23));
		((Map<String, TestBean>) testMap).put("4", new TestBean("Dong3", 24));
		((Map<String, TestBean>) testMap).put("5", new TestBean("Dong4", 25));
		((Map<String, TestBean>) testMap).put("6", new TestBean("Dong5", 26));
	}

	private SerializeWrapper SerializeWrapper = new CacheClassSerializeWrapper();

	@Before
	public void before() {
		ExtensiveSerializer serializer = getSerializer();
		if (serializer instanceof AbstractWrapSerializer) {
			((AbstractWrapSerializer) serializer).setSerializeWrapper(SerializeWrapper);
		}
	}

	public void testSerialize() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testBean);
		if (log.isDebugEnabled()) {
			log.debug("Byte array is {}", serialize);
			log.debug("Byte array's length is {}", serialize.length);
		}
		Object deserialize = getSerializer().deserialize(serialize);
		if (log.isDebugEnabled()) {
			log.debug("Deserialize is {}", deserialize);
		}
		assertEquals(testBean, deserialize);
	}

	public void testSerialize_withList() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testList);
		if (log.isDebugEnabled()) {
			log.debug("Byte array is {}", serialize);
			log.debug("Byte array's length is {}", serialize.length);
		}
		Object deserialize = getSerializer().deserialize(serialize);
		assertEquals(testList, deserialize);
	}

	public void testSerialize_withSet() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testSet);
		if (log.isDebugEnabled()) {
			log.debug("Byte array is {}", serialize);
			log.debug("Byte array's length is {}", serialize.length);
		}
		Object deserialize = getSerializer().deserialize(serialize);
		assertEquals(testSet, deserialize);
	}

	public void testSerialize_withMap() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testMap);
		if (log.isDebugEnabled()) {
			log.debug("Byte array is {}", serialize);
			log.debug("Byte array's length is {}", serialize.length);
		}
		Object deserialize = getSerializer().deserialize(serialize);
		assertEquals(testMap, deserialize);
	}

	@Test
	public void testSerialize_batch() throws ClassNotFoundException, IOException {
		for (int i = 0; i < 200000; i++) {
			testSerialize();
		}
	}

	protected abstract ExtensiveSerializer getSerializer();

}
