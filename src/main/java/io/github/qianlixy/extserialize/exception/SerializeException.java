package io.github.qianlixy.extserialize.exception;

/**
 * 序列化异常
 * 
 * @author Qianli
 * @since 1.0.0
 */
public class SerializeException extends RuntimeException {

	private static final long serialVersionUID = 8317230092434275112L;

	/**
	 * 构造函数
	 */
	public SerializeException() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 *            异常信息
	 * @param cause
	 *            内嵌异常
	 */
	public SerializeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param message
	 *            异常信息
	 */
	public SerializeException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param cause
	 *            内嵌异常
	 */
	public SerializeException(Throwable cause) {
		super(cause);
	}

}