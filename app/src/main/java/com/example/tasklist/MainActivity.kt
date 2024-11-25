package com.example.tasklist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftar_screen)

        val btnTambah: Button = findViewById(R.id.btnTambah)

        // inisialisasi recyclerview
        recyclerView = findViewById(R.id.recyclerView)
        taskAdapter = TaskAdapter(this, taskList) { task, position ->
            val intent = Intent(this, TambahActivity::class.java)
            intent.putExtra("judul", task.judul)
            intent.putExtra("tanggal", task.tanggal)
            intent.putExtra("deskripsi", task.deskripsi)
            intent.putExtra("position", position)
            startActivityForResult(intent, REQUEST_CODE_EDIT)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        // button tambah
        btnTambah.setOnClickListener {
            val intent = Intent(this, TambahActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_TAMBAH)
        }

        // contoh data default
        addSampleData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val judul = data.getStringExtra("judul") ?: return
            val tanggal = data.getStringExtra("tanggal") ?: return
            val deskripsi = data.getStringExtra("deskripsi") ?: return
            val position = data.getIntExtra("position", -1)

            when (requestCode) {
                REQUEST_CODE_TAMBAH -> {
                    taskList.add(Task(judul, tanggal, deskripsi))
                    taskAdapter.notifyDataSetChanged()
                }
                REQUEST_CODE_EDIT -> {
                    if (position != -1) {
                        taskList[position] = Task(judul, tanggal, deskripsi, taskList[position].isCompleted)
                        taskAdapter.notifyItemChanged(position)
                    }
                }
            }
        }
    }

    private fun addSampleData() {
        taskList.add(Task("Task List", "25-11-2024", "Membuat task list"))
        taskAdapter.notifyDataSetChanged()
    }

    companion object {
        const val REQUEST_CODE_TAMBAH = 1
        const val REQUEST_CODE_EDIT = 2
    }
}
