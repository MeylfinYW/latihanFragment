package com.example.latihanfragment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etAngka1 = findViewById<EditText>(R.id.etAngka1)
        val etAngka2 = findViewById<EditText>(R.id.etAngka2)

        val btnKali = findViewById<Button>(R.id.btnKali)
        val btnBagi = findViewById<Button>(R.id.btnBagi)
        val btnTambah = findViewById<Button>(R.id.btnTambah)
        val btnKurang = findViewById<Button>(R.id.btnKurang)

        btnKali.setOnClickListener {
            prosesHitung(etAngka1, etAngka2, "*")
        }

        btnBagi.setOnClickListener {
            prosesHitung(etAngka1, etAngka2, "/")
        }

        btnTambah.setOnClickListener {
            prosesHitung(etAngka1, etAngka2, "+")
        }

        btnKurang.setOnClickListener {
            prosesHitung(etAngka1, etAngka2, "-")
        }
    }

    private fun prosesHitung(et1: EditText, et2: EditText, operator: String) {
        val str1 = et1.text.toString()
        val str2 = et2.text.toString()

        if (str1.isEmpty() || str2.isEmpty()) {
            Toast.makeText(this, "Kedua angka harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        val angka1 = str1.toDouble()
        val angka2 = str2.toDouble()

        if (operator == "/" && angka2 == 0.0) {
            Toast.makeText(this, "Tidak bisa membagi dengan 0!", Toast.LENGTH_SHORT).show()
            return
        }

        val hasil = when (operator) {
            "*" -> angka1 * angka2
            "/" -> angka1 / angka2
            "+" -> angka1 + angka2
            "-" -> angka1 - angka2
            else -> 0.0
        }

        val formatAngka1 = if (angka1 % 1 == 0.0) angka1.toInt().toString() else angka1.toString()
        val formatAngka2 = if (angka2 % 1 == 0.0) angka2.toInt().toString() else angka2.toString()
        val formatHasil = if (hasil % 1 == 0.0) hasil.toInt().toString() else String.format("%.2f", hasil)

        val teksRiwayat = "$formatAngka1 $operator $formatAngka2 = $formatHasil"

        val bundle = Bundle().apply {
            putString("RIWAYAT", teksRiwayat)
            putDouble("HASIL", hasil)
        }

        val targetFragment: Fragment = when (operator) {
            "*" -> FragmentKali()
            "/" -> FragmentBagi()
            "+" -> FragmentTambah()
            "-" -> FragmentKurang()
            else -> FragmentTambah()
        }

        targetFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, targetFragment)
            .commit()
    }
}