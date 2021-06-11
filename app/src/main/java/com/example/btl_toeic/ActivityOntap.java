package com.example.btl_toeic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Model.TuVung;

import static com.example.btl_toeic.ActivityMuctieu.mucTieuTuMoi;
import static com.example.btl_toeic.MainActivity.dstv;

public class ActivityOntap extends AppCompatActivity {
    TextView txtTiengViet,txtTiengAnh,txtKetQua;
    Button  btnKiemTra, btnTiep;
    ImageButton btnPhatAm, btnXoa,btnSpace;
    GridView gvChuCai; // chia ra các khung
    ArrayAdapter<Character> danhsachchucai;
    ArrayList<Character> dscc;
    int i=0,demsotuontap=0; // lấy từ vựng
    String dapan;
    TuVung tv;
    ArrayList<Integer> stt; //là mảng giúp cho việc xóa ký tự

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontap);

        addControls();
        addEvents();
    }
    private void addEvents() {
        btnPhatAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(ActivityOntap.this,tv.getPhatam());
                mediaPlayer.start();
            }
        });

        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            //phat am dung sai khi bấm kiêm tra
            public void onClick(View v) {
                if (txtTiengAnh.getText().toString().equalsIgnoreCase(tv.getTiengAnh())){
                    txtKetQua.setText("Chinh Xac!");
                    MediaPlayer mediaPlayer = MediaPlayer.create(ActivityOntap.this,R.raw.dung);
                    mediaPlayer.start();
                    demsotuontap++;
                    KiemTraMucTieuOnTap();
                }else{
                    txtKetQua.setText("Sai Roi!");
                    MediaPlayer mediaPlayer = MediaPlayer.create(ActivityOntap.this,R.raw.sai);
                    mediaPlayer.start();
                }
            }
        });

        gvChuCai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //set là đẩy vào
                txtTiengAnh.setText(dapan + dscc.get(position));
                stt.add(position);// thêm vị trí ký tự chọn gần nhất
                dapan=txtTiengAnh.getText().toString();
                txtKetQua.setText("");
                gvChuCai.getChildAt(position).setBackgroundColor(Color.WHITE);
                btnSpace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dapan+=' ';
                        stt.add(-1);
                    }
                });

                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((dapan.length()-1)>=0){

                            dapan = dapan.substring(0,dapan.length()-1);
                            txtTiengAnh.setText(dapan);
                            int vt=stt.get(stt.size()-1);
                            stt.remove(stt.size()-1);
                            if(vt!=-1){
                                gvChuCai.getChildAt(vt).setBackgroundColor(View.INVISIBLE);//
                            }
                        }
                    }
                });
            }
        });

        btnTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                stt.clear();
                txtTiengAnh.setText("");
                txtKetQua.setText("");
                OnTapTuVung();
            }
        });
    }

    private void addControls() {
        txtTiengAnh = findViewById(R.id.txtTiengAnh);
        txtTiengViet=findViewById(R.id.txtTiengViet);
        txtKetQua = findViewById(R.id.txtKetQua);
        btnKiemTra=findViewById(R.id.btnKiemTra);
        btnPhatAm = findViewById(R.id.btnPhatAm);
        btnTiep=findViewById(R.id.btnTiep);
        gvChuCai = findViewById(R.id.gvChuCai);
        btnXoa= findViewById(R.id.btnXoa);
        btnSpace = findViewById(R.id.btnSpace);
        stt = new ArrayList<Integer>();
        OnTapTuVung();
    }

    private void OnTapTuVung() {
        tv = dstv.get(i);
        if (tv.isDahoc()){
            dapan="";
            String tuTiengAnh = tv.getTiengAnh();
            //dscc để lưu các ký tự sau tách
            dscc = new ArrayList<>();
            for(int j=0;j<tuTiengAnh.length();j++){
                dscc.add(tuTiengAnh.charAt(j));
            }
            Collections.shuffle(dscc);// làm xáo trộn các phần từ trong dscc
            gvChuCai.setNumColumns(5);
            gvChuCai.setHorizontalSpacing(20);
            gvChuCai.setVerticalSpacing(20);
            //grid là 1 view, adapter là đưa dữ liệu vào view
            danhsachchucai = new ArrayAdapter<Character>(ActivityOntap.this, android.R.layout.simple_list_item_1,dscc);
            gvChuCai.setAdapter(danhsachchucai);
            //lấy nghĩa từ tiếng việt
            txtTiengViet.setText(tv.getTiengViet());
        }else {
            i++;
            if(i==dstv.size()){
                KiemTraOnTapHetTuOnTap();
            }else{
                OnTapTuVung();
            }
        }
    }
    private void KiemTraMucTieuOnTap() {
        if ((demsotuontap==mucTieuTuMoi) && (demsotuontap>0)){
            Intent intent = new Intent(ActivityOntap.this,ActivityChucmung.class);
            intent.putExtra("CHUCMUNG","Chúc Mừng \n bạn đã đạt mục tiêu ôn tập lần học này!");
            startActivity(intent);
        }
    }

    private void KiemTraOnTapHetTuOnTap() {
            Intent intent = new Intent(ActivityOntap.this,ActivityChucmung.class);
            intent.putExtra("CHUCMUNG","Chúc Mừng \n bạn đã Ôn Tập hết các từ!");
            startActivity(intent);
            finish();
    }
}