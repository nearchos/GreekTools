[![](https://jitpack.io/v/nearchos/GreekTools.svg)](https://jitpack.io/#nearchos/GreekTools)

# GreekTools

A Java library for manipulating Greek text - mainly transforming between Latin and Greek character sets.

Based on the [ISO 843](https://en.wikipedia.org/wiki/ISO_843) standard (which is identical to [ELOT 743](http://www.geonoma.gov.cy/myfiles/logismika/elot/pinakas-metagrafis-ellnikikou-alfavitou-sm.jpg)).

It can be used to transform Greek text to the equivalent using the Latin alphabet (aka Greeklish).
The other way also works, assuming the source text is consistent with the above standards.

Navigate the JavaDoc comments in the Greeklish class for the available methods.

[**Greeklish.java**](https://github.com/nearchos/GreekTools/blob/master/src/main/java/com/aspectsense/greektools/Greeklish.java)

### Download

If needed, add jitpack at your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Also, update your module Gradle to add the dependency:

```gradle
dependencies {
  implementation 'com.github.nearchos:GreekTools:0.1.2'
}
```

### Versions
- 0.1.1 - first public release
- 0.1.2 - minor fix related to a bug that failed the processing when the input was one of the words in exceptions notes 1/2 (related to [issue #1](https://github.com/nearchos/GreekTools/issues/1))