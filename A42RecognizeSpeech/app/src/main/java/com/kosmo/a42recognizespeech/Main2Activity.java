package com.kosmo.a42recognizespeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements RecognitionListener {

    private static final String TAG = "lecture";

    private static final int REQUEST_CODE = 1000;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView1 = findViewById(R.id.textView1);
    }

    public void onBtn1Clicked(View v) {
        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "음성 검색");
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);

            startActivityForResult(intent, REQUEST_CODE);
        }
        catch (ActivityNotFoundException e) {
            Log.d(TAG, "ActivityNotFoundException");
        }
    }


    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int i) {
        String message;
        switch (i) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "오디오 에러";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "클라이언트 에러";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "퍼미션없음";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "네트워크 에러";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "네트워크 타임아웃";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "찾을수 없음";;
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "BUSY";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "서버이상";;
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "시간초과";
                break;
            default:
                message = "알수없음";
                break;
        }

        Log.d(TAG, "SPEECH ERROR : " + message);
    }


    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches =
                results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        String str = "";
        for (int i = 0; i < matches.size(); i++) {
            str = str + matches.get(i) + "\n";
            Log.d(TAG, "onResults text : " + matches.get(i));
        }

        textView1.setText(str);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    /*
    startActivityForResult()를 통해 인텐트를 실행한 후 결과를 콜백받을때
    사용하는 메서드
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE : {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text =
                            data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String str = "";
                    for (int i = 0; i < text.size(); i++) {
                        str = str + text.get(i) + "\n";
                        Log.i(TAG, "onActivityResult text : " + text.get(i));
                    }
                    textView1.setText(str);

                    String[] rs = new String[text.size()];
                    text.toArray(rs);

                    //음성인식 결과데이터를 텍스트뷰에 출력
                    textView1.setText(rs[0]);

                    //////////////////////////////////////////////////////////////////////
                    /*
                    하나의 문장으로 2번 이상의 명령실행이 필요한 경우 아래와 같이 List로
                    만들어서 사용하면 된다.
                    => "코딩 교육에는 자바가 가장 좋은 언어입니다."
                     */
                    String[] searchLists = {"코딩", "자바", "코스모", "명령", "팀플"};
                    String[] searchTxtArr = rs[0].split(" ");
                    for (String s : searchTxtArr) {
                        for (String li : searchLists) {
                            if (s.contains(li)) {
                                if (li.equals("코딩")) {
                                    Toast.makeText(getApplicationContext(),
                                            "명령1:" +li+ "이(가) 인식됨",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else if (li.equals("자바")) {
                                    Toast.makeText(getApplicationContext(),
                                            "명령2:" +li+ "이(가) 인식됨",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
    }
}