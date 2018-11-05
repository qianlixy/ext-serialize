package io.github.qianlixy.framework.serialize;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.qianlixy.framework.serialize.bean.TestBean;

public abstract class BaseTest {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	protected static TestBean testBean = new TestBean("Dong", 22);

	protected static List<TestBean> testList = null;

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

		testMap = new HashMap<>();
		((Map<String, TestBean>) testMap).put("1", new TestBean("Dong0", 21));
		((Map<String, TestBean>) testMap).put("2", new TestBean("Dong1", 22));
		((Map<String, TestBean>) testMap).put("3", new TestBean("Dong2", 23));
		((Map<String, TestBean>) testMap).put("4", new TestBean("Dong3", 24));
		((Map<String, TestBean>) testMap).put("5", new TestBean("Dong4", 25));
		((Map<String, TestBean>) testMap).put("6", new TestBean("Dong5", 26));
	}

	public void testSerialize() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testBean, TestBean.class);
		log.info("Byte array's length is {}", serialize.length);
		Object deserialize = getSerializer().deserialize(serialize, TestBean.class);
		assertEquals(testBean, deserialize);
	}

	public void testSerialize_withList() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testList, ArrayList.class);
		log.info("Byte array's length is {}", serialize.length);
		Object deserialize = getSerializer().deserialize(serialize, ArrayList.class);
		assertEquals(testList, deserialize);
	}

	public void testSerialize_withMap() throws IOException, ClassNotFoundException {
		byte[] serialize = getSerializer().serialize(testMap, HashMap.class);
		log.info("Byte array's length is {}", serialize.length);
		Object deserialize = getSerializer().deserialize(serialize, HashMap.class);
		assertEquals(testMap, deserialize);
	}

	public void testSerialize_withList_withWrapper() throws IOException, ClassNotFoundException {
		CollectionWrapper<Object> obj = new CollectionWrapper<>(testList);
		byte[] serialize = getSerializer().serialize(obj, CollectionWrapper.class);
		log.info("Byte array's length is {}", serialize.length);
		CollectionWrapper<Object> deserialize = (CollectionWrapper<Object>) getSerializer().deserialize(serialize,
				CollectionWrapper.class);
		assertEquals(testList, deserialize.collection);
	}

	public void testSerialize_withMap_withWrapper() throws IOException, ClassNotFoundException {
		CollectionWrapper<Object> obj = new CollectionWrapper<>(testMap);
		byte[] serialize = getSerializer().serialize(obj, CollectionWrapper.class);
		log.info("Byte array's length is {}", serialize.length);
		CollectionWrapper<Object> deserialize = (CollectionWrapper<Object>) getSerializer().deserialize(serialize,
				CollectionWrapper.class);
		assertEquals(testMap, deserialize.collection);
	}

	// @Test
	public void testSerialize_batch() throws ClassNotFoundException, IOException {
		for (int i = 0; i < 100000; i++) {
			testSerialize();
		}
	}

	protected abstract ExtensiveSerializer getSerializer();

}
