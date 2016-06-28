cmake_minimum_required(VERSION 3.5)
project(jni)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
        main.cpp)

include_directories(.)

add_executable(jni ${SOURCE_FILES})
