# loginitializer
This is the small library importing Java logging APIs (SLF4j, Apache commons-logging and Log4J).

# Motivation

Groovy has a logging field injection with some simple annotation, as below.

```groovy
@Grab(group='org.slf4j', module='slf4j-api', version='1.7.21')
import org.slf4j.*
import groovy.util.logging.Slf4j

@Slf4j
class LogstuffOnGroovy {
    def foo() {
      log.info "Hello, foo."
    }
}
```

But Kotlin, using logging APIs bother to you (a little).

```kotlin
import org.slf4j.LoggerFactory

class LogstuffOnKotlin {
    companion object {
        val logger = LoggerFactory.getLogger(LogstuffOnKotlin::class.java)
    }

    fun foo() {
        logger.info("Hello, foo.")
    }
}
```

# How to use

replace `companion object` block as below.

```kotlin
class LogstuffOnKotlin {

    val logger = forSlf4j()

    fun foo() {
        logger.info("Hello, foo.")
    }
}
```

# Install

To add this product to your project, the first thing to is to add in the API.

maven:

```xml
<dependency>
  <groupId>org.yggd</groupId>
  <artifactId>kotlin-loginitializer</artifactId>
  <version>1.0</version>
</dependency>

<!-- logging APIs you want.-->
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-api</artifactId>
  <version>${slf4j.version}</version>
  <scope>runtime</scope>
</dependency>
```

gradle:

```gradle
repositories {
  mavenCentral()
}

dependencies {
  compile group: 'org.yggd', name: 'kotlin-loginitializer', version: '1.0'
  runtime group: 'commons-logging', name: 'commons-logging', version: '1.2' // logging APIs you want.
}
```
