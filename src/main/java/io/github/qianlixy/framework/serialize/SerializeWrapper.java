package io.github.qianlixy.framework.serialize;

import java.io.IOException;

import io.github.qianlixy.framework.serialize.wrap.PrefixClassBean;

public interface SerializeWrapper {

	byte[] wrap(Object serializable) throws IOException;
	
	PrefixClassBean unwrap(byte[] source) throws IOException, ClassNotFoundException;
	
	boolean isWrap(byte[] source);
}
