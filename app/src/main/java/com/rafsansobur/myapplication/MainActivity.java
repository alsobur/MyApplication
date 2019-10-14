package com.rafsansobur.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    AdView mAdView;
    Button f;
    InterstitialAd mInterstitialAd;
    String myAdid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        Firebase.setAndroidContext(this);
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                Firebase firebase = new Firebase("https://my-application-e10bb.firebaseio.com/interstitial");
                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mInterstitialAd = new InterstitialAd(getApplicationContext());
                        myAdid = dataSnapshot.getValue(String.class);
                        mInterstitialAd.setAdUnitId(myAdid);
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }

        };
        new Thread(runnable2).start();
    }
}