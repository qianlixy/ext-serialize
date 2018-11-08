package io.github.qianlixy.extserialize;

import java.io.IOException;

import io.github.qianlixy.extserialize.wrap.WrapBean;

/**
 * 序列化包装接口
 * 
 * @author Qianli
 * @since 1.0.0
 */
public interface SerializeWrapper {

	/**
	 * 序列化包装标识前缀
	 */
	byte SERIALIZION_PREFIX = 125;

	/**
	 * 包装对象，返回前缀字节数组
	 * 
	 * @param serializable
	 *            对象
	 * @return 前缀字节数组
	 * @throws IOException
	 *             包装时可能抛出该异常
	 */
	byte[] wrap(Object serializable) throws IOException;

	/**
	 * 解包装对象，返回包装信息
	 * 
	 * @param wrapBytes
	 *            包装字节数组
	 * @return 包装信息
	 * @throws IOException
	 *             解包装时可能抛出该异常
	 * @throws ClassNotFoundException
	 *             解包装时找不到对象Class抛出异常
	 */
	WrapBean unwrap(byte[] wrapBytes) throws IOException, ClassNotFoundException;

	/**
	 * 是否是包装字节数组
	 * 
	 * @param wrapBytes
	 *            包装字节数组
	 * @return true - 已包装，false - 未包装
	 */
	default boolean isWrap(byte[] wrapBytes) {
		if (null == wrapBytes || wrapBytes.length <= 1) {
			return false;
		}
		return SERIALIZION_PREFIX == wrapBytes[0];
	}
}