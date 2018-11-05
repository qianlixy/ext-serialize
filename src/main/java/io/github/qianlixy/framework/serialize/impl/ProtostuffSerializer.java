package io.github.qianlixy.framework.serialize.impl;

import java.io.IOException;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import io.github.qianlixy.framework.serialize.ExtensiveSerializer;
import io.github.qianlixy.framework.serialize.SerializeWrapper;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtostuffSerializer implements ExtensiveSerializer {

	static {
		System.setProperty("protostuff.runtime.collection_schema_on_repeated_fields", "true");
	}

	private SerializeWrapper wrapper;
	
	public ProtostuffSerializer() {
		this.wrapper = new DefaultSerializeWrapper();
	}

	/**
	 * 无参构造工具
	 */
	private static Objenesis objenesis = new ObjenesisStd(true);

	@Override
	public byte[] serialize(Object obj, Class<?> clazz) throws IOException {
		if (null == obj) {
			throw new NullPointerException("The serializing object cannot be null");
		}
		@SuppressWarnings("unchecked")
		Schema<Object> schema = (Schema<Object>) RuntimeSchema.getSchema(clazz);
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} finally {
			buffer.clear();
		}
	}

	public byte[] serialize(Object obj) throws IOException {
		return wrapper.wrap(this, obj);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object deserialize(byte[] bytes, Class<?> clazz) throws IOException, ClassNotFoundException {
		Schema<Object> schema = (Schema<Object>) RuntimeSchema.getSchema(clazz);
		schema.newMessage();
		Object cache = objenesis.newInstance(clazz);
		ProtostuffIOUtil.mergeFrom(bytes, cache, schema);
		return cache;
	}

	@Override
	public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		return wrapper.unwrap(this, bytes);
	}

}
