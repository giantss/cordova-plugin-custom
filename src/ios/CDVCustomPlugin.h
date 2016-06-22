#import <Foundation/Foundation.h>
    #import <Cordova/CDVPlugin.h>
    // 同样，plugin类必须派生至CDVPlugin
    @interface CDVCustomPlugin : CDVPlugin
    // 定义一个plugin的api供js调用
    - (void)echo:(CDVInvokedUrlCommand*)command;

    @end