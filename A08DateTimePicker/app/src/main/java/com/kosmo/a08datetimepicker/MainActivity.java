package com.kosmo.a08datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Calendar calendar;
    TextView date_tv, time_tv;
    int yearStr, monthStr, dayStr;
    int hourStr, minuteStr, secondStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_tv = findViewById(R.id.date_tv);
        time_tv = findViewById(R.id.time_tv);

        calendar = Calendar.getInstance();

        yearStr = calendar.get(calendar.YEAR);
        monthStr = calendar.get(calendar.MONTH);
        dayStr = calendar.get(calendar.DATE);

        hourStr = calendar.get(calendar.HOUR_OF_DAY);
        minuteStr = calendar.get(calendar.MINUTE);
        secondStr = calendar.get(calendar.SECOND);

        date_tv.setText(yearStr + "년" + (monthStr+1) + "월" + dayStr + "일");
        time_tv.setText(hourStr + "시" + minuteStr + "분" + secondStr + "초");

        Button btn_datepicker = findViewById(R.id.btn_datepicker);

        btn_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(v.getContext(),
                        listener, yearStr, monthStr, dayStr);

                dialog.show();
            }
        });

        Button btn_timepicker = findViewById(R.id.btn_timepicker);
        btn_timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(v.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                time_tv.setText(String.format("설정된 시간 %n%d시 %d분",
                                        hourOfDay, minute));

                                Toast.makeText(getApplicationContext(),
                                        String.format("설정된 시간 %n%d시 %d분",
                                                hourOfDay, minute),
                                        Toast.LENGTH_LONG).show();
                            }
                        },
                        hourStr,
                        minuteStr,
                        true
                ).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener listener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    date_tv.setText(String.format("설정된 날짜 %n%d년 %d월 %d일",
                            year, (month+1), dayOfMonth));
                    Toast.makeText(getApplicationContext(),
                            year+"년"+(month+1)+"월"+dayOfMonth+"일",
                            Toast.LENGTH_LONG).show();
                }
            };
}