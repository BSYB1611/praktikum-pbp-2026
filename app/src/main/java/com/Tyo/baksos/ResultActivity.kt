package com.Tyo.baksos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        // Menangkap data String dan Integer dari MainActivity
        val nama = intent.getStringExtra("EXTRA_NAMA")
        val usia = intent.getIntExtra("EXTRA_USIA", 0)

        // Menampilkan sapaan: "Halo [Nama], usia Anda [Umur] tahun. Pendaftaran berhasil!"
        tvResult.text = getString(R.string.result_greeting, nama, usia)

        // Tombol Toast + kembali ke menu utama (finish)
        btnKembali.setOnClickListener {
            Toast.makeText(this, "Kembali ke halaman pendaftaran bakti sosial", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}