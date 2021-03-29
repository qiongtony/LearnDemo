#include <jni.h>

int test(){
    return 123;
}

// 编写jni层，供上层调用 com.example.learndemo;
jint Java_com_example_learndemo_JniActivity_nativeTest(JNIEnv *env, jclass clazz){
    return test();
}