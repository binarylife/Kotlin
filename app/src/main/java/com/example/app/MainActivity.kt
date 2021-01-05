package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val usernameKey: String = "username"
    private val passwordKey: String = "password"

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private var et_code: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)
        et_code!!.setSelection(1)
        et_username.setText("text")
        et_password.setText("password")

        val btn_login: Button = findViewById<Button>(R.id.btn_login)
        val findViewById: Button? = delegate.findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener(this)
    }


    override fun onClick(v: View?) {

        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username: String = et_username.text.toString()
        val password: String = et_password.text.toString()
        val code: String = et_code?.text.toString()

        val user = User(username, password, code)


        if (verify(user)) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }

    }

    private fun verify(user: User): Boolean {
        if (user.username == null || user.username!!.length < 4) {
            Utils.toast("用户不合法")
            return false
        }

        if (user.password == null || user.password!!.length < 4) {
            Utils.toast("用户不合法")
            return false
        }

        return true
    }


}