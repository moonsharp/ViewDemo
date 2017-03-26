package kk.qisheng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GitTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_test);

        test1();
    }

    private void test1() {
        Toast.makeText(GitTestActivity.this, "test2", Toast.LENGTH_SHORT).show();
    }

}
