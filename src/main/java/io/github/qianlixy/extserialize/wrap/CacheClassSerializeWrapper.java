package io.github.qianlixy.extserialize.wrap;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.qianlixy.extserialize.SerializeWrapper;
import io.github.qianlixy.extserialize.exception.SerializeException;

/**
 * 缓存Class信息的序列化包装实现类
 * 
 * @author Qianli
 * @since 1.0.0
 */
public class CacheClassSerializeWrapper implements SerializeWrapper {

	/**
	 * 日志打印
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 缓存的Class信息
	 */
	private static final ConcurrentMap<String, Class<?>> cacheClasses = new ConcurrentHashMap<>();

	@Override
	public byte[] wrap(Object serializable) throws IOException {
		byte[] md5 = md5(serializable.getClass().getName());
		if (log.isDebugEnabled()) {
			log.info("Genarate md5 [{}] for Class<{}>", md5, serializable.getClass().getName());
		}
		cacheClasses.put(new BigInteger(1, md5).toString(16), serializable.getClass());
		return ArrayUtils.insert(0, md5, SERIALIZION_PREFIX);
	}

	@Override
	public WrapBean unwrap(byte[] source) throws IOException, ClassNotFoundException {
		String md5 = new BigInteger(1, ArrayUtils.subarray(source, 1, 17)).toString(16);
		return new WrapBean(cacheClasses.get(md5), ArrayUtils.subarray(source, 17, source.length));
	}

	/**
	 * 获取MD5值
	 * 
	 * @param className
	 *            类名称
	 * @return MD5值
	 */
	private byte[] md5(String className) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(className.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new SerializeException("Cannot get class's MD5", e);
		}
	}

}