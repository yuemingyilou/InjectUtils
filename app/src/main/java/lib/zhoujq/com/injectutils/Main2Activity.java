package lib.zhoujq.com.injectutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.util.views.BindView;
import com.lib.util.views.FindViewUtil;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.text)
    TextView text1;
    @BindView(R.id.text2)
    Button text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.text7)
    TextView text7;
    @BindView(R.id.text8)
    TextView text8;
    @BindView(R.id.text9)
    TextView text9;
    @BindView(R.id.text10)
    TextView text10;
    @BindView(R.id.text11)
    TextView text11;
    @BindView(R.id.text12)
    TextView text12;
    @BindView(R.id.text13)
    TextView text13;
    @BindView(R.id.text14)
    TextView text14;
    @BindView(R.id.text15)
    TextView text15;

    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        time = System.currentTimeMillis();
        FindViewUtil.inject(this, this);
        text1.setText("Main2Activity-->test1");
        text2.setText("Main2Activity-->test2");
        Log.i("test", "time:" + (System.currentTimeMillis() - time));
    }

    @Override
    public void onClick(View v) {
        if (v == text1) {
            Toast.makeText(this, "Main2Activity-->onText1Click", Toast.LENGTH_LONG).show();
        } else if (v == text2) {
            Toast.makeText(this, "Main2Activity-->onText2Click", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl, new FragmentTest2()).commit();
        }
    }
}
