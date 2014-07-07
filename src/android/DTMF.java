
package vzaramel.cordova.dtmf;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.media.AudioManager;
import android.media.ToneGenerator;

public class DTMF extends CordovaPlugin {
    
    public static final String TAG = "DTMF";
    private enum METHODS {
      startTone
    }
    
    private ToneGenerator toneGenerator;
    
    /**
     *  The DTMF tone volume relative to other sounds in the stream
     *  {@link} https://android.googlesource.com/platform/packages/apps/Contacts/+/donut-release/src/com/android/contacts/TwelveKeyDialer.java#82
     **/
    private static final int TONE_RELATIVE_VOLUME = 100;
    
    @Override
    public void initialize(CordovaInterface cordova, final CordovaWebView webView) {
      super.initialize(cordova, webView);
      toneGenerator = new ToneGenerator(AudioManager.STREAM_DTMF, TONE_RELATIVE_VOLUME);
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
