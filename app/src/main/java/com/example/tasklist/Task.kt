package com.example.tasklist

data class Task(
    val judul: String,
    val tanggal: String,
    val deskripsi: String,
    var isCompleted: Boolean = false
)
