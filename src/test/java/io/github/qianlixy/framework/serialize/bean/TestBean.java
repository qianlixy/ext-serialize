package io.github.qianlixy.framework.serialize.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class TestBean implements Serializable {

	private static final long serialVersionUID = 3458239028793164159L;

	public TestBean(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public TestBean(String name, int age, List<String> list, Set<String> set, Map<String, Object> map) {
		super();
		this.name = name;
		this.age = age;
		this.list = list;
		this.set = set;
		this.map = map;
	}

	@Setter
	@Getter
	private String name;

	@Setter
	@Getter
	private int age;

	@Setter
	@Getter
	private List<String> list;

	@Setter
	@Getter
	private Set<String> set;

	@Setter
	@Getter
	private Map<String, Object> map;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((set == null) ? 0 : set.hashCode());
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
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TestBean [name=" + name + ", age=" + age + ", list=" + list + ", set=" + set + ", map=" + map + "]";
	}

}
