package com.kosmo.a33xmljsonparser02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    //각 위젯 설정을 위한 멤버변수
    TextView tv;
    Button btnJson;
    ListView listView;

    //리소스 이미지의 아이디를 저장할 정수형 배열
    int[] profileImg = {
            R.drawable.actor01, R.drawable.actor02, R.drawable.actor03,
            R.drawable.actor04, R.drawable.actor05, R.drawable.actor06,
            R.drawable.actor07, R.drawable.actor08, R.drawable.actor09,
            R.drawable.actor10, R.drawable.actor11, R.drawable.actor12,
    };

    //리스트뷰에 출력할 항목을 저장할 List컬렉션
    private List<ActorVO> items = new Vector<ActorVO>();

    //커스텀 어댑터 객체
    private ActorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJson = findViewById(R.id.btn_json);
        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJsonParser();
            }
        });
    }

    //리소스에 저장된 txt파일을 IO스트림을 통해 연결한 후 내용을 읽어온다.
    private String readJsonTxt() {
        String jsonData = null;
        //리소스 폴더 하위의 raw폴더에서 파일을 가져온다.
        InputStream inputStream = getResources().openRawResource(R.raw.json);
        //파일의 내용을 읽기위해 스트림을 생성한다.
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;

        try {
            //파일의 내용을 읽어온다. 문서의 끝까지 읽으면 -1이 반환되므로
            //이때는 반복문을 탈출한다. 읽은 내용은 앞에서 생성한 스트림 변수에 저장한다.
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            //저장된 내용을 문자열로 변환한 후 마지막에 반환한다.
            jsonData = byteArrayOutputStream.toString();
            inputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return jsonData;
    }

    private void getJsonParser() {
        String jsonStr = readJsonTxt();
        Log.i("ResourcesRAW", "json.txt 내용 : " + jsonStr);

        try {
            //데이터로 사용된 json은 객체이다.
            JSONObject object = new JSONObject(jsonStr);
            //key값 member의 값은 배열이다.
            JSONArray array = object.getJSONArray("member");
            //배열의 크기만큼 반복한다.
            for (int i = 0; i < array.length(); i++) {
                //배열의 각 원소는 객체이다. 인덱스로 얻어온다.
                JSONObject item = array.getJSONObject(i);
                //이름과 나이 파싱
                String name = item.getString("name");
                String age = item.getString("age");
                //hobbys는 배열이다.
                JSONArray hobbysArr = item.getJSONArray("hobbys");
                String hobbys = "";
                for (int j = 0; j < hobbysArr.length(); j++)  {
                    hobbys += hobbysArr.getString(j) + " ";
                }
                //login은 객체이므로 각 key값으로 접근하여 파싱한다.
                String user = item.getJSONObject("login").getString("user");
                String pass = item.getJSONObject("login").getString("pass");
                String loginInfo = String.format("아이디:%s, 패스워드:%s", user, pass);
                String printStr = String.format("이름:%s, 나이:%s, 취미:%s, " +
                        "아이디:%s, 패스워드:%s", name, age, hobbys, user, pass);

                Log.i("JsonParsing", "정보 > " + printStr);

                /*
                파싱한 정보는 VO객체에 저장한 후 리스트 컬렉션에 추가한다.
                해당 컬렉션에 저장된 값을 어댑터 객체에서 데이터로 사용하게 된다.
                 */
                items.add(new ActorVO(name, age, hobbys, loginInfo, profileImg[i]));
            }

            /*
            커스텀 어댑터 객체를 생성한다. 인자로 컨텍스트, 데이터를 지정한 컬렉션,
            커스텀 레이아웃 XML을 전달한다. 어댑터에서는 레이아웃을 전개하고,
            getView()에서 리스트 컬렉션에 저장된 항목을 하나씩 반환하여
            전체 리스트뷰를 구성하게 된다.
             */
            adapter = new ActorAdapter(this, items, R.layout.actor_layout);
            listView = findViewById(R.id.listview);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),
                            "선택한 배우:" + items.get(position).getName(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}