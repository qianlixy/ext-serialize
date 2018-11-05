package io.github.qianlixy.framework.serialize.exception;

public class SerializeException extends RuntimeException {

	private static final long serialVersionUID = 8317230092434275112L;

	public SerializeException() {
		super();
	}

	public SerializeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SerializeException(String message) {
		super(message);
	}

	public SerializeException(Throwable cause) {
		super(cause);
	}


}
