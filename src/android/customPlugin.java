 // package信息必须与<platform name="android">下的配置信息匹配
    package com.cordovacn.www; 

    import org.apache.cordova.CallbackContext;
    import org.apache.cordova.CordovaPlugin;
    import org.apache.cordova.PluginResult;
    import org.json.JSONArray;
    import org.json.JSONException;


    // 所有plugin类必须继承至CordovaPlugin
    public class customPlugin extends CordovaPlugin {

        public static final String METHOD_ECHO = "echo";

        /**
         * Executes the request and returns PluginResult.
         *
         * @param action            The action to execute.
         * @param args              JSONArray of arguments for the plugin.
         * @param callbackContext   The callback context used when calling back into JavaScript.
         * @return                  True if the action was valid, false otherwise.
         */
        public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
            // 与ios不同，android是通过action来区分js请求的具体api
            if (action.equals(customPlugin.METHOD_ECHO)) {
                // 从js代码传入的参数中取得第一个参数的值
                final String isRunBackgroud = args.getString(0);
                if(isRunBackgroud.equals("false")) {
                    // plugin执行成功，返回成功信息，调用successCallback回调
                    callbackContext.success("Invoke success!");
                } else if(isRunBackgroud.equals("true")) {
                    // 如果执行复杂耗时的操作，需要在线程中进行，避免app被系统杀死
                    this.cordova.getThreadPool().execute(new Runnable() {
                        public void run() {
                            callbackContext.success("Invoke success(background)!");
                        }
                    });
                } else {
                    // plugin执行失败，返回失败信息，调用failedCallback回调
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "ERROR!"));
                }
            } else {
                return false;
            }
            return true;
        }
    }