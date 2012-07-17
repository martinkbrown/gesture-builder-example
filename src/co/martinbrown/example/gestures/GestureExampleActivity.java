package co.martinbrown.example.gestures;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.Toast;

public class GestureExampleActivity extends Activity implements OnGesturePerformedListener{

    GestureOverlayView mOverlay;
    GestureLibrary gestureLib;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mOverlay = (GestureOverlayView) findViewById(R.id.gestures);
        mOverlay.addOnGesturePerformedListener(this);

        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);

        if(!gestureLib.load()) {
            Toast.makeText(this, "Couldn't load Gesture Library", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);

        for(Prediction prediction : predictions) {
            if(prediction.score > 1.0) {
                Toast.makeText(getApplicationContext(), prediction.name, Toast.LENGTH_SHORT).show();
            }
        }
    }
}