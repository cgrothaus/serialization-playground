package de.cgrothaus.hessianplayground;

import de.cgrothaus.hessianplayground.data.DataClass;

public interface Serializer {

	public abstract byte[] serialize(DataClass data);

	public abstract DataClass deserialize(byte[] databuf);

}