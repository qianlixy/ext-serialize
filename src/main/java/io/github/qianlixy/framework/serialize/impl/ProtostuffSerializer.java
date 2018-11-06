package io.github.qianlixy.framework.serialize.impl;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import io.github.qianlixy.framework.serialize.ExtensiveSerializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtostuffSerializer extends AbstractWrapSerializer implements ExtensiveSerializer {

	static {
		System.setProperty("protostuff.runtime.collection_schema_on_repeated_fields", "true");
	}

	/**
	 * 无参构造工具
	 */
	private static Objenesis objenesis = new ObjenesisStd(true);

	public byte[] wrapSerialize(Object cache) {
		log.debug("The cache serializing");
		if (null == cache) {
			throw new NullPointerException("The cache cannot be null");
		}
		@SuppressWarnings("unchecked")
		Schema<Object> schema = (Schema<Object>) RuntimeSchema.getSchema(cache.getClass());
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			return ProtostuffIOUtil.toByteArray(cache, schema, buffer);
		} finally {
			buffer.clear();
		}
	}

	public Object deserialize(byte[] bytes, Class<?> clazz) {
		@SuppressWarnings("unchecked")
		Schema<Object> schema = (Schema<Object>) RuntimeSchema.getSchema(clazz);
		Object cache = objenesis.newInstance(clazz);
		ProtostuffIOUtil.mergeFrom(bytes, cache, schema);
		return cache;
	}

}
