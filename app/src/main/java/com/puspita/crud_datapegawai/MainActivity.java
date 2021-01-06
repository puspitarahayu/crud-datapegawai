package com.puspita.crud_datapegawai;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText editNama, editNotelp, editAlamat, editId;
    Button btnSimpan;
    Button btnLihat;
    Button btnUpdate;
    Button btnHapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHelper(this);
        editNama = findViewById(R.id.editText_nama);
        editNotelp = findViewById(R.id.editText_nomor);
        editAlamat = findViewById(R.id.editText_alamat);
        editId = findViewById(R.id.editText_id);
        btnSimpan = findViewById(R.id.button_simpan);
        btnLihat = findViewById(R.id.button_lihat);
        btnUpdate = findViewById(R.id.button_update);
        btnHapus = findViewById(R.id.button_hapus);

        Simpan();
        Lihat_data();
        Update();
        Hapus();
    }

    private void Simpan() {
        btnSimpan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                editNama.getText().toString(),
                                editNotelp.getText().toString(),
                                editAlamat.getText().toString());

                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Berhasil simpan", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Gagal Simpan", Toast.LENGTH_LONG).show();
                    }

                }

        );

    }

    private void Lihat_data() {
        btnLihat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Data Tidak ditemukan");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Nama :" + res.getString(1) + "\n");
                            buffer.append("No telp :" + res.getString(2) + "\n");
                            buffer.append("Alamat :" + res.getString(3) + "\n\n");
                        }

                        showMessage("Data", buffer.toString());
                    }

                }

        );
    }

    public void Update() {

        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editId.getText().toString(),
                                editNama.getText().toString(),
                                editNotelp.getText().toString(),
                                editAlamat.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Berhasil Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Gagal Update", Toast.LENGTH_LONG).show();
                    }

                }

        );

    }

    public void Hapus() {

        btnHapus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Berhasil Hapus", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Gagal Hapus", Toast.LENGTH_LONG).show();
                    }

                }

        );

    }


    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}