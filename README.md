# log4j2-json-helper

[![Maven Central](https://img.shields.io/maven-central/v/io.symphonia/lambda-packaging.svg)](https://search.maven.org/artifact/io.symphonia/log4j2-json-helper)

A very tiny library (just one class!) to make using Log4J2's JsonLayout a little less painful via a thin wrapper around `Logger` and a varargs method for providing JSON keys and values to the underlying logger.

### Prerequisites

Your application needs to be using Log4J2's [JsonLayout](https://logging.apache.org/log4j/2.x/manual/layouts.html#JSONLayout).

My `log4j2.xml` file looks like this:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
    <Appenders>
         <Console name="Console" target="SYSTEM_OUT">
            <JsonLayout compact="true" eventEol="true" objectMessageAsJsonObject="true"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

*Get a basic Log4J2 and JsonLayout configuration working before adding this library!*

### Usage

Include the dependency in your `pom.xml` file or equivalent:

```xml
<dependency>
    <groupId>io.symphonia</groupId>
    <artifactId>log4j2-json-helper</artifactId>
    <version>1.0.0</version>
</dependency>

```

Use the `JsonLogHelper` in place of a `Logger` in your Java code.

```java
package my.app;

import io.symphonia.log4j2.JsonLogHelper;
import org.apache.logging.log4j.LogManager;

public class MyClass {
    private static JsonLogHelper LOG = new JsonLogHelper(MyClass.class);
    
    public void doStuff() {
        LOG.info("userId", "ABC", "action", "placeOrder");
    }
}
```

The log output will look something like this:

```json
{
    "thread": "main",
    "level": "INFO",
    "loggerName": "my.app",
    "message": {
        "userId": "ABC",
        "action": "placeOrder"
    },
    "endOfBatch": false,
    "loggerFqcn": "org.apache.logging.log4j.spi.AbstractLogger",
    "instant": {
        "epochSecond": 1565022901,
        "nanoOfSecond": 856000000
    },
    "threadId": 1,
    "threadPriority": 5
}
```
