package com.kosmo.a32xmljsonparser01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnJson1, btnJson2, btnXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJson1 = findViewById(R.id.btn_json1);
        btnJson2 = findViewById(R.id.btn_json2);
        btnXml = findViewById(R.id.btn_xml);

        btnJson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJsonData1();
            }
        });

        btnJson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJsonData2();
            }
        });

        btnXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getXmlData();
            }
        });
    }

    //JSON 파싱1
    private void getJsonData1() {
        /*
        파싱할 데이터의 전체는 객체로 구성되었고, number키에 해당하는
        데이터는 배열로 구성되어 있다.
         */
        String jsonStr = "{'number':[1,2,3,4,5]}";

        //안드로이드에서 JSON 파싱시에는 반드시 예외처리를 해야한다.
        try {
            //JSON 객체를 파싱할때는 JSONObject를 통해 객체를 생성한다.
            JSONObject jsonObject = new JSONObject(jsonStr);
            /*
            number 키값의 value가 배열이므로 getJSONArray로 값을 얻어온다.
             */
            JSONArray jsonArray = jsonObject.getJSONArray("number");

            //값의 크기만큼 반복하면서 각 항목을 출력한다.
            for (int i = 0; i < jsonArray.length(); i++) {
                int tempNum = jsonArray.getInt(i);
                Log.i("KOSMO", "JSON1 파싱데이터 : " + tempNum);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //JSON 파싱2
    private void getJsonData2() {
        /*
        파싱할 데이터의 전체는 객체로 구성되었고, 또한 value도 객체로 구성된
        JSON 데이터
         */
        String jsonStr = "{'color':{'top':'red','right':'blue','bottom':'green'," +
                "'left':'black'}}";

        try {
            //객체이므로 JSONObject로 1차 파싱
            JSONObject jsonObject = new JSONObject(jsonStr);
            //color 키값에 해당하는 데이터도 객체이므로 getJSONObject로 2차 파싱
            JSONObject color = jsonObject.getJSONObject("color");

            //각 키에 해당하는 데이터를 얻어와서 저장한다.
            String top = color.getString("top");
            String right = color.getString("right");
            String bottom = color.getString("bottom");
            String left = color.getString("left");

            String jsonPrint = String.format("top:%s, right:%s, " +
                    "bottom:%s, left:%s", top, right, bottom, left);
            Log.i("KOSMO", "JSON2 파싱데이터 : " + jsonPrint);

            //has(키값) : JSON객체내에 해당 키값이 있는지 확인하는 메서드
            if (color.has("left")) {
                Log.i("has메서드1", "key:left있음");
            }
            else {
                Log.i("has메서드1", "key:left없음");
            }

            if (color.has("css")) {
                Log.i("has메서드2", "key:css있음");
            }
            else {
                Log.i("has메서드2", "key:css없음");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    XmlPullParser 클래스를 이용한 XML파싱
    형식
        next() : XML탐색을 위한 메서드로 XML요소를 식별하면 탐색을 중지하고
            식별된 요소를 반환한다.

    상수
        START_DOCUMENT : XML문서의 시작으로 파싱의 시작을 알린다.
        START_TAG : XML시작태그를 의미한다.
        TEXT : XML의 시작태그와 종료태그 사이의 텍스트로 실제 파싱할 데이터를 가리킨다.
        END_TAG : 종료태그를 의미한다.
        END_DOCUMENT : XML문서의 끝을 의미한다.
    태그는 xxx.getName()을 통해 파싱하고, 데이터는 xxx.getText()를 통해 파싱한다.
     */
    private void getXmlData() {

        try {
            //파싱한 값을 저장할 리스트 컬렉션 생성
            ArrayList<String> xNumber = new ArrayList<String>();
            ArrayList<String> xActor = new ArrayList<String>();
            ArrayList<String> xWord = new ArrayList<String>();
            int event = 0;
            String currentTag = null;

            /*
            Arrays.asList()
                : 인자로 주어진 배열을 List컬렉션으로 변환해주는 메서드.
                단, 이렇게 변환된 컬렉션에는 원소를 새롭게 추가 할 수는 없다.
                하지만 컬렉션에서 제공하는 모든 메서드를 사용 할 수 있는 장점이 있다.
             */
            //문자열 배열을 List컬렉션으로 변환
            List<String> tagList = Arrays.asList(new String[]{"number", "actor", "word"});

            //XML리소스 문서를 읽어와 파서를 선언한다.
            XmlPullParser parser = getResources().getXml(R.xml.word);

            //XML문서의 노드를 하나씩 읽으면서 문서의 끝까지 반복한다.
            while ((event=parser.next()) != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    //탐색된 요소가 시작태그이면..
                    case XmlPullParser.START_TAG:
                        //변수에 저장한다.
                        currentTag = parser.getName();//태그를 파싱하는 메서드
                        break;

                    //해당원소가 텍스트(데이터)이면..
                    case XmlPullParser.TEXT:
                        if (currentTag != null && tagList.contains(currentTag)) {
                            //데이터를 가져와서 변수에 저장한다.
                            String value = parser.getText();
                            Log.i("XML>TEXT", "value=" + value);

                            //각 태그명에 해당하는 값을 컬렉션에 저장한다.
                            if (currentTag.equals("number")) {
                                xNumber.add(value);
                            }
                            else if (currentTag.equals("actor")) {
                                xActor.add(value);
                            }
                            else if (currentTag.equals("word")) {
                                xWord.add(value);
                            }
                        }
                        break;

                    //해당 요소가 종료태그라면..
                    case XmlPullParser.END_TAG:
                        //해당 변수를 비운다.
                        currentTag = null;
                        break;
                    default:
                        break;
                }
            }

            //컬렉션에 저장된 모든 내용을 로그로 출력한다.
            for (int i = 0; i < xNumber.size(); i++) {
                Log.i("XML>Data", "number=" + xNumber.get(i));
                Log.i("XML>Data", "actor=" + xActor.get(i));
                Log.i("XML>Data", "word=" + xWord.get(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}