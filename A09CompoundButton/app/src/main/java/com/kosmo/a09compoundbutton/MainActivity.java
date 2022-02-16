package com.kosmo.a09compoundbutton;


import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {

    //체크박스에서 선택한 값을 저장하기 위한 리스트 컬렉션
    private List checkBoxList = new Vector();

    //스피너에 어뎁터 객체를 통해 추가할 항목을 배열로 선언
    private String[] items = {"레드벨벳","트와이스","마마무","블랙핑크",
            "에이핑크","오마이걸","피에스타"};

    //선택한 항목에 대한 결과출력용 변수(라디오, 스피너, 토글버튼, 스위치)
    private String radioChecked = "여성";
    private String spinnerSelected = "트와이스";
    private String toggleOnOff = "OFF";
    private String switchOnOff = "OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //결과출력용 텍스트뷰를 얻어와서 글자크기와 폰트를 설정함.
        final TextView tv = (TextView)findViewById(R.id.textview);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tv.setTypeface(Typeface.SANS_SERIF);
        //체크박스 버튼 가져오기
        CheckBox chk_eco = (CheckBox)findViewById(R.id.check_eco);
        CheckBox chk_pol = (CheckBox)findViewById(R.id.check_pol);
        CheckBox chk_spo = (CheckBox)findViewById(R.id.check_spo);
        CheckBox chk_ent = (CheckBox)findViewById(R.id.check_ent);
        //Java에서 체크된 상태로 설정함
        chk_eco.setChecked(true);
        chk_pol.setChecked(true);
        //xml에서 이미 선택한 항목과 합쳐서 3가지를 리스트 컬렉션에 저장
        checkBoxList.add("정치");
        checkBoxList.add("경제");
        checkBoxList.add("연예");
        //각 체크박스에 리스너 부착. 선택시 onCheckedChanged()메소드를 호출한다.
        chk_eco.setOnCheckedChangeListener(this);
        chk_ent.setOnCheckedChangeListener(this);
        chk_pol.setOnCheckedChangeListener(this);
        chk_spo.setOnCheckedChangeListener(this);

        //라디오 버튼인 경우 라디오그룹을 통해 접근한다.
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        //라디오버튼의 체크해제
        radioGroup.clearCheck();
        //여성을 디폴트로 체크함
        radioGroup.check(R.id.radio_female);
        //라디오그룹에 리스너 부착 후 메소드 오버라이딩
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /*
            라디오 그룹을 통해 선택된 라디오버튼의 id값이 매개변수로 전달된다.
            int형 매개변수 i를 통해 리소스 아이디를 얻어올 수 있다.
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                /*
                안드로이드에서 로그를 확인할때는 Log.로그종류(태그명, 출력값);
                형태로 사용한다. 로그의 종류는 info, debug, error등이 있다.
                아래의 Logcat에서 확인할 수 있다.
                 */
                Log.d("RadioIndex", "라디오 인덱스"+i);

                //매개변수 i를 통해 선택한 라디오 버튼을 얻어온다.
                RadioButton radio = (RadioButton)findViewById(i);
                //해당 라디오버튼의 텍스트를 얻어와서 토스트로 출력한다.
                radioChecked = radio.getText().toString();
                Toast.makeText(MainActivity.this,
                        radioChecked, Toast.LENGTH_SHORT).show();
            }
        });

        //토글버튼, 스위치 버튼을 얻어와서 uncheck상태로 변경함
        ToggleButton toggleButton = (ToggleButton)findViewById(R.id.togglebutton);
        toggleButton.setChecked(false);
        Switch switch_btn = (Switch)findViewById(R.id.switchbutton);
        switch_btn.setChecked(false);
        //리스너 부착. 선택시 onChekcedChanged() 메소드를 호출한다.
        toggleButton.setOnCheckedChangeListener(this);
        switch_btn.setOnCheckedChangeListener(this);

        /*
        스피너 위젯을 얻어온 후 어뎁터 객체와 연결함
            어뎁터객체 사용 형식]
                ArrayAdapter<타입매개변수>(컨텍스트, 모양, 항목);
                -모양은 안드로이드의 기본 레이아웃을 사용
                -항목은 멤버변수로 선언한 String형 배열 사용
         */
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        items);
        //스피너에 어뎁터를 연결한다.
        spinner.setAdapter(adapter);
        /*
        앱 실행시 디폴트로 선택된 항목을 지정하려면 아래와 같이 Java에서 처리한다.
        xml에는 select라는 속성이 별도로 없기때문이다. setSelection()을 실행하면
        onItemSelected() 콜백메소드가 호출된다.
         */
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int i, long l) {
                //선택한 항목의 인덱스 i가 전달되어 해당 항목을 알수있다.
                Toast.makeText(MainActivity.this,
                        items[i],
                        Toast.LENGTH_SHORT).show();
                spinnerSelected = items[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //버튼을 눌렀을때 처리
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //선택한 모든 항목의 값을 얻어와서 텍스트뷰에 설정한다.
                tv.setText(String.format("체크박스:%s\n라디오:%s\n토글:%s\n스위치:%s\n스피너:%s\n",
                        Arrays.toString(checkBoxList.toArray()),
                        radioChecked,
                        toggleOnOff,
                        switchOnOff,
                        spinnerSelected));
            }
        });
    }////onCreate()끝

    //각 버튼을 선택(체크)했을때 이벤트를 감지하는 리스너 메소드
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        /*
        compoundButton : 사용자가 클릭한 버튼 객체가 전달됨.
        b : check or uncheck 여부를 알 수 있음.
         */

        //컴파운드 버튼이 체크박스라면...
        if(compoundButton instanceof CheckBox) {
            if(b==true) {//체크된 상태라면...
                Toast.makeText(this,
                        compoundButton.getText()+"선택함",
                        Toast.LENGTH_SHORT).show();
                //리스트 컬렉션에 선택항목을 추가함.
                checkBoxList.add(compoundButton.getText());
            }
            else {//체크가 해제된 상태라면...
                Toast.makeText(this,
                        compoundButton.getText()+"해제함",
                        Toast.LENGTH_SHORT).show();
                //리스트 컬렉션에 선택항목을 제거함.
                checkBoxList.remove(compoundButton.getText());
            }
        }
        else if(compoundButton.getId()==R.id.togglebutton) {
            //선택한 버튼이 토글버튼일때...
            if(b==true) {
                Toast.makeText(this,"토글ON상태",
                        Toast.LENGTH_SHORT).show();
                toggleOnOff = "토글ON";
            }
            else {
                Toast.makeText(this,"토글OFF상태",
                        Toast.LENGTH_SHORT).show();
                toggleOnOff = "토글OFF";
            }
        }
        else if(compoundButton.getId()==R.id.switchbutton) {
            //선택한 버튼이 스위치인 경우..
            if(b==true) {
                Toast.makeText(this,"스위치ON",
                        Toast.LENGTH_SHORT).show();
                switchOnOff = "스위치ON";
            }
            else {
                Toast.makeText(this,"스위치OFF",
                        Toast.LENGTH_SHORT).show();
                switchOnOff = "스위치OFF";
            }
        }
    }
}
