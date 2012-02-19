package de.cgrothaus.hessianplayground;

public class JavaSerializerPerformanceTest extends AbstractSerializerPerformanceTest
{

	@Override
	protected Serializer initSerializer()
	{
		return new JavaSerializer();
	}

}
