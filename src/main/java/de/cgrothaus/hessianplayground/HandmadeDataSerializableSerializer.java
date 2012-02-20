package de.cgrothaus.hessianplayground;

import java.io.*;

import de.cgrothaus.hessianplayground.data.*;

public class HandmadeDataSerializableSerializer implements Serializer {

	public byte[] serialize(DataClass data) {
		byte[] result;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(bos);
		try {
			out.writeUTF(data.getClass().getName());
			data.writeData(out);
			result = bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public DataClass deserialize(byte[] databuf) {
		DataClass result;
		ByteArrayInputStream bis = new ByteArrayInputStream(databuf);
		DataInputStream in = new DataInputStream(bis);
		try {
			String fcqn = in.readUTF();
			if (SmallDataClass.class.getName().equals(fcqn)) {
				result = new SmallDataClass();
			} else if (MediumDataClass.class.getName().equals(fcqn)) {
				result = new MediumDataClass();
			} else if (LargeDataClass.class.getName().equals(fcqn)) {
				result = new LargeDataClass();
			} else {
				throw new RuntimeException("Unknown DataClass descendant: " + fcqn);
			}
			result.readData(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

}
