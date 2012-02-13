package de.cgrothaus.hessianplayground;

public class HessianSerializerTest extends AbstractSerializerTest {

	@Override
	protected Serializer initSerializer() {
		return new HessianSerializer();
	}

}
