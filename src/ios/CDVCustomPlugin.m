 #import "CDVCustomPlugin.h"
    #import <Cordova/CDVViewController.h>

    @implementation CDVCustomPlugin

    - (void)echo:(CDVInvokedUrlCommand*)command
    {
        // ȡ��js���ݹ����Ĳ���
        NSString* isRunBackgroud = [command argumentAtIndex:0];
        if([isRunBackgroud isEqualToString:@"false"]) {
            // pluginִ�гɹ������سɹ���Ϣ������successCallback�ص�
            CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Invoke success!"];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        } else if([isRunBackgroud isEqualToString:@"true"]) {
            // ���ִ�и��Ӻ�ʱ�Ĳ�������Ҫ���߳��н��У�����app��ϵͳɱ��
            [self.commandDelegate runInBackground:^ {
                CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Invoke success(background)!"];
                [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            }];
        } else {
            // pluginִ��ʧ�ܣ�����ʧ����Ϣ������failedCallback�ص�
            CDVPluginResult *result = [CDVPluginResult resultWithStatus: CDVCommandStatus_ERROR messageAsString:@"ERROR!"];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }
    @end