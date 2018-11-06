package io.github.qianlixy.framework.serialize;

import java.io.IOException;

public interface ExtensiveSerializer {

	byte[] serialize(Object obj) throws IOException;

	Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException;

}
