package com.kosmo.a19contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //레이아웃, 이미지 등 각 위젯에 대한 전역변수 선언
    private Button btnImageScale, btnBackground;
    private ImageView imageView;
    private LinearLayout layout;
    private float rotation, scaleXY=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBackground = (Button)findViewById(R.id.btn_background);
        btnImageScale = (Button)findViewById(R.id.btn_imagescale);
        imageView = (ImageView)findViewById(R.id.imageview);
        layout = (LinearLayout)findViewById(R.id.layout);

        //각 위젯을 롱클릭했을때 컨텍스트 메뉴가 뜨도록 설정
        registerForContextMenu(btnBackground);
        registerForContextMenu(btnImageScale);
        registerForContextMenu(layout);

        //버튼에 롱클릭 이벤트 리스너 부착
        btnBackground.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                //배경색 변경 버튼을 롱클릭 할 경우 토스트를 띄워줌
                Toast toast = Toast.makeText(v.getContext(),
                        "버튼롱클릭됨",
                        Toast.LENGTH_SHORT);
                //토스트를 화면 중간에 뜨게 설정한다.
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                return false;
            }
        });
    }////onCreate메소드끝

    //컨텍스트 메뉴를 전개하기 위한 메서드 오버라이딩
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        /*
        리소스 폴더 하위에 menu폴더를 생성한 후 각 메뉴를 담당할 xml파일을 작성하고
        클릭된 메뉴에 따라 inflate(전개)한다.
         */
        MenuInflater menuInflater = getMenuInflater();
        if(v.getId()==R.id.btn_background) {
            menu.setHeaderTitle("배경색 변경하기");
            menuInflater.inflate(R.menu.menu_background, menu);
        }
        else if(v.getId()==R.id.btn_imagescale) {
            menuInflater.inflate(R.menu.menu_imagescale, menu);
        }
        else {
            menuInflater.inflate(R.menu.menu_layout, menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }////onCreateContextMenu메소드끝

    //컨텍스트 메뉴를 클릭했을때 이벤트 처리용 메서드 오버라이딩
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.item_red:
                layout.setBackgroundColor(Color.RED);
                break;

            case R.id.item_green:
                layout.setBackgroundColor(Color.GREEN);
                break;

            case R.id.item_blue:
                layout.setBackgroundColor(Color.BLUE);
                break;

            case R.id.rotate:
                if(rotation==360) rotation=0;
                rotation += 90;
                imageView.setRotation(rotation);
                break;

            case R.id.increase:
                if(scaleXY != 5) scaleXY += 2;
                imageView.setScaleX(scaleXY);
                imageView.setScaleY(scaleXY);
                break;

            case R.id.decrease:
                if(scaleXY > 1) scaleXY -= 2;
                imageView.setScaleX(scaleXY);
                imageView.setScaleY(scaleXY);
                break;

            case R.id.menu_layout:
                Toast.makeText(MainActivity.this,
                        "메인엑티비티용 컨텍스트 메뉴 눌러짐(XML)",
                        Toast.LENGTH_LONG).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}