package com.example.tasklist

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val context: Context,
    private val taskList: MutableList<Task>,
    private val onEdit: (Task, Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tv_judul)
        val tvTanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnStartSelesai: Button = itemView.findViewById(R.id.btnStartSelesai)
        val cardView: CardView = itemView.findViewById(R.id.cardView) // CardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]

        // set data ke textview
        holder.tvJudul.text = task.judul
        holder.tvTanggal.text = task.tanggal
        holder.tvDeskripsi.text = task.deskripsi

        // atur warna latar belakang berdasarkan status
        if (task.isCompleted) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#A5D6A7"))
            holder.btnStartSelesai.text = "Selesai"
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFCCBC"))
            holder.btnStartSelesai.text = "Start"
        }

        // button hapus
        holder.btnHapus.setOnClickListener {
            taskList.removeAt(position)
            notifyItemRemoved(position)
        }

        // button edit
        holder.btnEdit.setOnClickListener {
            onEdit(task, position)
        }

        // button start/selesai
        holder.btnStartSelesai.setOnClickListener {
            task.isCompleted = !task.isCompleted
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = taskList.size
}
