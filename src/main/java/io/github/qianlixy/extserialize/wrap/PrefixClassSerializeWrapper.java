package io.github.qianlixy.extserialize.wrap;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.qianlixy.extserialize.SerializeWrapper;
import io.github.qianlixy.extserialize.exception.SerializeException;

/**
 * 前缀Class信息的序列化包装实现类
 * 
 * @author Qianli
 * @since 1.0.0
 */
public class PrefixClassSerializeWrapper implements SerializeWrapper {

	/**
	 * 日志打印
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 不支持的类型
	 */
	private static final Class<?>[] NOT_SUPPORT = { Byte.class, Integer.class, Character.class, Long.class, Float.class,
			Double.class, Boolean.class, String.class };

	@Override
	public byte[] wrap(Object obj) throws IOException {
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
		String className = obj.getClass().getName();
		return ArrayUtils.insert(0, className.getBytes(), SERIALIZION_PREFIX, (byte) className.length());
	}

	@Override
	public WrapBean unwrap(byte[] bytes) throws IOException, ClassNotFoundException {
		int length = bytes[1], start = 2;
		byte[] classSerialization = ArrayUtils.subarray(bytes, start + length, bytes.length);
		Class<?> clazz = Class.forName(new String(ArrayUtils.subarray(bytes, start, start + length)));
		return new WrapBean(clazz, classSerialization);
	}

}