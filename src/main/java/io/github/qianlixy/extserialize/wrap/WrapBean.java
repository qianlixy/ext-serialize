package io.github.qianlixy.extserialize.wrap;

/**
 * 包装信息类
 * 
 * @author Qianli
 * @since 1.0.0
 */
public class WrapBean {

	/**
	 * Class对象
	 */
	private Class<?> clazz;

	/**
	 * 源字节数组
	 */
	private byte[] source;

	/**
	 * 空构造函数
	 */
	public WrapBean() {
	}

	/**
	 * 构造函数
	 * 
	 * @param clazz
	 *            Class对象
	 * @param source
	 *            源字节数组
	 */
	public WrapBean(Class<?> clazz, byte[] source) {
		super();
		this.clazz = clazz;
		this.source = source;
	}

	/**
	 * 获取Class对象
	 * 
	 * @return Class对象
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * 设置Class对象
	 * 
	 * @param clazz
	 *            Class对象
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 获取源字节数组
	 * 
	 * @return 源字节数组
	 */
	public byte[] getSource() {
		return source;
	}

	/**
	 * 设置源字节数组
	 * 
	 * @param source
	 *            源字节数组
	 */
	public void setSource(byte[] source) {
		this.source = source;
	}
}