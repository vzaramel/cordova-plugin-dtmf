

var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function DTMF(){

}

/**
 * Start tone
 *
 * @param {Function} successCallback The function to call when the heading data is available
 * @param {Function} errorCallback The function to call when there is an error getting the heading data. (OPTIONAL)
 */
DTMF.prototype.startTone = function(successCallback, errorCallback, tone, duration) {
    argscheck.checkArgs('fF', 'DTMF.startTone', arguments);
    exec(successCallback, errorCallback, "DTMF", "startTone", [tone, duration]);
};

DTMF.prototype.TONE_0 = 0;
DTMF.prototype.TONE_1 = 1;
DTMF.prototype.TONE_2 = 2;
DTMF.prototype.TONE_3 = 3;
DTMF.prototype.TONE_4 = 4;
DTMF.prototype.TONE_5 = 5;
DTMF.prototype.TONE_6 = 6;
DTMF.prototype.TONE_7 = 7;
DTMF.prototype.TONE_8 = 8;
DTMF.prototype.TONE_9 = 9;
DTMF.prototype.TONE_A = 12;
DTMF.prototype.TONE_B = 13;
DTMF.prototype.TONE_C = 14;
DTMF.prototype.TONE_D = 15;
DTMF.prototype.TONE_P = 11;
DTMF.prototype.TONE_S = 10;


module.exports = new DTMF();
