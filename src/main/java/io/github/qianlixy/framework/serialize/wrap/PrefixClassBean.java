package io.github.qianlixy.framework.serialize.wrap;

public class PrefixClassBean {

	private Class<?> clazz;

	private byte[] source;

	public PrefixClassBean() {

	}

	public PrefixClassBean(Class<?> clazz, byte[] source) {
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
