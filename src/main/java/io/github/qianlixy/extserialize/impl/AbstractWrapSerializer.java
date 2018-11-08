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

public abstract class AbstractWrapSerializer implements ExtensiveSerializer {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private SerializeWrapper serializeWrapper;

	public AbstractWrapSerializer() {
		this.serializeWrapper = new PrefixClassSerializeWrapper();
	}

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

	protected Object doWrap(Object obj) {
		return obj;
	}

	protected abstract byte[] doSerialize(Object obj) throws IOException;

	protected abstract Object deserialize(byte[] bytes, Class<?> clazz) throws IOException, ClassNotFoundException;

	public void setSerializeWrapper(SerializeWrapper serializeWrapper) {
		this.serializeWrapper = serializeWrapper;
	}

}
