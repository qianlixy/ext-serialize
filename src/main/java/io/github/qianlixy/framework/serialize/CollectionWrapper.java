package io.github.qianlixy.framework.serialize;

import java.io.Serializable;

public class CollectionWrapper<T> implements Serializable {

	private static final long serialVersionUID = 226541795217260245L;

	public T collection;

	public CollectionWrapper() {
	}

	public CollectionWrapper(T collection) {
		super();
		this.collection = collection;
	}

}
