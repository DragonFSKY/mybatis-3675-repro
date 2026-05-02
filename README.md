# mybatis-3675-repro

Minimal reproduction for the MyBatis native-image mapper bean creation failure reported in
mybatis/mybatis-3#3675.

## Environment

- GraalVM 17.0.11 native-image
- Spring Boot 3.3.13
- mybatis-spring-boot-starter 3.0.5
- MyBatis 3.5.19
- H2

## Reproduce

```bash
JAVA_HOME=/path/to/graalvm17 PATH=$JAVA_HOME/bin:$PATH mvn -Pnative native:compile -DskipTests
./target/mybatis-3675-repro
```

Expected failure:

```text
Error creating bean with name 'dictionaryMapper':
Unsatisfied dependency expressed through constructor parameter 0:
No qualifying bean of type 'java.lang.Class<?>' available
```

## Notes

The native reflection metadata under `src/main/resources/META-INF/native-image/com.example/mybatis-extra`
is included only to let the native executable reach the mapper bean creation phase.

The generated Spring AOT code for `dictionaryMapper` contains:

```java
BeanInstanceSupplier.<MapperFactoryBean>forConstructor(Class.class)
    .withGenerator((registeredBean, args) -> new MapperFactoryBean(args.get(0)));
```

At native runtime, the `Class<?>` constructor argument appears to be resolved as an autowired
dependency instead of being passed as the concrete mapper interface class.
