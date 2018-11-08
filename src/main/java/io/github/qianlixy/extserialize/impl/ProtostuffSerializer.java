package io.github.qianlixy.extserialize.impl;

import java.util.Collection;
import java.util.Map;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import io.github.qianlixy.extserialize.ExtensiveSerializer;
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

	public byte[] doSerialize(Object serializable) {
		if (null == serializable) {
			throw new NullPointerException("The serializable cannot be null");
		}
		@SuppressWarnings("unchecked")
		Schema<Object> schema = (Schema<Object>) RuntimeSchema.getSchema(serializable.getClass());
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			return ProtostuffIOUtil.toByteArray(serializable, schema, buffer);
		} finally {
			buffer.clear();
		}
	}

	public Object deserialize(byte[] bytes, Class<?> clazz) {
		@SuppressWarnings("unchecked")
		Schema<Object> schema = (Schema<Object>) RuntimeSchema.getSchema(clazz);
		Object source = objenesis.newInstance(clazz);
		ProtostuffIOUtil.mergeFrom(bytes, source, schema);
		if (source instanceof Wrapper) {
			return ((Wrapper) source).source;
		}
		return source;
	}

	@Override
	protected Object doWrap(Object obj) {
		if (obj instanceof Map || obj instanceof Collection) {
			return new Wrapper(obj);
		}
		return super.doWrap(obj);
	}

	public static class Wrapper {

		public Object source;

		public Wrapper(Object source) {
			this.source = source;
		}
	}

}
