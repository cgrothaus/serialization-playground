package de.cgrothaus.hessianplayground.data;

import java.util.UUID;

public class MediumDataClass extends DataClass {

	private static final long serialVersionUID = 1L;

	private final String string2;
	private final String string3;
	private final String string4;
	private final String string5;
	private final int int1;
	private final int int2;
	private final int int3;
	private final long long1;
	private final long long2;
	private final long long3;

	public MediumDataClass(String string1, String string2, String string3,
			String string4, String string5, int int1, int int2, int int3,
			long long1, long long2, long long3) {
		super(string1);
		this.string2 = string2;
		this.string3 = string3;
		this.string4 = string4;
		this.string5 = string5;
		this.int1 = int1;
		this.int2 = int2;
		this.int3 = int3;
		this.long1 = long1;
		this.long2 = long2;
		this.long3 = long3;
	}

	public static MediumDataClass randomInstance() {
		return new MediumDataClass(UUID.randomUUID().toString(), UUID
				.randomUUID().toString(), UUID.randomUUID().toString(), UUID
				.randomUUID().toString(), UUID.randomUUID().toString(),
				random.nextInt(), random.nextInt(), random.nextInt(),
				random.nextLong(), random.nextLong(), random.nextLong());
	}

	public String getString2() {
		return string2;
	}

	public String getString3() {
		return string3;
	}

	public String getString4() {
		return string4;
	}

	public String getString5() {
		return string5;
	}

	public int getInt1() {
		return int1;
	}

	public int getInt2() {
		return int2;
	}

	public int getInt3() {
		return int3;
	}

	public long getLong1() {
		return long1;
	}

	public long getLong2() {
		return long2;
	}

	public long getLong3() {
		return long3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + int1;
		result = prime * result + int2;
		result = prime * result + int3;
		result = prime * result + (int) (long1 ^ (long1 >>> 32));
		result = prime * result + (int) (long2 ^ (long2 >>> 32));
		result = prime * result + (int) (long3 ^ (long3 >>> 32));
		result = prime * result + ((string2 == null) ? 0 : string2.hashCode());
		result = prime * result + ((string3 == null) ? 0 : string3.hashCode());
		result = prime * result + ((string4 == null) ? 0 : string4.hashCode());
		result = prime * result + ((string5 == null) ? 0 : string5.hashCode());
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
		MediumDataClass other = (MediumDataClass) obj;
		if (int1 != other.int1)
			return false;
		if (int2 != other.int2)
			return false;
		if (int3 != other.int3)
			return false;
		if (long1 != other.long1)
			return false;
		if (long2 != other.long2)
			return false;
		if (long3 != other.long3)
			return false;
		if (string2 == null) {
			if (other.string2 != null)
				return false;
		} else if (!string2.equals(other.string2))
			return false;
		if (string3 == null) {
			if (other.string3 != null)
				return false;
		} else if (!string3.equals(other.string3))
			return false;
		if (string4 == null) {
			if (other.string4 != null)
				return false;
		} else if (!string4.equals(other.string4))
			return false;
		if (string5 == null) {
			if (other.string5 != null)
				return false;
		} else if (!string5.equals(other.string5))
			return false;
		return true;
	}

}
