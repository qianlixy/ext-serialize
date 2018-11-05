package io.github.qianlixy.framework.serialize.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class TestBean implements Serializable {

	private static final long serialVersionUID = 3458239028793164159L;

	public TestBean() {
	}

	public TestBean(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Setter
	@Getter
	private String name;

	@Setter
	@Getter
	private int age;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestBean other = (TestBean) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
