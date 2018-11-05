package io.github.qianlixy.framework.serialize.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import io.github.qianlixy.framework.serialize.ExtensiveSerializer;
import io.github.qianlixy.framework.serialize.SerializeWrapper;

public class KryoSerializer implements ExtensiveSerializer {
	
	private SerializeWrapper wrapper;
	
	private static Kryo kryo;
	
	public KryoSerializer() {
		this.wrapper = new DefaultSerializeWrapper();
		
		if (null == kryo) {
			synchronized (KryoSerializer.class) {
				if (null == kryo) {
					kryo = new Kryo();
					kryo.setReferences(false);
					kryo.setRegistrationRequired(false);
				}
			}
		}
	}

	@Override
	public byte[] serialize(Object obj) throws IOException {
		return wrapper.wrap(this, obj);
	}

	@Override
	public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		return wrapper.unwrap(this, bytes);
	}

	@Override
	public byte[] serialize(Object obj, Class<?> clazz) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); Output output = new Output(baos)) {
			kryo.writeObject(output, obj);
			return output.toBytes();
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public Object deserialize(byte[] bytes, Class<?> clazz) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
				Input input = new Input(byteArrayInputStream)) {
			return kryo.readObject(input, clazz);
		} catch (IOException e) {
			throw e;
		}
	}

}
