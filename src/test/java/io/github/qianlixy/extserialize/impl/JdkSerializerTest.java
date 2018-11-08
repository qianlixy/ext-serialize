package io.github.qianlixy.extserialize.impl;

import java.io.IOException;

import org.junit.Test;

import io.github.qianlixy.extserialize.BaseTest;
import io.github.qianlixy.extserialize.ExtensiveSerializer;
import io.github.qianlixy.extserialize.impl.JdkSerializer;

public class JdkSerializerTest extends BaseTest {
	private JdkSerializer serializer = new JdkSerializer();

	protected ExtensiveSerializer getSerializer() {
		return serializer;
	}

	@Override
	@Test
	public void testSerialize() throws IOException, ClassNotFoundException {
		super.testSerialize();
	}

	@Override
	@Test
	public void testSerialize_withList() throws IOException, ClassNotFoundException {
		super.testSerialize_withList();
	}

	@Override
	@Test
	public void testSerialize_withMap() throws IOException, ClassNotFoundException {
		super.testSerialize_withMap();
	}

	@Override
	@Test
	public void testSerialize_withSet() throws IOException, ClassNotFoundException {
		super.testSerialize_withSet();
	}

}
