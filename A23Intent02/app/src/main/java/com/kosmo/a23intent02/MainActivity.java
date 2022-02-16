package com.kosmo.a23intent02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //부가데이터를 보낼때 응답을 받기위한 코드
    public static final int REQUEST_CODE = 0;

    //위젯선언
    private Button btnOnlyData, btnDataReqResp;
    private EditText editUser, editPass;
    private TextView textViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 얻기
        btnOnlyData = findViewById(R.id.btn_only_data);
        btnDataReqResp = findViewById(R.id.btn_data_req_resp);
        editUser = findViewById(R.id.edit_user);
        editPass = findViewById(R.id.edit_pass);
        textViewMain = findViewById(R.id.textview_main);

        //버튼에 리스너 부착
        btnOnlyData.setOnClickListener(listener);
        btnDataReqResp.setOnClickListener(listener);
    }

    //리스너 부착을 위한 이벤트 핸들러
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //인텐트 생성을 통해 실행할 액티비티를 설정한다.
            Intent intent = new Intent(v.getContext(), OnlyActivity.class);

            //부가데이터를 인텐트에 추가한다.
            intent.putExtra("USER", editUser.getText().toString());//아이디 입력값
            intent.putExtra("PASS", editPass.getText().toString());//패스워드 입력값

            if (v == btnOnlyData) {
                //인텐트를 통해 부가데이터를 전달만 한다. 결과를 돌려받지 않는다.
                startActivity(intent);
            }
            else if (v == btnDataReqResp) {
                /*
                부가데이터를 전달한 후 결과값을 돌려받는다.
                startActivityForResult(인텐트, 요청코드)
                    : 요청코드는 인텐트를 전달하거나 전달받을 액티비티에서
                    식별자로 사용된다.
                 */
                intent.setClass(MainActivity.this, ResultActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        }
    };
    
    /*
    부가데이터를 전달했던 액티비티로부터 결과값을 돌려받기 위해 오버라이딩한다.
    해당 메서드는 setResult()가 호출되면 자동으로 콜백된다. 즉 임의로 호출하지
    않는다.
    
    매개변수
        requestCode : 내가 보낸 인텐트 확인용 요청코드
        resultCode : 인텐트를 받은 액티비티에서 보낸 응답코드
        data : 인텐트를 통해 전송된 부가데이터
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //REQUEST_CODE는 전역변수 0으로 초기화 된 상태이다.
        if (requestCode == REQUEST_CODE) {
            //result_code를 통해 조건으로 분기한다.
            if (resultCode == ResultActivity.RESULT_CODE_FAIL_ID) {
                textViewMain.setText(data.getStringExtra("FAIL_ID"));
            }
            else if (resultCode == ResultActivity.RESULT_CODE_FAIL_PWD) {
                textViewMain.setText(data.getStringExtra("FAIL_PWD"));
            }
            else if (resultCode == Activity.RESULT_OK) {
                textViewMain.setText(data.getStringExtra("SUCCESS"));
            }
        }
    }
}