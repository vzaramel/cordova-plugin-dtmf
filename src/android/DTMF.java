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
package org.apache.cordova.dtmf;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.util.Log;

public class DTMF extends CordovaPlugin {
    
    public static final String TAG = "DTMF";
    private enum METHODS {
      startTone
    }
    
    private ToneGenerator toneGenerator;
    
    @Override
    public void initialize(CordovaInterface cordova, final CordovaWebView webView) {
      super.initialize(cordova, webView);
      AudioManager audio = (AudioManager) cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
      int volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
      toneGenerator = new ToneGenerator(AudioManager.STREAM_DTMF, volume);
    }
    
    @Override
    public void onDestroy() {
      super.onDestroy();
      toneGenerator.release();
    }
    
    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        METHODS method = null;
        try {
          method = METHODS.valueOf(action);
        } catch (Exception e) {
          return false;
        };
        switch( method ) {
        case startTone:
          this.startTone(args, callbackContext);
          break;
        }
        return true;
    }
    
    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------
    
    /**
     * Get the OS name.
     *
     * @return
     * @throws JSONException 
     */
    public void startTone(JSONArray args, final CallbackContext callbackContext) throws JSONException {
        final int tone = args.getInt(0);
        final int duration = args.getInt(1);
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                toneGenerator.startTone(tone, duration);
                callbackContext.success();
            }
        });
    }
    
}
