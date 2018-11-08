package io.github.qianlixy.extserialize.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import io.github.qianlixy.extserialize.ExtensiveSerializer;

/**
 * kryo序列化实现类
 * 
 * @author Qianli
 * @since 1.0.0
 */
public class KryoSerializer extends AbstractWrapSerializer implements ExtensiveSerializer {

	/**
	 * Buffer size
	 */
	private static int bufferSize = 1024 * 20;

	/**
	 * kryo实例。保存在ThreadLocal对象，保证并发情况下正常使用。
	 */
	private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {

		@Override
		protected Kryo initialValue() {
			Kryo kryo = new Kryo();
			kryo.setReferences(false);
			kryo.setRegistrationRequired(false);
			((Kryo.DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
					.setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
			return kryo;
		}

	};

	@Override
	public byte[] doSerialize(Object obj) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); Output output = new Output(baos, bufferSize)) {
			kryos.get().writeObject(output, obj);
			return output.toBytes();
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public Object deserialize(byte[] bytes, Class<?> clazz) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
				Input input = new Input(byteArrayInputStream)) {
			return kryos.get().readObject(input, clazz);
		} catch (IOException e) {
			throw e;
		}
	}

}