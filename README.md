# Commons

Common utilities for Java applications

## Features

#### ZIP file extraction
- Multiple ways to extract ZIP archives
- Customizable: rename extracted files before they are extracted

#### HTTP utilities
- Get remote file contents
- Download remote content and write them to file
- Get an instance of a URL without having to handle an exception

#### Stream utilities
- Read InputStreams easily

#### Operating system utilities
- Detect the host operating system

#### Resource utilities
- Get a resource as a File object

#### Hashing utilities
- SHA-1, SHA-256, SHA-384, SHA-512, MD5
- Hexadecimal conversion


## Coming Soon
- We'll see! Submit a suggestion inside issues.

## Getting Started
Add the Pocolifo repository and Commons dependency

### Gradle
```groovy
repositories {
    maven {
        name 'pocolifo'
        url 'https://maven.services.pocolifo.com'
    }
}
```

```groovy
dependencies {
    implementation 'com.pocolifo:commons:1.2.1'
}
```

## Develop
- Clone this repository
- Open the project in IntelliJ IDEA or another editor
- Add an environment variable called `RUNNER_OS` inside the `test` Gradle task. Set it to your OS. Can be `Linux`, `Windows`, or `macOS`.
  For more information, see [GitHub's docs](https://docs.github.com/en/actions/learn-github-actions/environment-variables#default-environment-variables).
- Get developing!
