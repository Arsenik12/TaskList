package com.example.tasklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class TambahActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_screen)

        val inputTask: EditText = findViewById(R.id.input_task)
        val inputTanggal: EditText = findViewById(R.id.input_tanggal)
        val inputDeskripsi: EditText = findViewById(R.id.input_deskripsi)
        val btnSimpan: Button = findViewById(R.id.btnSimpan)

        // get atau ambil data untuk mengedit
        val judul = intent.getStringExtra("judul")
        val tanggal = intent.getStringExtra("tanggal")
        val deskripsi = intent.getStringExtra("deskripsi")
        val position = intent.getIntExtra("position", -1)

        if (judul != null && tanggal != null && deskripsi != null) {
            inputTask.setText(judul)
            inputTanggal.setText(tanggal)
            inputDeskripsi.setText(deskripsi)
        }

        // button simpan
        btnSimpan.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("judul", inputTask.text.toString())
            resultIntent.putExtra("tanggal", inputTanggal.text.toString())
            resultIntent.putExtra("deskripsi", inputDeskripsi.text.toString())
            resultIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
