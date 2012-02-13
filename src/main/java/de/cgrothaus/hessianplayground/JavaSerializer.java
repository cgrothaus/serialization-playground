package de.cgrothaus.hessianplayground;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.cgrothaus.hessianplayground.data.DataClass;

public class JavaSerializer implements Serializer {

	public byte[] serialize(DataClass data) {
		byte[] result;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(data);
			oos.close();
			result = bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public DataClass deserialize(byte[] databuf) {
		DataClass result;
		ByteArrayInputStream bis = new ByteArrayInputStream(databuf);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(bis);
			result = (DataClass) ois.readObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

}
