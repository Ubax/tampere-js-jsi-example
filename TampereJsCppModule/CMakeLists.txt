cmake_minimum_required(VERSION 3.13)
set(CMAKE_VERBOSE_MAKEFILE on)

add_compile_options(
        -fexceptions
        -frtti
        -std=c++17)

file(GLOB TampereJsCppModule_SRC CONFIGURE_DEPENDS *.cpp)
add_library(TampereJsCppModule STATIC ${tm_SRC})

target_include_directories(TampereJsCppModule PUBLIC .)
target_include_directories(react_codegen_TampereJsSpecs PUBLIC .)

target_link_libraries(TampereJsCppModule
        jsi
        react_nativemodule_core
        react_codegen_TampereJsSpecs)

