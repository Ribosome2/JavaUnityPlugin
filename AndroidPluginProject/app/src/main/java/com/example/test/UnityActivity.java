package com.example.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UnityActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public String ShowDialog(final String _title, final String _content){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(UnityActivity.this);
                builder.setTitle(_title).setMessage(_content).setPositiveButton("Down", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callUnityFunc("TestManager","DiaCallback","dd");
                            }
                        }
                        );
                builder.show();
            }
        });

        return "Java return";
    }

    // 定义一个显示Toast的方法，在Unity中调用此方法
    public void ShowToast(final String mStr2Show){
        // 同样需要在UI线程下执行
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),mStr2Show, Toast.LENGTH_LONG).show();
            }
        });
    }


    //  定义一个手机振动的方法，在Unity中调用此方法
    public void SetVibrator(){
        Vibrator mVibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        mVibrator.vibrate(new long[]{200, 2000, 2000, 200, 200, 200}, -1); //-1：表示不重复 0：循环的震动
    }

    // 第一个参数是unity中的对象名字，记住是对象名字，不是脚本类名
    // 第二个参数是函数名
    // 第三个参数是传给函数的参数，目前只看到一个参数，并且是string的，自己传进去转吧
    public void callUnityFunc(String _objName , String _funcStr, String _content)
    {
        UnityPlayer.UnitySendMessage(_objName, _funcStr, "Come from:" + _content);
    }

}
