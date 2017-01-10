package baishuai.github.io.keddit.feature.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import baishuai.github.io.keddit.feature.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(baseContext, MainActivity::class.java))
        finish()
    }
}
