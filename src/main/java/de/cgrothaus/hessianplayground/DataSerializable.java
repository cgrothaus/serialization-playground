package de.cgrothaus.hessianplayground;

import java.io.*;

/**
 * Handmade serialization with java.io.DataOutput and java.io.DataInput. This interface is an exact copy of
 * com.hazelcast.nio.DataSerializable.
 */
public interface DataSerializable extends Serializable {

	void writeData(DataOutput out) throws IOException;

	void readData(DataInput in) throws IOException;

}