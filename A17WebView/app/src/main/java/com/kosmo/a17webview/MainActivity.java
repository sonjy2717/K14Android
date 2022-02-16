package com.kosmo.a17webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 얻어오기
        Button btn_go = findViewById(R.id.btn_go);
        Button btn_back = findViewById(R.id.btn_back);
        final EditText url_addr = findViewById(R.id.url_addr);
        final WebView webView = findViewById(R.id.webview);

        //웹뷰 설정을 위한 객체생성
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); //JS의 사용여부 설정
        settings.setDisplayZoomControls(true); //확대 컨트롤 사용여부
        webView.setWebChromeClient(new WebChromeClient()); //시스템에서 기본적으로 제공됨
        webView.setWebViewClient(new CustomWebViewClient()); //개발자가 직접 작성
        webView.setWebChromeClient(new CustomWebChromeClient()); //개발자가 직접 작성

        //바로가기 버튼
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //웹URL 입력상자에 입력한 URL을 얻어와서
                String url = url_addr.getText().toString();
                //웹뷰에서 해당 URL로 이동시킨다.
                webView.loadUrl(url);
            }
        });

        //뒤로가기 버튼
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이전페이지로 이동한다.
                webView.goBack();
            }
        });
    }

    /*
    웹뷰 세팅을 위한 클래스 정의1
        : WebViewClient 클래스를 상속하여 정의한다.
        웹로딩을 담당하고, 스마트폰 앱안에 웹뷰가 들어오도록 설정한다.
        또한 상단의 EditText나 Button들이 그대로 보이도록 설정한다.
        ※ 별도의 코딩없이 상속 후 오버라이딩만 하면 된다.
     */
    class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    /*
    웹뷰 세팅을 위한 클래스 정의2
        : WebChromeClient를 상속하여 정의한다. Javascript의 alert()를
        앱에 맞게 Toast로 변경하기 위한 클래스로 웹에서 사용하는 alert()의
        기본창을 사용하지 않는다.
        또한 경고창을 별도로 띄우지 않고 파라미터를 전달하는 용도로도 사용된다.
     */
    class CustomWebChromeClient extends  WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            /*
            1|안녕하세요|kosmo
                : alert()로 위와같이 전달되는 경우 split()으로 아래와 같이 문자열 배열을 만들어
                처리하면 메세지는 메세지대로 파라미터는 파라미터대로 별도로 구분하여 사용할 수 있다.
                ※ split()에서 구분자로 |(파이프) 기호를 사용하는 경우 앞뒤로 []를 붙여줘야
                한다. 파이프 기호는 정규표현식에서 사용되는 기호이기 때문이다.
             */
            String[] msgArray = message.split("[|]");
            Log.d("KOSMO123", "message[0]=" + msgArray[0]);
            if (msgArray[0].equals("1")) {
                //파싱한 결과 첫번째값이 1인 경우 파라미터로 판단하여 Logcat에 출력한다.
                Log.d("KOSMO123", "파라미터가 전달되었습니다.");
                Log.d("KOSMO123", "파라미터1=" + msgArray[1]);
                Log.d("KOSMO123", "파라미터2=" + msgArray[2]);
            }
            else {
                //만약 1이 아닌 경우 일반 알림으로 판단하여 토스트로 변환한다.
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }

            /*
            아래와 같이 처리하면 alert()창의 확인버튼을 누른것으로 간주하여
            자동으로 닫히게 된다. 아래 처리를 하지 않으면 Toast는 뜨지 않는다.
             */
            result.confirm();
            return true;
        }
    }
}