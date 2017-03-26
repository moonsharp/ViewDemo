package kk.qisheng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_simple).setOnClickListener(this);
        findViewById(R.id.btn_loading1).setOnClickListener(this);
        findViewById(R.id.btn_loading3).setOnClickListener(this);
        findViewById(R.id.btn_path).setOnClickListener(this);
        findViewById(R.id.btn_loading4).setOnClickListener(this);
        findViewById(R.id.btn_loading6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_simple:
                startActivity(new Intent(MainActivity.this,SimpleViewActivity.class));
                break;
            case R.id.btn_loading1:
                startActivity(new Intent(MainActivity.this,Loading1Activity.class));
                break;
            case R.id.btn_loading3:
                startActivity(new Intent(MainActivity.this,Loading3Activity.class));
                break;
            case R.id.btn_path:
                startActivity(new Intent(MainActivity.this,DrawPathActivity.class));
                break;
            case R.id.btn_loading4:
                startActivity(new Intent(MainActivity.this,Loading4Activity.class));
                break;
            case R.id.btn_loading6:
                startActivity(new Intent(MainActivity.this,Loading6Activity.class));
                break;
        }
    }

}
