package com.example.notepad

import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import com.example.notepad.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var edtTitle : Editable
    private lateinit var edtContent : Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //intent를 이용해서 edt의 입력값들 subActivity로 넘기고 화면 전환 후 activity 삭제하기
        binding.btnSubmit.setOnClickListener {
            edtTitle = binding.edtTitle.text
            edtContent = binding.edtContent.text

            val note_title = edtTitle.toString()
            val note_content = edtContent.toString()

            val intent = Intent(this@MainActivity, SubActivity::class.java)
            //bundle 이용해서 보내기
            var bundle = bundleOf(
                "title" to note_title,
                "content" to note_content
            )
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        // onPause에서 저장한 내용 노출(dialog 설정에 따라서)
    }

    override fun onPause() {
        super.onPause()
        // edt에 쓰여진 내용 저장
        edtTitle = binding.edtTitle.text
        edtContent = binding.edtContent.text
        Log.d(TAG, "onPause : 제목 : ${edtTitle}, 내용 : ${edtContent}")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop : 제목 : ${edtTitle}, 내용 : ${edtContent}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestory : 제목 : ${edtTitle}, 내용 : ${edtContent}")
    }

    override fun onRestart() {
        super.onRestart()
        //계속 작성할 거랴고 dialog로 묻기
        Log.d(TAG, "onRestart : 제목 : ${edtTitle}, 내용 : ${edtContent}")
        val dialogBuilder = AlertDialog.Builder(this@MainActivity)
        dialogBuilder.setTitle("warning")
        dialogBuilder.setMessage("[삭제]을 누르면 작성 중인 내용이 삭제됩니다.")
        dialogBuilder.setPositiveButton("삭제") {
                dialogInterface: DialogInterface, i : Int ->
            binding.edtTitle.text = null
            binding.edtContent.text = null
        }
        dialogBuilder.setNegativeButton("계속 작성") {
            DialogInterface: DialogInterface, i ->
            binding.edtTitle.text = edtTitle
            binding.edtContent.text = edtContent
        }
        dialogBuilder.show()

    }
}