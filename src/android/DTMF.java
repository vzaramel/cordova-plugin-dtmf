/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package org.apache.cordova.device;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.media.AudioManager;
import android.media.ToneGenerator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.Settings;

public class DTMF extends CordovaPlugin {
    
    public static final String TAG = "DTMF";

    private ToneGenerator toneGenerator;
    /**
     * Constructor.
     */
    public DTMF() {
        
    }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        ToneGenerator = new ToneGenerator(AudioManager.STREAM_DTMF,ToneGenerator.MAX_VOLUME);
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if( action.Equals("setDTMF") )
        {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    res = new JSONObject();
                    setDTMF(args.getLong(0));
                    callbackContext.success();
                }
            });
            return true;
        }
        return false;
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------

    /**
     * Get the OS name.
     * 
     * @return
     */
    public void setDTMF(int tone) {
        
        switch(tone)
        {
            case 0:
                tone = ToneGenerator.TONE_DTMF_0;
                break;
            case 1:
                tone = ToneGenerator.TONE_DTMF_1;
                break;
            case 2:
                tone = ToneGenerator.TONE_DTMF_2;
                break;
            case 3:
                tone = ToneGenerator.TONE_DTMF_3;
                break;
            case 4:
                tone = ToneGenerator.TONE_DTMF_4;
                break;
            case 5:
                tone = ToneGenerator.TONE_DTMF_5;
                break;
            case 6:
                tone = ToneGenerator.TONE_DTMF_6;
                break;
            case 7:
                tone = ToneGenerator.TONE_DTMF_7;
                break;
            case 8:
                tone = ToneGenerator.TONE_DTMF_8;
                break;
            case 9:
                tone = ToneGenerator.TONE_DTMF_9;
                break;
            case 10:
                tone = ToneGenerator.TONE_DTMF_A;
                break;
            case 11:
                tone = ToneGenerator.TONE_DTMF_B;
                break;
            case 12:
                tone = ToneGenerator.TONE_DTMF_C;
                break;
            case 13:
                tone = ToneGenerator.TONE_DTMF_D;
                break;
            default:
                break;
        }
        toneGenerator.startTone(tone, 2000);
    }

}
