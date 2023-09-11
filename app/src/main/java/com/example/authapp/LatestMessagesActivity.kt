package com.example.authapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authapp.database.MainDb
import com.example.authapp.databinding.ActivityLatestMessagesBinding
import com.example.authapp.models.User

class LatestMessagesActivity : AppCompatActivity() {

    lateinit var binding: ActivityLatestMessagesBinding
    lateinit var adapter: UserAdapter  //перемернная для записи адаптера

    private var index = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatestMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRcView()

    }

    private fun initRcView() = with(binding) {
        adapter = UserAdapter()
        rcviewLatestMessages.layoutManager = LinearLayoutManager(this@LatestMessagesActivity)
        rcviewLatestMessages.adapter = adapter
        btnNewChat.setOnClickListener{
            val user = User (index, "testLogin", "testEmail@test.com", "testPassword")
            adapter.addUser(user)
            index++

        }

    }

}