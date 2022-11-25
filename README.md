### Implemented
- [AC-1] As a user, I want to see a random quote when I open the app.
- [AC-2] As a user, I want to be able to browse through a list of public quotes.
- [AC-4] As a user, I want to see the contents and author of any displayed quote.
- [AC-5] As a user, I want to be able to log in with my existing FavQs account.

### Setup & Info

Project can be imported to android studio
- api key is required, to get it you need to create an account and login on https://favqs.com/api_keys then put it in /project.rootDir/local.properties 
  as FAVQS_API_KEY="your_api_key" <- don't forget the quotes 
- target SDK 30
- build and tested on Android 11 (not tested configuration change)
- used Android Studio Chipmunk | 2021.2.1 Patch 1

### How to run it
1. Import project to Android Studio and hit run
2. connect device and run in terminal ```./gradlew app:build app:installDebug```  from project.rootDir
### Android Studio details

```
Android Studio Chipmunk | 2021.2.1 Patch 1
Build #AI-212.5712.43.2112.8609683, built on May 18, 2022
Runtime version: 11.0.12+0-b1504.28-7817840 aarch64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
macOS 12.4
GC: G1 Young Generation, G1 Old Generation
Memory: 4096M
Cores: 8
Registry: external.system.auto.import.disabled=true, debugger.watches.in.variables=false, kotlin.experimental.new.j2k=false
Non-Bundled Plugins: org.toml.lang (0.2.155.4114-212), org.intellij.plugins.markdown (212.5457.16), org.jetbrains.kotlin (212-1.7.10-release-333-AS5457.46)

```
