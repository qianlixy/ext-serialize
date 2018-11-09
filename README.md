[![Maven Central](https://img.shields.io/maven-central/v/io.github.qianlixy/ext-serialize.svg)](https://search.maven.org/search?q=a:ext-serialize)
[![GitHub license](https://img.shields.io/github/license/qianlixy/ext-serialize.svg)](https://github.com/qianlixy/ext-serialize/blob/master/LICENSE)

# ext-serialize
Extensive serialize

## Useage
```
// JDK serialize
ExtensiveSerializer serializer = new JdkSerializer();
byte[] serialize = getSerializer().serialize(testBean);
Object deserialize = getSerializer().deserialize(serialize);
assertEquals(testBean, deserialize);

// Kryo serialize
ExtensiveSerializer serializer = new KryoSerializer();
byte[] serialize = getSerializer().serialize(testBean);
Object deserialize = getSerializer().deserialize(serialize);
assertEquals(testBean, deserialize);

// Protostuff serialize
ExtensiveSerializer serializer = new ProtostuffSerializer();
byte[] serialize = getSerializer().serialize(testBean);
Object deserialize = getSerializer().deserialize(serialize);
assertEquals(testBean, deserialize);
```
