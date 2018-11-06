package io.github.qianlixy.framework.serialize.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import io.github.qianlixy.framework.serialize.ExtensiveSerializer;

public class KryoSerializer extends AbstractWrapSerializer implements ExtensiveSerializer {

	private static Kryo kryo;

	public KryoSerializer() {
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
	public byte[] doSerialize(Object obj) throws IOException {
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
