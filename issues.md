# 搭建遇到的问题
## 1. 如何创建一个共用的`base_build.gradle`
在build.gradle 顶部导入创建的共用gradle
```shell
apply from: '../cg_conf/base_build.gradle'
```
build报错
```text
> startup failed:
  script '/Users/craig/data/code/cg_game_frame/cg_conf/base_build.gradle': 1: Only Project and Settings build scripts can contain plugins {} blocks
  
  See https://docs.gradle.org/7.2/userguide/plugins.html#sec:plugins_block for information on the plugins {} block
  
   @ line 1, column 1.
     plugins {
     ^
  
  1 error
* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
* Get more help at https://help.gradle.org
BUILD FAILED in 1s
```
根据报错删了plugins，修改build.gradle
```groovy
buildscript {
    group 'com.cg.train'
    version '1.0-SNAPSHOT'
    repositories {
        mavenCentral()
    }

}
apply from: '../cg_conf/base_build.gradle'
test {
    useJUnitPlatform()
}
```
还是报错
```text
Could not open init generic class cache for initialization script '/private/var/folders/y0/qqk5dr497x5931nj2d2g9yqh0000gn/T/wrapper_init.gradle' (/usr/local/Cellar/gradle/7.1.1/caches/7.1/scripts/dlf5z6mmaljic7zrvzr2jzt1y).
> BUG! exception in phase 'semantic analysis' in source unit '_BuildScript_' Unsupported class file major version 61

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
```
游戏使用的是java17，gradle版本是7.1，升级gradle。gradle7.3+才支持java17版本

最后升级idea到最新版本，并且放弃共用.gradle文件。
其余的等后续再看看。