//
// Created by funplus on 2018/3/13.
//

#include "diandian_myapplication_MainActivity.h"
#include <jni.h>
#include <string.h>


JNIEXPORT jstring JNICALL Java_diandian_myapplication_MainActivity_stringFromJNI
        (JNIEnv *env, jobject obj){
    const char * string = "String form native c";
#if defined(__arm__)
    string = "__arm__ String form native c";
#endif
    return env->NewStringUTF(string);
}