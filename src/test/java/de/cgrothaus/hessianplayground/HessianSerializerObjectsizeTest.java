package de.cgrothaus.hessianplayground;

public class HessianSerializerObjectsizeTest extends AbstractSerializerObjectsizeTest {

	@Override
	protected Serializer initSerializer() {
		return new HessianSerializer();
	}

}
