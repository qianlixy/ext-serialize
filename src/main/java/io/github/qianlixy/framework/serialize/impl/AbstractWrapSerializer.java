package io.github.qianlixy.framework.serialize.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.qianlixy.framework.serialize.CollectionWrapper;
import io.github.qianlixy.framework.serialize.ExtensiveSerializer;
import io.github.qianlixy.framework.serialize.exception.SerializeException;

public abstract class AbstractWrapSerializer implements ExtensiveSerializer {
	
	private static final String SERIALIZION_PREFIX = "[_C_S_]";

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final Class<?>[] NOT_SUPPORT = { Byte.class, Integer.class, Character.class, Long.class, Float.class,
			Double.class, Boolean.class, String.class };

	private int classNameMaxLength = 3;

	@Override
	public byte[] serialize(Object obj) throws IOException {
		if (null == obj) {
			throw new NullPointerException("Serialize object must be not null");
		}
		if (ArrayUtils.contains(NOT_SUPPORT, obj.getClass())) {
			throw new SerializeException(
					String.format("The serialize object's type [%s] does not support", obj.getClass()));
		}
		if (obj instanceof byte[]) {
			return (byte[]) obj;
		}
		if (isCollection(obj)) {
			obj = new CollectionWrapper<>(obj);
		}
		String className = obj.getClass().getName();
		String pattern = "%s%0" + classNameMaxLength + "d%s";
		String prefix = String.format(pattern, SERIALIZION_PREFIX, className.length(), className);
		if (log.isDebugEnabled()) {
			log.debug("Generate cache prefix [{}] for [{}]", prefix, obj.toString());
		}
		return ArrayUtils.addAll(prefix.getBytes(), wrapSerialize(obj));
	}

	@Override
	public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		if (!isSerialized(bytes)) {
			throw new SerializeException();
		}
		int length = toIntFromArray(bytes, SERIALIZION_PREFIX.length(), classNameMaxLength);
		int start = SERIALIZION_PREFIX.length() + classNameMaxLength;
		byte[] classSerialization = ArrayUtils.subarray(bytes, start + length, bytes.length);
		Class<?> clazz = Class.forName(new String(ArrayUtils.subarray(bytes, start, start + length)));
		Object cache = deserialize(classSerialization, clazz);
		if (cache instanceof CollectionWrapper<?>) {
			return ((CollectionWrapper<?>) cache).collection;
		}
		return cache;
	}

	public abstract byte[] wrapSerialize(Object obj) throws IOException;

	public abstract Object deserialize(byte[] bytes, Class<?> clazz) throws IOException, ClassNotFoundException;

	public boolean isSerialized(byte[] bytes) {
		if (bytes.length < SERIALIZION_PREFIX.length()) {
			return false;
		}
		return SERIALIZION_PREFIX.equals(toStringFromArray(bytes, 0, SERIALIZION_PREFIX.length()));
	}

	private boolean isCollection(Object cache) {
		return cache instanceof Map || cache instanceof Collection<?>;
	}

	private int toIntFromArray(byte[] bytes, int start, int length) {
		return Integer.parseInt(toStringFromArray(bytes, start, length));
	}

	private String toStringFromArray(byte[] bytes, int start, int length) {
		return new String(ArrayUtils.subarray(bytes, start, start + length));
	}
}
