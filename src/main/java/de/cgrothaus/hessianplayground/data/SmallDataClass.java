package de.cgrothaus.hessianplayground.data;

import java.util.UUID;

public class SmallDataClass extends DataClass {

	private static final long serialVersionUID = 1L;
	
	private final int int1;
	private final long long1;

	public SmallDataClass(String string1, int int1, long long1) {
		super(string1);
		this.int1 = int1;
		this.long1 = long1;
	}

	public static SmallDataClass randomInstance() {
		return new SmallDataClass(UUID.randomUUID().toString(),
				random.nextInt(), random.nextLong());
	}

	public int getInt1() {
		return int1;
	}

	public long getLong1() {
		return long1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + int1;
		result = prime * result + (int) (long1 ^ (long1 >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmallDataClass other = (SmallDataClass) obj;
		if (int1 != other.int1)
			return false;
		if (long1 != other.long1)
			return false;
		return true;
	}

}
