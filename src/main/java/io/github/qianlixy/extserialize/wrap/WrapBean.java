package io.github.qianlixy.extserialize.wrap;

public class WrapBean {

	private Class<?> clazz;

	private byte[] source;

	public WrapBean() {

	}

	public WrapBean(Class<?> clazz, byte[] source) {
		super();
		this.clazz = clazz;
		this.source = source;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public byte[] getSource() {
		return source;
	}

	public void setSource(byte[] source) {
		this.source = source;
	}
}
