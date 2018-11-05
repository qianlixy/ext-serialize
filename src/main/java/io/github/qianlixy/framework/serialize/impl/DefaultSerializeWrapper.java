package io.github.qianlixy.framework.serialize.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.qianlixy.framework.serialize.CollectionWrapper;
import io.github.qianlixy.framework.serialize.ExtensiveSerializer;
import io.github.qianlixy.framework.serialize.SerializeWrapper;
import io.github.qianlixy.framework.serialize.exception.SerializeException;

public class DefaultSerializeWrapper implements SerializeWrapper {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final String CACHE_SERIALIZION_PREFIX = "[_C_S_]";

	private int classNameMaxLength = 3;

	public DefaultSerializeWrapper() {
	}

	public DefaultSerializeWrapper(int classNameMaxLength) {
		this.classNameMaxLength = classNameMaxLength;
	}

	@Override
	public byte[] wrap(ExtensiveSerializer serializer, Object obj) throws IOException {
		if (obj instanceof byte[]) {
			return (byte[]) obj;
		}
		if (isCollection(obj)) {
			obj = new CollectionWrapper<>(obj);
		}
		String className = obj.getClass().getName();
		String pattern = "%s%0" + classNameMaxLength + "d%s";
		String prefix = String.format(pattern, CACHE_SERIALIZION_PREFIX, className.length(), className);
		if (log.isDebugEnabled()) {
			log.debug("Generate cache prefix [{}] for [{}]", prefix, obj.toString());
		}
		return ArrayUtils.addAll(prefix.getBytes(), serializer.serialize(obj, obj.getClass()));
	}

	@Override
	public Object unwrap(ExtensiveSerializer serializer, byte[] bytes) throws ClassNotFoundException, IOException {
		if (!isSerialized(bytes)) {
			throw new SerializeException();
		}
		int length = toIntFromArray(bytes, CACHE_SERIALIZION_PREFIX.length(), classNameMaxLength);
		int start = CACHE_SERIALIZION_PREFIX.length() + classNameMaxLength;
		byte[] classSerialization = ArrayUtils.subarray(bytes, start + length, bytes.length);
		Class<?> clazz = Class.forName(new String(ArrayUtils.subarray(bytes, start, start + length)));
		Object cache = serializer.deserialize(classSerialization, clazz);
		if (cache instanceof CollectionWrapper<?>) {
			return ((CollectionWrapper<?>) cache).collection;
		}
		return cache;
	}

	private boolean isCollection(Object cache) {
		return cache instanceof Map || cache instanceof Collection<?>;
	}

	public boolean isSerialized(byte[] bytes) {
		if (bytes.length < CACHE_SERIALIZION_PREFIX.length()) {
			return false;
		}
		return CACHE_SERIALIZION_PREFIX.equals(toStringFromArray(bytes, 0, CACHE_SERIALIZION_PREFIX.length()));
	}

	private String toStringFromArray(byte[] bytes, int start, int length) {
		return new String(ArrayUtils.subarray(bytes, start, start + length));
	}

	private int toIntFromArray(byte[] bytes, int start, int length) {
		return Integer.parseInt(toStringFromArray(bytes, start, length));
	}

}
