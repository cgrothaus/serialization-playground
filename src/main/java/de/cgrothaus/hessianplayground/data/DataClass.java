package de.cgrothaus.hessianplayground.data;

import java.io.Serializable;
import java.util.Random;

public abstract class DataClass implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected final static Random random = new Random();
	
	private final String string1;
	
	public DataClass(String string1) {
		super();
		this.string1 = string1;
	}
	
	public String getString1() {
		return string1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string1 == null) ? 0 : string1.hashCode());
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
		DataClass other = (DataClass) obj;
		if (string1 == null) {
			if (other.string1 != null)
				return false;
		} else if (!string1.equals(other.string1))
			return false;
		return true;
	}

}
