package com.ieseljust.pmdm.comptador


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.ieseljust.pmdm.comptador.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var comptador=0

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_main)*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)

        //Textview
        binding.textViewComptador.text = comptador.toString()



        //Callback sumar
        binding.btAdd.setOnClickListener {
            comptador++
            binding.textViewComptador.text = comptador.toString()
        }

        //Callback reset
        binding.btReset.setOnClickListener {
            comptador = 0
            binding.textViewComptador.text = comptador.toString()
        }

        //Callback resta

        binding.btResta.setOnClickListener {
                comptador--
                binding.textViewComptador.text = comptador.toString()

        }


        //Intent evento click

        binding.btNewActivity.setOnClickListener{
            val intent = Intent(baseContext, MostraComptador::class.java)
            intent.putExtra("comptador", comptador)
            startActivity(intent)
        }

        /*
        // Referencia al TextView
        val textViewContador=findViewById<TextView>(R.id.textViewComptador)

        // Inicialitzem el TextView amb el comptador a 0
        textViewContador.setText(comptador.toString())

        // Referencia al botón
        val btAdd=findViewById<Button>(R.id.btAdd)

        //Referencias resta y reset

        val btResta = findViewById<Button>(R.id.btResta)
        val btReset = findViewById<Button>(R.id.btReset)

        // Asociaciamos una expresióin lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btAdd.setOnClickListener {
            comptador++
            textViewContador.setText(comptador.toString())
        }

        //Abrir nueva actividad al pulsar open

        val btNewActivity=findViewById<Button>(R.id.btNewActivity)

        // Captura evento click para lanzar intent

        btNewActivity.setOnClickListener{
            val intent = Intent(baseContext, MostraComptador::class.java)
            intent.putExtra("comptador", comptador)
            startActivity(intent)


        }

        //Callbacks de resta y reseteo

        btResta.setOnClickListener {
            comptador--
            textViewContador.setText(comptador.toString())
        }

        btReset.setOnClickListener {
            comptador = 0
            textViewContador.setText(comptador.toString())
        }
*/
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "Al mètode onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "Al mètode onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "Al mètode onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "Al mètode onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "Al mètode onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "Al mètode onDestroy")
    }







}