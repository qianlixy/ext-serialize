package io.github.qianlixy.framework.serialize;

import java.io.IOException;

import org.junit.Test;

import io.github.qianlixy.framework.serialize.impl.KryoSerializer;

public class KryoSerializerTest extends BaseTest {

	private ExtensiveSerializer serializer = new KryoSerializer();

	@Test
	public void testSerialize() throws ClassNotFoundException, IOException {
		super.testSerialize();
	}

	@Test
	public void testSerialize_withList() throws IOException, ClassNotFoundException {
		super.testSerialize_withList();
	}

	@Override
	protected ExtensiveSerializer getSerializer() {
		return serializer;
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
