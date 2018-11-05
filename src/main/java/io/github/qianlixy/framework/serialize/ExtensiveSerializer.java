package io.github.qianlixy.framework.serialize;

import java.io.IOException;

public interface ExtensiveSerializer {

	String CACHE_SERIALIZION_PREFIX = "[_C_S_]";

	byte[] serialize(Object obj) throws IOException;

	byte[] serialize(Object obj, Class<?> clazz) throws IOException;

	Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException;

	Object deserialize(byte[] bytes, Class<?> clazz) throws IOException, ClassNotFoundException;

}
