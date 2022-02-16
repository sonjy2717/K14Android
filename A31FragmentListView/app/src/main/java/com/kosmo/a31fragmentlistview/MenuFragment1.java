package com.kosmo.a31fragmentlistview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuFragment1 extends Fragment {

    public static final String TAG = "iKosmo";

    String[] idolGroup = {"엑소", "방탄소년단", "워너원", "뉴이스트", "갓세븐",
    "비투비", "빅스"};
    int[] teamCount = {9, 7, 11, 5, 7, 7, 6};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "MenuFragment1 > onCreateView()");

        //View viewGroup = inflater.inflate(R.layout.fragment_menu1, container, false);
        /*
        프레그먼트에서 리스트뷰를 사용할때는 View객체가 아닌 ViewGroup객체를 사용한다.
        어댑터 객체생성 및 연결하는 부분은 기존과 동일하다
        */
        ViewGroup viewGroup =
                (ViewGroup)inflater.inflate(R.layout.fragment_menu1,container, false);
        //뷰그룹을 통해 리스트뷰를 얻어온다.
        ListView listView =
                (ListView)viewGroup.findViewById(R.id.listView1);
        //어댑터 객체 생성 후 둘을 연결한다.
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        //리스너 연결
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "인덱스 : " + position);
                Toast.makeText(getContext(),
                        idolGroup[position] + " 선택됨",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return viewGroup;
    }

    //커스텀 어댑터
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return idolGroup.length;
        }

        @Override
        public Object getItem(int position) {
            return idolGroup[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //하나의 항목을 생성한 후 setter()를 통해 초기화 및 반환
            IdolView idolView = new IdolView(getContext());

            idolView.setName(idolGroup[position]);
            idolView.setPerson(new Integer(teamCount[position]).toString());

            return idolView;
        }
    }
}