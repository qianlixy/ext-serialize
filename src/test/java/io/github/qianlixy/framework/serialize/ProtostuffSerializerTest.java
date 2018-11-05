package io.github.qianlixy.framework.serialize;

import java.io.IOException;

import org.junit.Test;

import io.github.qianlixy.framework.serialize.impl.ProtostuffSerializer;

public class ProtostuffSerializerTest extends BaseTest {

	private ExtensiveSerializer serializer = new ProtostuffSerializer();

	protected ExtensiveSerializer getSerializer() {
		return serializer;
	}

	@Test
	public void testSerialize() throws IOException, ClassNotFoundException {
		super.testSerialize();
	}

	@Test
	public void testSerialize_withList_withWrapper() throws IOException, ClassNotFoundException {
		super.testSerialize_withList_withWrapper();
	}

	@Test
	public void testSerialize_withMap_withWrapper() throws IOException, ClassNotFoundException {
		super.testSerialize_withMap_withWrapper();
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

}
