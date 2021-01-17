package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /*val vibrationBtn:Button=findViewById(R.id.btn_vibration)
       val systemBeepBtn:Button=findViewById(R.id.btn_system_beep)
       val customBeepBtn:Button=findViewById(R.id.btn_custom_sound)

        vibrationBtn.setOnClickListener(ButtonListener())
        systemBeepBtn.setOnClickListener(ButtonListener())
        customBeepBtn.setOnClickListener(ButtonListener())*/
        btn_vibration.setOnClickListener(ButtonListener())
        btn_system_beep.setOnClickListener(ButtonListener())
        btn_custom_sound.setOnClickListener(ButtonListener())
    }

    inner class ButtonListener : View.OnClickListener{
        override fun onClick(v: View?){
            if(v==btn_vibration){
                val vib = getSystemService(VIBRATOR_SERVICE) as Vibrator
                vib.vibrate(1000)
            }
            else if(v==btn_system_beep){
                val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val ringtone=RingtoneManager.getRingtone(getApplicationContext(),notification)
                ringtone.play()
            }
            else if(v== btn_custom_sound){
                val player = MediaPlayer.create(this,R.raw.fallbackring)
                player.start()
            }
        }
    }
}
