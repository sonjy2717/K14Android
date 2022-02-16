package com.kosmo.a33xmljsonparser02;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
코드의 확장성을 위해 내부클래스가 아닌 별도의 클래스로 정의한다.
커스텀 어댑터 객체는 BaseAdapter 클래스를 상속하여 정의한다.
 */
public class ActorAdapter extends BaseAdapter {

    //멤버변수 : 컨텍스트, 어댑터의 데이터, 커스텀 레이아웃의 리소스 아이디
    private Context context;
    private List<ActorVO> items;
    private int layoutResId;

    /*
    생성자
        컨텍스트 : 메인 액티비티에서 사용할 것임
        데이터 : JSON을 파싱한 후 데이터를 삽입할 것임
        리소스 아이디 : 커스텀 XML 레이아웃 설정(리스트뷰의 부분 레이아웃)
     */
    public ActorAdapter(Context context, List<ActorVO> items, int layoutResId) {
        this.context = context;
        this.items = items;
        this.layoutResId = layoutResId;
    }

    //커스텀 어댑터 객체에서 기본적으로 오버라이딩 하는 메서드
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //하나의 항목을 표현하는 메서드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //생성된 커스텀 레이아웃이 없을때 inflate()한다.
        if (convertView == null) {
            convertView = View.inflate(context, layoutResId, null);
        }

        //XML레이아웃을 inflate한 후 각 위젯을 가져온다.
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvAge = convertView.findViewById(R.id.tv_age);
        TextView tvHobbys = convertView.findViewById(R.id.tv_hobbys);
        TextView tvLogin = convertView.findViewById(R.id.tv_login);
        ImageView profileImg = convertView.findViewById(R.id.imageView);

        //값을 위젯에 설정한다. 데이터는 List컬렉션이므로 get(인덱스) 형태로 값을 출력한다.
        tvName.setText(items.get(position).getName());
        tvAge.setText(items.get(position).getAge());
        tvHobbys.setText(items.get(position).getHobbys());
        tvLogin.setText(items.get(position).getLogin());
        profileImg.setImageResource(items.get(position).getProfileImg());

        //리스트뷰에 스트라이프 효과를 주기 위해 배경색을 설정한다.
        if (position % 2 == 0) {
            convertView.setBackgroundColor(0x99dadada);
        }
        else {
            convertView.setBackgroundColor(0x99ffffff);
        }

        return convertView;
    }
}
