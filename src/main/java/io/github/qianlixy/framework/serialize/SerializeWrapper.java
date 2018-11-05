package io.github.qianlixy.framework.serialize;

import java.io.IOException;

public interface SerializeWrapper {

	byte[] wrap(ExtensiveSerializer serializer, Object obj) throws IOException;
	
	Object unwrap(ExtensiveSerializer serializer, byte[] bytes) throws ClassNotFoundException, IOException;
}
