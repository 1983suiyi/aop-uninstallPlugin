LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS:=-llog -lz

LOCAL_MODULE:=hello

LOCAL_SRC_FILES:=diandian_myapplication_MainActivity.cpp

include $(BUILD_SHARED_LIBRARY)

LOCAL_ARM_MODE:=arm