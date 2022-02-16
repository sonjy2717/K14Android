package com.kosmo.a16sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
SharedPreferences
    : 해당 애플리케이션에서 저장하고 싶은 데이터가 있을때 단말기에
    파일형태로 보관해주는 기능을 제공한다. 해당 파일은 앱이 삭제되기
    전까지 유지되고 삭제될때 파일도 같이 삭제된다.
    자동로그인, 게임스코어유지, 앱의 마지막 상태 저장 등에 사용된다.
 */
public class MainActivity extends AppCompatActivity {

    //멤버변수 선언
    SharedPreferences.Editor editor;
    EditText tvID;
    EditText tvPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        SharedPreferences 객체에 저장된 내용이 있는지 확인한다.
        Activity.MODE_PRIVATE : 본인의 앱에서만 사용하도록 설정하는 기본값
         */
        SharedPreferences pref = getSharedPreferences("login",
                Activity.MODE_PRIVATE);
        editor = pref.edit();

        //입력상자를 가져온다.
        tvID = findViewById(R.id.etID);
        tvPwd = findViewById(R.id.etPwd);

        /*
        저장된 값을 가져와서 입력상자에 설정한다. 만약 저장된 값이 없을 경우에는
        디폴트값으로 두번째 인자에 지정된 값을 사용할 수 있다.
         */
        String id = pref.getString("id", "");
        String pwd = pref.getString("pwd", "");

        //얻어온 값으로 입력상자에 설정한다.
        tvID.setText(id);
        tvPwd.setText(pwd);
    }

    //로그인 버튼을 눌렀을때 호출한다.
    public void btnLoginClicked(View v) {
        //입력상자에 입력된 내용을 가져와서 문자열로 저장한다.
        String sid = tvID.getText().toString();
        String spwd = tvPwd.getText().toString();

        //객체에 데이터를 저장한다.
        editor.putString("id", sid);
        editor.putString("pwd", spwd);
        //저장 후 반드시 commit()메서드를 호출해야 한다.
        editor.commit();

        Toast.makeText(getApplicationContext(), "아이디/비번 저장됨",
                Toast.LENGTH_SHORT).show();
    }
}