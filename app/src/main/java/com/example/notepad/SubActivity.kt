package com.example.notepad

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notepad.databinding.ActivitySubBinding
import java.time.LocalDate

class SubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //intent로 받은 값 화면에 연결시키기
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val date = LocalDate.now().toString()
        Log.d(TAG, "오늘 날짜 : $date")

        binding.tvTitle.text = title
        binding.tvContent.text = content
        binding.tvDate.text = "작성 날짜 : $date"


        //재입력 버튼 누르면 activity 삭제시키고 mainactivity 실행시키기.
        binding.btnReset.setOnClickListener {
            val sintent = Intent(this@SubActivity, MainActivity::class.java)
            startActivity(sintent)
            finish()
        }

    }
}