package de.cgrothaus.hessianplayground.data;

import java.io.*;
import java.util.*;

public class LargeDataClass extends MediumDataClass
{

	private static final long serialVersionUID = 1L;
	
	private final List<LargeDataClass> listChildren = new ArrayList<LargeDataClass>();
	private final Map<String, LargeDataClass> mappedChildren = new HashMap<String, LargeDataClass>();

	/**
	 * Empty default constructor
	 */
	public LargeDataClass() {}
	
	public LargeDataClass(String string1, String string2, String string3, String string4, String string5, int int1, int int2, int int3, long long1, long long2,
			long long3, List<LargeDataClass> listChildren, Map<String, LargeDataClass> mappedChildren)
	{
		super(string1, string2, string3, string4, string5, int1, int2, int3, long1, long2, long3);
		this.listChildren.addAll(listChildren);
		this.mappedChildren.putAll(mappedChildren);
	}

	public LargeDataClass(String string1, String string2, String string3, String string4, String string5, int int1, int int2, int int3, long long1, long long2,
			long long3)
	{
		this(string1, string2, string3, string4, string5, int1, int2, int3, long1, long2, long3, Collections.<LargeDataClass> emptyList(), 
				Collections.<String, LargeDataClass> emptyMap());
	}
	
	
	/**
	 * Copy-Constructor;
	 * 
	 * @param toCopy
	 *            the instance to copy
	 */
	public LargeDataClass(LargeDataClass toCopy)
	{
		this(toCopy.getString1(), toCopy.getString2(), toCopy.getString3(), toCopy.getString4(), toCopy.getString5(), toCopy.getInt1(), toCopy.getInt2(),
				toCopy.getInt3(), toCopy.getLong1(), toCopy.getLong2(), toCopy.getLong3(), toCopy.listChildren, toCopy.mappedChildren);
	}
	
	public static LargeDataClass randomSingleInstance() {
		return new LargeDataClass(UUID.randomUUID().toString(), UUID
				.randomUUID().toString(), UUID.randomUUID().toString(), UUID
				.randomUUID().toString(), UUID.randomUUID().toString(),
				random.nextInt(), random.nextInt(), random.nextInt(),
				random.nextLong(), random.nextLong(), random.nextLong());
	}

	/**
	 * @return a sixpack of LargeDataClass
	 */
	public static LargeDataClass randomMultiInstance() {
		LargeDataClass ldc1 = randomSingleInstance();		
		ldc1.getListChildren().add(randomSingleInstance());
		ldc1.getListChildren().add(randomSingleInstance());
		ldc1.getMappedChildren().put(UUID.randomUUID().toString(), randomSingleInstance());
		
		LargeDataClass ldc2 = randomSingleInstance();
		ldc2.getMappedChildren().put(UUID.randomUUID().toString(), randomSingleInstance());
		
		ldc1.getMappedChildren().put(UUID.randomUUID().toString(), ldc2);
		
		return ldc1;
	}
	
	public List<LargeDataClass> getListChildren()
	{
		return listChildren;
	}
	
	public Map<String, LargeDataClass> getMappedChildren()
	{
		return mappedChildren;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((listChildren == null) ? 0 : listChildren.hashCode());
		result = prime * result + ((mappedChildren == null) ? 0 : mappedChildren.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LargeDataClass other = (LargeDataClass) obj;
		if (listChildren == null)
		{
			if (other.listChildren != null)
				return false;
		}
		else if (!listChildren.equals(other.listChildren))
			return false;
		if (mappedChildren == null)
		{
			if (other.mappedChildren != null)
				return false;
		}
		else if (!mappedChildren.equals(other.mappedChildren))
			return false;
		return true;
	}
	
	/*
	 * ##### Hazelcast DataSerializable #####################################
	 */

	public void writeData(DataOutput out) throws IOException {
		super.writeData(out);
		out.writeInt(listChildren.size());
		for (LargeDataClass listChild : listChildren) {
			listChild.writeData(out);
		}
		out.writeInt(mappedChildren.size());
		for (Map.Entry<String, LargeDataClass> entry : mappedChildren.entrySet()) {
			out.writeUTF(entry.getKey());
			entry.getValue().writeData(out);
		}
	}

	public void readData(DataInput in) throws IOException {
		super.readData(in);
		int listChildrenSize = in.readInt();
		for (int i = 0; i < listChildrenSize; i++) {
			LargeDataClass item = new LargeDataClass();
			item.readData(in);
			listChildren.add(item);
		}
		int mappedChildrenSize = in.readInt();
		for (int i = 0; i < mappedChildrenSize; i++) {
			String key = in.readUTF();
			LargeDataClass value = new LargeDataClass();
			value.readData(in);
			mappedChildren.put(key, value);
		}
	}

}
