package de.cgrothaus.hessianplayground;

import org.junit.Before;

public abstract class AbstractSerializerTest
{

	protected Serializer serializer;

	public AbstractSerializerTest()
	{
		super();
	}

	protected abstract Serializer initSerializer();

	@Before
	public void setUp()
	{
		this.serializer = initSerializer();
	}

}