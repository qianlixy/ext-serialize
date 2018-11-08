package io.github.qianlixy.extserialize.impl;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.qianlixy.extserialize.ExtensiveSerializer;
import io.github.qianlixy.extserialize.SerializeWrapper;
import io.github.qianlixy.extserialize.exception.SerializeException;
import io.github.qianlixy.extserialize.wrap.PrefixClassSerializeWrapper;
import io.github.qianlixy.extserialize.wrap.WrapBean;

/**
 * 包装序列化抽象类，需要包装序列化时继承该类
 * 
 * @author Qianli
 * @since 1.0.0
 */
public abstract class AbstractWrapSerializer implements ExtensiveSerializer {

	/**
	 * 日志打印
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 序列化包装类.默认是{@link PrefixClassSerializeWrapper}
	 */
	private SerializeWrapper serializeWrapper;

	/**
	 * 构造函数
	 */
	public AbstractWrapSerializer() {
		this.serializeWrapper = new PrefixClassSerializeWrapper();
	}

	/**
	 * 构造函数
	 * 
	 * @param serializeWrapper
	 *            序列化包装类
	 */
	public AbstractWrapSerializer(SerializeWrapper serializeWrapper) {
		this.serializeWrapper = serializeWrapper;
	}

	@Override
	public byte[] serialize(Object obj) throws IOException {
		if (null == obj) {
			throw new NullPointerException("Serialize object must be not null");
		}
		Object wrap = doWrap(obj);
		return ArrayUtils.addAll(serializeWrapper.wrap(wrap), doSerialize(wrap));
	}

	@Override
	public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		if (!serializeWrapper.isWrap(bytes)) {
			throw new SerializeException();
		}
		WrapBean classBean = serializeWrapper.unwrap(bytes);
		if (null == classBean.getClazz()) {
			log.warn("Deserialize Class is null, return null");
			return null;
		}
		return deserialize(classBean.getSource(), classBean.getClazz());
	}

	/**
	 * 包装对象。由实现类决定是否包装对象。默认不包装
	 * 
	 * @param obj
	 *            对象
	 * @return 包装对象
	 */
	protected Object doWrap(Object obj) {
		return obj;
	}

	/**
	 * 序列化对象，不同的序列化实现。
	 * 
	 * @param obj
	 *            对象
	 * @return 对象字节数组
	 * @throws IOException
	 *             序列化时可能抛出该异常
	 */
	protected abstract byte[] doSerialize(Object obj) throws IOException;

	/**
	 * 反序列化对象
	 * 
	 * @param bytes
	 *            对象字节数组
	 * @param clazz
	 *            对象Class
	 * @return 对象
	 * @throws IOException
	 *             序列化时可能抛出该异常
	 * @throws ClassNotFoundException
	 *             找不到对象Class时抛出该异常
	 */
	protected abstract Object deserialize(byte[] bytes, Class<?> clazz) throws IOException, ClassNotFoundException;

	/**
	 * 设置序列化包装类
	 * 
	 * @param serializeWrapper
	 *            序列化包装类
	 */
	public void setSerializeWrapper(SerializeWrapper serializeWrapper) {
		this.serializeWrapper = serializeWrapper;
	}

}