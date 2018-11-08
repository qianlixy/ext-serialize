package io.github.qianlixy.extserialize;

import java.io.IOException;

/**
 * 可扩展序列化接口
 * 
 * @author Qianli
 * @since 1.0.0
 */
public interface ExtensiveSerializer {

	/**
	 * 序列化对象
	 * 
	 * @param obj
	 *            对象
	 * @return 序列化结果
	 * @throws IOException
	 *             序列化时可能抛出该异常
	 */
	byte[] serialize(Object obj) throws IOException;

	/**
	 * 反序列化对象
	 * 
	 * @param bytes
	 *            对象的字节数组
	 * @return 对象
	 * @throws IOException
	 *             序列化时可能抛出该异常
	 * @throws ClassNotFoundException
	 *             找不到对象Class时抛出该异常
	 */
	Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException;

}