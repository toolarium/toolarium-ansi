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
#### Create an ansi colored encoded string:
```java
String myAnsiString = AnsiString.getInstance(AnsiColor.AUTO).appendWithColor(text, ForegroundColor.CYAN).toString();
```

#### Create an ansi colored encoded string by the builder:
```java
String myAnsiString = AnsiStringBuilder builder = 
    new AnsiStringBuilder()
        .setAnsiColor(AnsiColor.AUTO) // is optional
        .color(ForegroundColor.CYAN)
        .append("my text")
        .reset()
        .toString();
```

#### Sample how to use the AnsiStringBuilder:
```java
// All formatting functions support at least three different overloads, each intended for a different use case.
AnsiStringBuilder a = new AnsiStringBuilder()
    .italic() // Use case 1: Manual Reset
    .append("This is italicized and reset manually.")
    .resetItalic(System.lineSeparator()) // You can optionaly supply an additional append string to any of the reset functions that will be appended after the formating reset has been applied.
                
    .dim("This is dimmed and reset automatically.") // Use case 2: Automatic Reset
    .append(System.lineSeparator())
        
    .underline(sb -> { // Use case 3: Function Consumer 
       sb.color24("#00ff00", "This is both underlined and green");
    }) // The string builder passed to this function consumer will automatically wrap all content appended to it with the underline formatting.
    .append(System.lineSeparator())
                
    .color(ForegroundColor.CYAN).append("This is in cyan color")
               
    .reset();
    
LOG.debug("=>" + a.toString());
```
