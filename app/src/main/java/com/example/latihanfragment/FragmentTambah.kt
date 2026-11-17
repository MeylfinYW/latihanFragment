package com.example.latihanfragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentTambah : Fragment() {

    companion object {
        val historyList = ArrayList<String>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tambah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutRoot = view.findViewById<LinearLayout>(R.id.layoutRoot)
        val tvHistory = view.findViewById<TextView>(R.id.tvHistory)

        arguments?.let {
            val teksBaru = it.getString("RIWAYAT")
            val hasil = it.getDouble("HASIL", 0.0)

            if (teksBaru != null) {
                historyList.add(teksBaru)
            }

            if (hasil < 0) {
                layoutRoot.setBackgroundColor(Color.RED)
            }
        }

        if (historyList.isNotEmpty()) {
            tvHistory.text = historyList.joinToString("\n")
        }
    }
}