package com.kosmo.a38http02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //로그 출력용 태그
    public static final String TAG = "lecture";

    //전역변수(위젯)
    EditText user_id, user_pw;
    TextView textResult;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //서버와 통신시 진행 대화상자를 띄우기 위한 객체생성
        dialog = new ProgressDialog(this);
        dialog.setCancelable(true); //back버튼 누를 경우 닫힘 설정
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//스타일 설정
        dialog.setIcon(android.R.drawable.ic_dialog_email);//아이콘 설정
        dialog.setTitle("로그인 처리중");//타이틀 설정
        dialog.setMessage("서버로부터 응답을 기다리고 있습니다.");//메세지 설정

        //레이아웃의 모든 위젯을 얻어옴
        textResult = findViewById(R.id.text_result);//텍스트뷰
        user_id = findViewById(R.id.user_id);//아이디 입력
        user_pw = findViewById(R.id.user_pw);//패스워드 입력
        Button btnLogin = findViewById(R.id.btn_login);//로그인 버튼
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                서버로 요청할 URL생성 및 파라미터 설정
                안드로이드에서 Spring서버로 요청할 경우 반드시 IP주소나 도메인으로
                사용해야 한다.
                쿼리스트링은 EditText위젯에 입력한 값을 얻어와서 조립한다.
                 */
                String requestUrl = "http://192.168.219.106:8081/jsonrestapi/android/memberLogin.do";
                requestUrl += "?id=" + user_id.getText().toString();
                requestUrl += "&pass=" + user_pw.getText().toString();
                Log.d(TAG, requestUrl);

                /*
                AsyncTask클래스의 doInBackground()메서드를 호출한다.
                이때 요청URL을 인수로 전달한다.
                 */
                new AsyncHttpRequest().execute(requestUrl);
            }
        });
    }

    class AsyncHttpRequest extends AsyncTask<String, Void, String> {
        //doInBackground() 실행 전 먼저 호출된다. 진행 대화상자를 띄워준다.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

        /*
        AsyncTask를 상속하여 클래스를 생성할때 doInBackground() 메서드는 반드시
        오버라이딩 해야 한다. 없으면 에러가 발생한다. 나머지 메서드는 선택사항으로
        필요한 경우에만 오버라이딩 하면 된다.
         */
        @Override
        protected String doInBackground(String... strings) {
            StringBuffer sBuffer = new StringBuffer();
            try {
                //요청 URL을 통해 URL객체를 생성한 후 http통신을 위한 준비를 한다.
                URL url = new URL(strings[0]);
                HttpURLConnection connection =
                        (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true); //이게 있으면 무조건 POST가 된다.
                OutputStream out = connection.getOutputStream();
                out.flush();
                out.close();
                //서버와 정상적으로 연결되면 HTTP_OK를 통해 확인할 수 있다.
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.i(TAG, "HTTP OK 성공");

                    //서버에서 내려주는 JSON데이터를 마지막 라인까지 읽어서 StringBuffer에 저장
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String responseData;

                    while ((responseData = reader.readLine()) != null) {
                        sBuffer.append(responseData + "\n\r");
                    }
                    reader.close();
                }
                else {
                    Log.i(TAG, "HTTP OK 안됨");
                }
                Log.i(TAG, sBuffer.toString());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            //StringBuffer객체에 저장된 JSON데이터를 String으로 변환 후 반환한다.
            return sBuffer.toString();
        }

        /*
        doInBackground()가 종료되면 자동으로 호출된다. 단, 반환값이 있는 경우
        매개변수를 통해 전달받을 수 있다.
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.i(TAG, s);
            //진행 대화상자를 닫아준다.
            dialog.dismiss();
            //JSON데이터를 파싱한 후 결과를 반환받는다.
            String result = jsonParser(s);
            //반환받은 결과 데이터를 텍스트뷰에 출력한다.
            textResult.setText(result);
        }
    }

    public String jsonParser(String data) {
        StringBuffer sb = new StringBuffer();
        try {
            /*
            서버에서 내려받은 JSON데이터가 객체인지 배열인지 확인하여
            상황에 맞게 파싱한다.
            JSON객체라면 JSONObject를 통해 파싱하고
            JSON배열이라면 JSONArray를 통해 파싱한다.
             */
            JSONObject jsonObject = new JSONObject(data);
            int success = Integer.parseInt(jsonObject.getString("isLogin"));
            if (success == 1) {
                sb.append("로그인 성공\n");
                JSONObject memberInfo = jsonObject.getJSONObject("memberInfo");
                String id = memberInfo.getString("id").toString();
                String pass = memberInfo.getString("pass").toString();
                String name = memberInfo.getString("name").toString();
                String regidate = memberInfo.getString("regidate").toString();

                sb.append("==================\n");
                sb.append("아이디:" + id + "\n");
                sb.append("패스워드:" + pass + "\n");
                sb.append("이름:" + name + "\n");
                sb.append("가입날짜:" + regidate + "\n");
            }
            else {
                sb.append("로그인 실패");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}