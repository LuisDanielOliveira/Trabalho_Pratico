package com.example.trabalhopraticoparte3


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.trabalhopraticoparte3.adapters.NotasAdapter
import com.example.trabalhopraticoparte3.entities.NotasEntity
import com.example.trabalhopraticoparte3.viewModel.NotasViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var notasViewModel: NotasViewModel
    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotasAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true);

        // view model
        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        notasViewModel.allNotas.observe(this, Observer { titulo ->
            // Update the cached copy of the words in the adapter.
            titulo?.let { adapter.setNotas(it) }
        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNotas::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val ptitulo = data?.getStringExtra(AddNotas.EXTRA_REPLY_CITY)
            val pnotas = data?.getStringExtra(AddNotas.EXTRA_REPLY_COUNTRY)

            if (ptitulo!= null && pnotas != null) {
                val city = NotasEntity(titulo = ptitulo, notas = pnotas)
                notasViewModel.insert(city)
                Toast.makeText(this, ptitulo,Toast.LENGTH_LONG).show()
                Toast.makeText(this, pnotas,Toast.LENGTH_LONG).show()
            }

        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.apagartudo -> {
                notasViewModel.deleteAll()
                true
            }


            R.id.todasNotas -> {

                // recycler view
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                val adapter = NotasAdapter(this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                // view model
                notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
                notasViewModel.allNotas.observe(this, Observer { titulo ->
                    // Update the cached copy of the words in the adapter.
                    titulo?.let { adapter.setNotas(it) }
                })


                true
            }


            R.id.alterar -> {
                val city = NotasEntity(id = 1, titulo = "xxx", notas = "xxx")
                notasViewModel.updateNota(city)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}