#import <Foundation/Foundation.h>
    #import <Cordova/CDVPlugin.h>
    // ͬ����plugin�����������CDVPlugin
    @interface CDVCustomPlugin : CDVPlugin
    // ����һ��plugin��api��js����
    - (void)echo:(CDVInvokedUrlCommand*)command;

    @end