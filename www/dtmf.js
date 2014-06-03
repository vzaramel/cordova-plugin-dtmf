/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

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
    argscheck.checkArgs('fF', 'DTMF.setDTMF', arguments);
    exec(successCallback, errorCallback, "DTMF", "startTone", [tone, duration]);
};

DTMF.prototype.TONE_DTMF_0 = 0;
DTMF.prototype.TONE_DTMF_1 = 1;
DTMF.prototype.TONE_DTMF_2 = 2;
DTMF.prototype.TONE_DTMF_3 = 3;
DTMF.prototype.TONE_DTMF_4 = 4;
DTMF.prototype.TONE_DTMF_5 = 5;
DTMF.prototype.TONE_DTMF_6 = 6;
DTMF.prototype.TONE_DTMF_7 = 7;
DTMF.prototype.TONE_DTMF_8 = 8;
DTMF.prototype.TONE_DTMF_9 = 9;
DTMF.prototype.TONE_DTMF_A = 12;
DTMF.prototype.TONE_DTMF_B = 13;
DTMF.prototype.TONE_DTMF_C = 14;
DTMF.prototype.TONE_DTMF_D = 15;
DTMF.prototype.TONE_DTMF_P = 11;
DTMF.prototype.TONE_DTMF_S = 10;


module.exports = new DTMF();
