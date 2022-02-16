package com.kosmo.a37http01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String TAG = "KOSMO";

    TextView textResult;
    ProgressDialog dialog;
    int buttonResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.text_result);
        Button btnJson = findViewById(R.id.btn_json);
        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonResId = v.getId();

                /*
                버튼 클릭시 로컬 Spring Rest API서버와의 통신을 위해 객체를 생성한 후
                첫번째 인수로 접속URL을 전달한다. execute() 호출을 통해 doInBackground()가
                호출된다.
                 */
                new AsyncHttpRequest().execute(
                        "http://192.168.219.106:8081/jsonrestapi/android/memberList.do"
                );
            }
        });

        /*
        Http통신 시작시 진행대화상자를 띄우기 위해 객체를 생성한다.
        스타일, 아이콘, 타이틀 등의 설정을 진행한다.
        특히 setCancelable()은 물리버튼(Back버튼)을 눌렀을때 대화창이 닫히지 않도록
        설정한다.
         */
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setTitle("회원정보 리스트 가져오기");
        dialog.setMessage("서버로부터 응답을 기다리고 있습니다.");
        dialog.setCancelable(false);
    }

    class AsyncHttpRequest extends AsyncTask<String, Void, String> {
        //통신이 시작되면 제일 먼저 진행 대화상자를 띄워준다.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

        //실제 서버와의 통신을 진행한다.
        @Override
        protected String doInBackground(String... strings) {

            //매개변수는 가변인자를 사용하므로 항상 배열형태로 사용해야 한다.

            //스프링 서버에서 반환하는 JSON데이터를 저장
            StringBuffer sBuffer = new StringBuffer();
            try {
                //가변인자를 통해 받은 0번째 인수를 통해 URL객체를 생성한다.
                URL url = new URL(strings[0]);
                //URL을 통해 연결객체 생성 및 통신방식, 쓰기모드를 설정한다.
                HttpURLConnection connection =
                        (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                OutputStream out = connection.getOutputStream();
                /*
                out.write(strings[1].getBytes());
                파라미터가 2개 이상이라면 &로 문자열을 연결
                out.write("&".getBytes());
                out.write(strings[2].getBytes());
                 */
                out.flush();
                out.close();

                // 서버에 요청이 전달되고 성공하면 HTTP_OK로 확인할 수 있다.
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.i(TAG, "HTTP OK 성공");

                    // 접속에 성공했다면 서버에서 내려준 JSON데이터를 읽어온다.
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String responseData;

                    while ((responseData = reader.readLine()) != null) {
                        // 내용을 한줄씩 읽어서 StringBuffer객체에 저장한다.
                        sBuffer.append(responseData + "\n\r");
                    }
                    reader.close();
                }
                else {
                    Log.i(TAG, "HTTP OK 안됨");
                }

                // 누른 버튼이 "회원리스트 가져오기" 라면 JSON 파싱
                if (buttonResId == R.id.btn_json) {
                    // StringBuffer 객체를 String객체로 변환한다.
                    Log.i(TAG, sBuffer.toString());
                    // JSON배열로 파싱한다.
                    JSONArray jsonArray = new JSONArray(sBuffer.toString());
                    // StringBuffer객체를 비운다.
                    sBuffer.setLength(0);
                    // 배열의 크기만큼 반복하면서 JSON객체를 파싱한다.
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // 파싱한 내용은 StringBuffer객체에 저장한다.
                        sBuffer.append("아이디:"+
                                jsonObject.getString("id") + "\n\r");
                        sBuffer.append("패스워드:"+
                                jsonObject.getString("pass") + "\n\r");
                        sBuffer.append("이름:"+
                                jsonObject.getString("name") + "\n\r");
                        sBuffer.append("가입날짜:"+
                                jsonObject.getString("regidate") + "\n\r");
                        sBuffer.append("------------------\n\r");
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // 파싱된 모든 내용을 반환한다. 반환된 값은 onPostExecute()로 전달된다.
            return sBuffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // 진행대화창을 닫아준다.
            dialog.dismiss();
            // 파싱된 최종결과값을 텍스트뷰에 출력한다.
            textResult.setText(s);
        }
    }
}