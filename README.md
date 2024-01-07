[![License](https://img.shields.io/github/license/toolarium/toolarium-ansi)](https://github.com/toolarium/toolarium-ansi/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.toolarium/toolarium-ansi/0.8.0)](https://search.maven.org/artifact/com.github.toolarium/toolarium-ansi/0.8.9/jar)
[![javadoc](https://javadoc.io/badge2/com.github.toolarium/toolarium-ansi/javadoc.svg)](https://javadoc.io/doc/com.github.toolarium/toolarium-ansi)

# toolarium-ansi

Simple library to detetc ansi support. It contains a AnsiStringBuilder which can be simply used for ansi coloring.


## Built With

* [cb](https://github.com/toolarium/common-build) - The toolarium common build

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/toolarium/toolarium-security/tags). 


### Gradle:

```groovy
dependencies {
    implementation "com.github.toolarium:toolarium-ansi:0.8.0"
}
```

### Maven:

```xml
<dependency>
    <groupId>com.github.toolarium</groupId>
    <artifactId>toolarium-ansi</artifactId>
    <version>0.8.0</version>
</dependency>
```


### Samples:
#### Create ansi color encoded strings:
```java
String myAnsiString = AnsiString.getInstance(AnsiColor.AUTO).appendWithColor(text, ForegroundColor.CYAN).toString();
...
String myAnsiString = AnsiStringBuilder builder = new AnsiStringBuilder()
                                                                .setAnsiColor(AnsiColor.AUTO) // is optional
                                                                .color(ForegroundColor.CYAN)
                                                                .append("my text")
                                                                .reset()
                                                                .toString();
```


