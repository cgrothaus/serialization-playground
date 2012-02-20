package de.cgrothaus.hessianplayground.data;

import java.io.*;
import java.util.Random;

import de.cgrothaus.hessianplayground.DataSerializable;

public abstract class DataClass implements DataSerializable {
	
	private static final long serialVersionUID = 1L;

	protected final static Random random = new Random();
	
	private String string1;
	
	/**
	 * Empty default constructor
	 */
	public DataClass() {}
	
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

	/*
	 * ##### Hazelcast DataSerializable #####################################
	 */

	public void writeData(DataOutput out) throws IOException {
		out.writeUTF(string1);
	}

	public void readData(DataInput in) throws IOException {
		string1 = in.readUTF();
	}
	
}
