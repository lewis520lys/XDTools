package com.xdkj.xd.xdtools;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xdkj.xd.xdtools.view.SweepView;
import com.zhy.autolayout.AutoLayoutActivity;

public class MainActivity extends AutoLayoutActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageView iv_bluetooth;
    private TextView tv_hlep;
    private ImageView iv_power;
    private SweepView sweepView;
    private Button bt_minus;
    private SeekBar sb;
    private Button bt_add;
    private float currentDegree=0;
    private float currProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv_bluetooth = (ImageView) findViewById(R.id.iv_bluetooth);
        tv_hlep = (TextView) findViewById(R.id.tv_hlep);
        iv_power = (ImageView) findViewById(R.id.iv_power);
        sweepView = (SweepView) findViewById(R.id.sweepView);
        bt_minus = (Button) findViewById(R.id.bt_minus);
        sb = (SeekBar) findViewById(R.id.sb);
        bt_add = (Button) findViewById(R.id.bt_add);

        bt_minus.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        tv_hlep.setOnClickListener(this);
        sb.setOnSeekBarChangeListener(this);
        currProgress = currentDegree / 360 * 40;
        sb.setProgress((int) currProgress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_minus:
                if (currentDegree>0){
                    currentDegree= currentDegree-9.0f;
                    sweepView.setmCurrentNum(currentDegree);
                    currProgress = currentDegree / 360 * 40;
                    sb.setProgress((int) currProgress);
                }
                break;
            case R.id.bt_add:
                if (currentDegree<360){
                    currentDegree= currentDegree+9.0f;
                    sweepView.setmCurrentNum(currentDegree);
                    currProgress = currentDegree / 360 * 40;
                    sb.setProgress((int) currProgress);
                }
                break;
                case R.id.tv_hlep:

                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        currentDegree = i*9 ;
        Log.e("currentDegree",currentDegree+"");
        sweepView.setmCurrentNum(currentDegree);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
