package com.example.vieweventdisturbution

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnJumpToCustomChildrenOrderPage)
                .setOnClickListener {
                    startActivity(Intent(this, CustomGetChildrenOrderActivity::class.java))
                }
        /*   btnJumpToCustomChildrenOrderPage.setOnClickListener {
               startActivity(Intent(this, CustomGetChildrenOrderActivity::class.java))
           }*/
    }

}
