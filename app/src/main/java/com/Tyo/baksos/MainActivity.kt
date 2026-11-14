package com.Tyo.baksos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etUsia = findViewById<EditText>(R.id.etUsia)
        val btnForm = findViewById<Button>(R.id.btnForm)
        val btnBrowser = findViewById<Button>(R.id.btnBrowser)

        // Tombol "Kirim Data ke Form": Validasi + Explicit Intent (String & Integer)
        btnForm.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val usiaText = etUsia.text.toString().trim()

            // 1. Validasi kolom kosong
            var isEmpty = false
            if (nama.isEmpty()) {
                etNama.error = "Nama lengkap wajib diisi!"
                isEmpty = true
            }
            if (usiaText.isEmpty()) {
                etUsia.error = "Usia wajib diisi!"
                isEmpty = true
            }
            if (isEmpty) {
                Toast.makeText(
                    this,
                    "Harap isi nama lengkap dan usia terlebih dahulu!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // 2. Tantangan Tambahan: Validasi angka valid & tidak minus
            val usia = usiaText.toIntOrNull()
            if (usia == null) {
                etUsia.error = "Usia harus berupa angka valid!"
                Toast.makeText(this, "Format usia tidak valid!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (usia < 0) {
                etUsia.error = "Usia tidak boleh bernilai minus!"
                Toast.makeText(this, "Usia tidak boleh negatif!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Kirim data String dan Integer via Explicit Intent ke ResultActivity
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("EXTRA_NAMA", nama)
            intent.putExtra("EXTRA_USIA", usia)
            startActivity(intent)
        }

        // Tombol "Buka Info Event": Implicit Intent ke link informasi kegiatan bakti sosial
        btnBrowser.setOnClickListener {
            val urlInfoEvent = "https://www.instagram.com/informatika_akprind/"
            val intent = Intent(Intent.ACTION_VIEW, urlInfoEvent.toUri())
            startActivity(intent)
        }
    }
}