package com.example.authapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.authapp.database.MainDb
import com.example.authapp.databinding.ActivityLoginBinding
import com.example.authapp.models.User

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDb.getDb(this)

        db.getDao().getAllItems().asLiveData().observe(this) { list ->
            binding.tvList.text = ""
            list.forEach {
                val text =
                    "Id: ${it.id} Login: ${it.login} Email: ${it.email} Password: ${it.password}\n"
                binding.tvList.append(text)
            }
        }

        binding.buttonAuth.setOnClickListener {
            if (binding.EditText1.text.toString().isEmpty() || binding.EditText3.text.toString().isEmpty()) {
                var toast = Toast.makeText(applicationContext, "Заполните все поля", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                var item1 = binding.EditText1.text.toString()
                var item3 = binding.EditText3.text.toString()

                var user: LiveData<List<User>>? = db.getDao().isExists(item1, item3)
                user?.observe(this) { list ->
                    ListCallBack(list)
                }
            }
        }
    }

    fun ListCallBack(userList: List<User>) {
        if (userList.count() == 0) {
            var toast = Toast.makeText(applicationContext, "Пользователь не найден", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            var toast = Toast.makeText(applicationContext, "Добро пожаловать, ${binding.EditText1.text.toString()} !", Toast.LENGTH_SHORT)
            toast.show()

            val myIntent = Intent(this, LatestMessagesActivity::class.java).apply { }
            startActivity(myIntent)
        }

        binding.EditText1.text.clear()
        binding.EditText3.text.clear()
    }

    fun backToRegistr(view: View) {
        val myIntent = Intent(this, MainActivity::class.java).apply { }
        startActivity(myIntent)
    }
}