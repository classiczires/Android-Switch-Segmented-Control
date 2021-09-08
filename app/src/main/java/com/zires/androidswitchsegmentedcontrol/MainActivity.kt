package com.zires.androidswitchsegmentedcontrol

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zires.switchsegmentedcontrol.ZiresSwitchSegmentedControl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val switchSegmentedControl: ZiresSwitchSegmentedControl = findViewById(R.id.zires_switch)
        switchSegmentedControl.setOnToggleSwitchChangeListener(object :
            ZiresSwitchSegmentedControl.OnSwitchChangeListener {
            override fun onToggleSwitchChangeListener(isChecked: Boolean) {
                Toast.makeText(this@MainActivity, "Is checked: $isChecked", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}