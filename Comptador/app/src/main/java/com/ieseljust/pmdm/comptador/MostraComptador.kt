package com.ieseljust.pmdm.comptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MostraComptador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostra_comptador)

        val comptador:Int? = intent.getIntExtra("comptador", 0)

        val comptadorTextView=findViewById<TextView>(R.id.textViewMostraComptador)
        comptadorTextView.setText(comptador.toString())


        val btClose=findViewById<Button>(R.id.btClose)
        btClose.setOnClickListener{
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MostraComptador", "Al mètode onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MostraComptador", "Al mètode onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MostraComptador", "Al mètode onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MostraComptador", "Al mètode onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MostraComptador", "Al mètode onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MostraComptador", "Al mètode onDestroy")
    }
}