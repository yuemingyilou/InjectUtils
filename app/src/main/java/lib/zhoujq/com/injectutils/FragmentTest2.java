package lib.zhoujq.com.injectutils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.util.views.BindClick;
import com.lib.util.views.BindView;
import com.lib.util.views.FindViewUtil;

public class FragmentTest2 extends Fragment implements View.OnClickListener {
    @BindView(R.id.text)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FindViewUtil.inject(this, this);
        text1.setText("FragmentTest2-->test1");
        text2.setText("FragmentTest2-->test2");
    }

    @Override
    public void onClick(View v) {
        if (v == text1) {
            Toast.makeText(getActivity(), "FragmentTest2-->onText1Click", Toast.LENGTH_LONG).show();
        } else if (v == text2) {
            Toast.makeText(getActivity(), "FragmentTest2-->onText2Click", Toast.LENGTH_LONG).show();
        }
    }
}
