package de.cgrothaus.hessianplayground;

import java.io.*;

import com.caucho.hessian.io.*;

import de.cgrothaus.hessianplayground.data.DataClass;

public class HessianSerializer implements Serializer {

	public byte[] serialize(DataClass data) {
		byte[] result;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		AbstractHessianOutput hessianOutput = new HessianSerializerOutput(bos);

		try {
			hessianOutput.writeObject(data);
			hessianOutput.close();
			result = bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public DataClass deserialize(byte[] databuf) {
		DataClass result;
		ByteArrayInputStream bis = new ByteArrayInputStream(databuf);
		HessianSerializerInput hessianInput;
		try {
			hessianInput = new HessianSerializerInput(bis);
			result = (DataClass) hessianInput.readObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

}
