var argscheck = require('cordova/argscheck'),
        exec      = require('cordova/exec');

    var customPlugin = {};
    // 定义对外发布的接口echo，该方法可以通过navigator.customPlugin.echo调用
    customPlugin.echo = function(successCallback, errorCallback, args) {
      exec(successCallback, errorCallback, "customPlugin", "echo", args);
    };
    // 发布接口对象
    module.exports = customPlugin;