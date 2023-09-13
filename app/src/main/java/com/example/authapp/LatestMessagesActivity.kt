package com.example.authapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authapp.database.MainDb
import com.example.authapp.databinding.ActivityLatestMessagesBinding
import com.example.authapp.models.User

class LatestMessagesActivity : AppCompatActivity() {

    lateinit var binding: ActivityLatestMessagesBinding
    lateinit var adapter: UserAdapter  //перемернная для записи адаптера

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatestMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDb.getDb(this)

        adapter = UserAdapter()
        binding.rcviewLatestMessages.layoutManager = LinearLayoutManager(this@LatestMessagesActivity)
        binding.rcviewLatestMessages.adapter = adapter

        db.getDao().getAllItems().asLiveData().observe(this@LatestMessagesActivity) { list ->
            list.forEach {
                val user1 = User(it.id, it.login, it.email, it.password)
                adapter.addUser(user1)
            }
        }
    }

    /*private fun initRcView() = with(binding) {

        adapter = UserAdapter()
        rcviewLatestMessages.layoutManager = LinearLayoutManager(this@LatestMessagesActivity)
        rcviewLatestMessages.adapter = adapter
        btnNewChat.setOnClickListener {

            val user = TestClass(index, "testLogin", "testEmail@test.com", "testPassword")
            adapter.addUser(user)
            index++
        }
    }*/
}