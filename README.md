# TampereJS JSI example

This repository contains demo for [my talk "JSI - How to call C++ from JS"](https://docs.google.com/presentation/d/1V9MA4U5-5GHNLoZ82JxiYk6-99CY0vQn8-KskJhNNW0/edit?usp=sharing) for TampereJS.

## How to run it

This is ReactNative application, that uses new architecture. To run it run:

- `npm install` to install dependencies
- `cd ios && RCT_NEW_ARCH_ENABLED=1 bundle exec pod install && cd ..` to install pods for iOS (you can skip this step if you are building only for Android).
- `npm run ios` or `npm run android` to start the application for given platform (android is not working currently)

## C++ Module

- TampereJsCppModule/\*

## Changes in files

Common for both platforms:

- package.json

iOS:

- ios/JSI_example/AppDelegate.mm
- ios/Podfile

Android:

- android/app/build.gradle
- android/app/src/main/jni/\*

## Generated files

- ios/build/generated/ios/TampereJsSpecs/TampereJsSpecs.h
- ios/build/generated/ios/TampereJsSpecsJSI-generated.cpp

## Resources

- [ReactNative docs about C++ modules](https://reactnative.dev/docs/the-new-architecture/cxx-cxxturbomodules)
- [JSI types and functions](https://github.com/facebook/react-native/blob/0.71-stable/ReactCommon/jsi/jsi/jsi.h)
