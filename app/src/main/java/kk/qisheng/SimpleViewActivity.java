package kk.qisheng;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kk.qisheng.views.LineProgressBar;

public class SimpleViewActivity extends AppCompatActivity {
    LineProgressBar lpb;
    int lpbProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_viewt);

        lpbStart();
    }

    private void lpbStart() {

        lpb = (LineProgressBar) findViewById(R.id.lpb);
        new Thread() {

            @Override
            public void run() {
                lpbGo(this);
            }
        }.start();
    }

    private void lpbGo(Thread t) {
        while (true) {
            if (lpbProgress > 100) lpbProgress = 0;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    lpb.setProgress(lpbProgress += 5);
                }
            });

            try {
                t.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
