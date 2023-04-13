# TampereJS JSI example

I created this repository as a demo of JSI for my talk for TampereJS.

## How to run it

This is ReactNative application, that uses new architecture. To run it run:
- `npm install` to install dependencies
- `cd ios && RCT_NEW_ARCH_ENABLED=1 bundle exec pod install && cd ..` to install pods for iOS (you can skip this step if you are building only for Android).
- `npm run ios` or `npm run android` to start the application for given platform

# Notes

1. Remember that modules must start from Native...

## Module

- TampereJsCppModule/*

## Generated files

- ios/build/generated/ios/TampereJsSpecs/TampereJsSpecs.h

## Resources

- https://reactnative.dev/docs/the-new-architecture/cxx-cxxturbomodules
