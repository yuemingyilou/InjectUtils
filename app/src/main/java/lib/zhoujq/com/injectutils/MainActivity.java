package lib.zhoujq.com.injectutils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.util.views.BindClick;
import com.lib.util.views.BindView;
import com.lib.util.views.FindViewUtil;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text)
    TextView text1;
    @BindView(R.id.text2)
    Button text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FindViewUtil.inject(this);
        text1.setText("MainActivity-->test1");
        text2.setText("MainActivity-->test2");
    }

    @BindClick({R.id.text, R.id.text2})
    public void onText1Click(View v) {
        if (v == text1) {
            startActivity(new Intent(this, Main2Activity.class));
            Toast.makeText(this, "MainActivity-->onText1Click", Toast.LENGTH_LONG).show();
        } else if (v == text2) {
            Toast.makeText(this, "MainActivity-->onText2Click", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl, new FragmentTest()).commit();
        }
    }
}
