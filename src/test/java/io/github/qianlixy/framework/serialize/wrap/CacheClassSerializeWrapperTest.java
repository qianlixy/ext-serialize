package io.github.qianlixy.framework.serialize.wrap;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import io.github.qianlixy.framework.serialize.SerializeWrapper;
import io.github.qianlixy.framework.serialize.bean.TestBean;

public class CacheClassSerializeWrapperTest {
	
	SerializeWrapper wrapper = new CacheClassSerializeWrapper();

	@Test
	public void testWrap() throws IOException {
		byte[] prefix = wrapper.wrap(new TestBean("a", 11));
		assertEquals(17, prefix.length);
	}
	
	@Test
	public void testUnwrap() throws ClassNotFoundException, IOException {
		WrapBean wrapBean = wrapper.unwrap(wrapper.wrap(new TestBean("", 11)));
		assertEquals(TestBean.class, wrapBean.getClazz());
		assertEquals(0, wrapBean.getSource().length);
	}
	
	@Test
	public void testIsWrap() throws IOException {
		assertEquals(true, wrapper.isWrap(wrapper.wrap(new TestBean("", 22))));
	}
}
