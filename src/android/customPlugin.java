 // package��Ϣ������<platform name="android">�µ�������Ϣƥ��
    package com.cordovacn.www; 

    import org.apache.cordova.CallbackContext;
    import org.apache.cordova.CordovaPlugin;
    import org.apache.cordova.PluginResult;
    import org.json.JSONArray;
    import org.json.JSONException;


    // ����plugin�����̳���CordovaPlugin
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
            // ��ios��ͬ��android��ͨ��action������js����ľ���api
            if (action.equals(customPlugin.METHOD_ECHO)) {
                // ��js���봫��Ĳ�����ȡ�õ�һ��������ֵ
                final String isRunBackgroud = args.getString(0);
                if(isRunBackgroud.equals("false")) {
                    // pluginִ�гɹ������سɹ���Ϣ������successCallback�ص�
                    callbackContext.success("Invoke success!");
                } else if(isRunBackgroud.equals("true")) {
                    // ���ִ�и��Ӻ�ʱ�Ĳ�������Ҫ���߳��н��У�����app��ϵͳɱ��
                    this.cordova.getThreadPool().execute(new Runnable() {
                        public void run() {
                            callbackContext.success("Invoke success(background)!");
                        }
                    });
                } else {
                    // pluginִ��ʧ�ܣ�����ʧ����Ϣ������failedCallback�ص�
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "ERROR!"));
                }
            } else {
                return false;
            }
            return true;
        }
    }