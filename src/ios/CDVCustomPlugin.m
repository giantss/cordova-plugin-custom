 #import "CDVCustomPlugin.h"
    #import <Cordova/CDVViewController.h>

    @implementation CDVCustomPlugin

    - (void)echo:(CDVInvokedUrlCommand*)command
    {
        // 取得js传递过来的参数
        NSString* isRunBackgroud = [command argumentAtIndex:0];
        if([isRunBackgroud isEqualToString:@"false"]) {
            // plugin执行成功，返回成功信息，调用successCallback回调
            CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Invoke success!"];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        } else if([isRunBackgroud isEqualToString:@"true"]) {
            // 如果执行复杂耗时的操作，需要在线程中进行，避免app被系统杀死
            [self.commandDelegate runInBackground:^ {
                CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Invoke success(background)!"];
                [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            }];
        } else {
            // plugin执行失败，返回失败信息，调用failedCallback回调
            CDVPluginResult *result = [CDVPluginResult resultWithStatus: CDVCommandStatus_ERROR messageAsString:@"ERROR!"];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }
    @end