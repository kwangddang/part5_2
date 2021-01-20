package com.example.myapplication

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*


class Lab5_2Activity : AppCompatActivity(), View.OnClickListener {

    val alertBtn=findViewById(R.id.btn_alert) as Button
    val listBtn=findViewById(R.id.btn_list) as Button
    val progressBtn=findViewById(R.id.btn_progress) as Button
    val dateBtn=findViewById(R.id.btn_date) as Button
    val timeBtn=findViewById(R.id.btn_time) as Button
    val customDialogBtn=findViewById(R.id.btn_custom) as Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab5_2)

        alertBtn.setOnClickListener(this)
        listBtn.setOnClickListener(this)
        progressBtn.setOnClickListener(this)
        dateBtn.setOnClickListener(this)
        timeBtn.setOnClickListener(this)
        customDialogBtn.setOnClickListener(this)

    }

    private fun showToast(message:String){
        val toast = Toast.makeText(this,message,Toast.LENGTH_SHORT)
        toast.show()
    }

    val dialogListener = object:DialogInterface.OnClickListener{
        override fun onClick(dialog:DialogInterface, which:Int){
            if(dialog==customDialog && which=DialogInterface.BUTTON_POSITIVE){
                showToast("custom dialog 확인 click.....")
            }
            else if(dialog==listDialog){
                val datas=getResources().getStringArray(R.array.dialog_array)
                showToast("${datas[which]} 선택 하셨습니다.")
            }
            else if(dialog==alertDialog&&which==DialogInterface.BUTTON_POSITIVE){
                showToast("alert dialog ok click.....")
            }

        }
    }

    override fun onClick(v: View) {
        if (v == alertBtn) {
            val builder = AlertDialog.Builder(this)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setTitle("알림")
            builder.setMessage("정말 종료 하시겠습니까?")
            builder.setPositiveButton("OK", dialogListener)
            builder.setNegativeButton("NO", null);

            alertDialog = builder.create()
            alertDialog.show()
        } else if (v == listBtn) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("알람 벨소리")
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener)

            builder.setPositiveButton("확인", null)
            builder.setNegativeButton("취소", null)
            listDialog = builder.create()
            listDialog.show()
        } else if (v == progressBtn) {
            val progressDialog = ProgressDialog(this)
            progressDialog.setIcon(android.R.drawable.ic_dialog_alert)
            progressDialog.setTitle("Wait...")
            progressDialog.setMessage("서버 연동중입니다. 잠시만 기다리세요.")

            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressDialog.setIndeterminate(true)

            progressDialog.show()
        } else if (v == dateBtn) {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dateDialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker,
                        year: Int,
                        monthOfYear: Int,
                        dayOfMonth: Int
                    ) {
                        showToast("$year : ${monthOfYear + 1} : $dayOfMonth")
                    }
                }, year, month, day)
            dateDialog.show()
        }
        else if(v==timeBtn){
            val c=Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute=c.get(Calendar.MINUTE)

            val timeDialog=TimePickerDialog(this, object:TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker, hourOfDay:Int, minute:Int){
                    showToast("$hourOfDay : $minute")
                }
            },hour,minute,false)
            timeDialog.show()
        }
        else if(v==customDialogBtn){
            val builder =AlertDialog.Builder(this)
            val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view:View=inflater.inflate(R.layout.dialog_layout,null)
            builder.setView(view)

            builder.setPositiveButton("확인",dialogListener)
            builder.setNegativeButton("취소",null)

            customDialog=bulder.create()
            customDialog.show()
        }
    }

}
