package io.github.qianlixy.framework.serialize;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.qianlixy.framework.serialize.bean.TestBean;

public abstract class BaseTest {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	protected static TestBean testBean = new TestBean("Dong", 22);

	protected static List<TestBean> testList = null;

	protected static Set<TestBean> testSet = null;

	protected static Map<String, TestBean> testMap = null;

	@BeforeClass
	public static void beforeClass() {
		testList = new ArrayList<>();
		((List<TestBean>) testList).add(new TestBean("Dong", 21));
		((List<TestBean>) testList).add(new TestBean("Dong1", 22));
		((List<TestBean>) testList).add(new TestBean("Dong2", 23));
		((List<TestBean>) testList).add(new TestBean("Dong3", 24));
		((List<TestBean>) testList).add(new TestBean("Dong4", 25));
		((List<TestBean>) testList).add(new TestBean("Dong5", 26));

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

	public void testSerialize() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testBean);
		// log.info("Byte array's length is {}", serialize.length);
		Object deserialize = getSerializer().deserialize(serialize);
		assertEquals(testBean, deserialize);
	}

	public void testSerialize_withList() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testList);
		log.info("Byte array's length is {}", serialize.length);
		Object deserialize = getSerializer().deserialize(serialize);
		assertEquals(testList, deserialize);
	}

	public void testSerialize_withSet() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testSet);
		log.info("Byte array's length is {}", serialize.length);
		Object deserialize = getSerializer().deserialize(serialize);
		assertEquals(testSet, deserialize);
	}

	public void testSerialize_withMap() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testMap);
		log.info("Byte array's length is {}", serialize.length);
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
