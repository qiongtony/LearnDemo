# 定义模块当前路径（必须定义在文件开头）
LOCAL_PATH := $(call my-dir)

# 清空当前环境变量（LOCAL_PATH除外）
include $(CLEAR_VARS)

# 当前模块名（这里会生成libhello-jni.so）
LOCAL_MODULE := hello-jni

# 当前模块包含的源代码文件
LOCAL_SRC_FILES := hello-jni.c

# 当前模块将被编译成一个共享库
include $(BUILD_SHARED_LIBRARY)
