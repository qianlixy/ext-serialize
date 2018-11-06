package io.github.qianlixy.framework.serialize;

import java.io.IOException;

import io.github.qianlixy.framework.serialize.wrap.WrapBean;

public interface SerializeWrapper {
	
	byte SERIALIZION_PREFIX = 125;

	byte[] wrap(Object serializable) throws IOException;
	
	WrapBean unwrap(byte[] source) throws IOException, ClassNotFoundException;
	
	default boolean isWrap(byte[] source) {
		return SERIALIZION_PREFIX == source[0];
	};
}
