var argscheck = require('cordova/argscheck'),
        exec      = require('cordova/exec');

    var customPlugin = {};
    // ������ⷢ���Ľӿ�echo���÷�������ͨ��navigator.customPlugin.echo����
    customPlugin.echo = function(successCallback, errorCallback, args) {
      exec(successCallback, errorCallback, "customPlugin", "echo", args);
    };
    // �����ӿڶ���
    module.exports = customPlugin;