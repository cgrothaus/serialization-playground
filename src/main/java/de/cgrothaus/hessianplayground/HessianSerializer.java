package de.cgrothaus.hessianplayground;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;

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
