package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.TuVung;

import static com.example.btl_toeic.MainActivity.dstv;

public class ActivityTratu extends AppCompatActivity {
    ImageButton btnPhatAmKetQuaTraTu,btnTraTu;
    AutoCompleteTextView autotxtTraTu;
    ImageView imgKetQuaTraTu;
    TextView txtKetQuaTraTu;
    ArrayList<String> dst;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratu);
        //xử lý cài đặt
        addControls();
        // xử lý sự kiện
        addEvents();
    }
    // xử lý cài đặt
    private void addControls() {
        btnPhatAmKetQuaTraTu = findViewById(R.id.btnPhatAmKetQuaTraTu);
        btnTraTu = findViewById(R.id.btnTraTu);
        autotxtTraTu = findViewById(R.id.autotxtTraTu);
        imgKetQuaTraTu = findViewById(R.id.imgKetQuaTraTu);
        txtKetQuaTraTu = findViewById(R.id.txtKetQuaTraTu);
        //tạo 1 list
        dst = new ArrayList<>();
        //lây data
        LayDanhSachTu();
        //nhet vao adapter
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dst);
        autotxtTraTu.setAdapter(arrayAdapter);
        //khi nhap tu thi goi y
        autotxtTraTu.setThreshold(1);
    }
    //data
    private void LayDanhSachTu() {
        for (TuVung tv:dstv){
            dst.add(tv.getTiengAnh());
        }
    }
    // xử lý sự kiện bấm nút tìm kiếm
    private void addEvents() {
        btnTraTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(TuVung tv:dstv){
                    // so sanh chuoi chỉ trả về True hoặc False
                    if (tv.getTiengAnh().equalsIgnoreCase(autotxtTraTu.getText().toString())){
                        txtKetQuaTraTu.setText(tv.getTiengViet()+"\n\n"+tv.getMota());
                        imgKetQuaTraTu.setImageResource(tv.getImgmota());
                        btnPhatAmKetQuaTraTu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            //hàm phát âm
                            public void onClick(View v) {
                                MediaPlayer mediaPlayer = MediaPlayer.create(ActivityTratu.this,tv.getPhatam());
                                mediaPlayer.start();
                            }
                        });
                        break;
                    }
                }
            }
        });
    }
}