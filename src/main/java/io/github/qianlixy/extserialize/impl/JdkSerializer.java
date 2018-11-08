package io.github.qianlixy.extserialize.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.github.qianlixy.extserialize.ExtensiveSerializer;

public class JdkSerializer implements ExtensiveSerializer {

	@Override
	public byte[] serialize(Object obj) throws IOException {
		try (ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(bytes)) {
			out.writeObject(obj);
			out.flush();
			return bytes.toByteArray();
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(baos)) {
			return ois.readObject();
		} catch (IOException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

}
