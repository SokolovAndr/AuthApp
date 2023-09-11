package com.example.authapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.example.authapp.database.MainDb
import com.example.authapp.databinding.ActivityMainBinding
import com.example.authapp.models.User

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDb.getDb(this)

        db.getDao().getAllItems().asLiveData().observe(this) {list ->
            binding.tvList.text = ""
            list.forEach{
                val text = "Id: ${it.id} Login: ${it.login} Email: ${it.email} Password: ${it.password}\n"
                binding.tvList.append(text)
            }
        }

        binding.buttonReg.setOnClickListener {

            if(binding.EditText1.text.toString().isEmpty() || binding.EditText2.text.toString().isEmpty() || binding.EditText3.text.toString().isEmpty())
            {
                var toast = Toast.makeText(applicationContext,"Заполните все поля", Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                if(binding.EditText2.text.toString().contains("@") != true){
                    var toast = Toast.makeText(applicationContext,"Введите почту", Toast.LENGTH_SHORT)
                    toast.show()
                }
                else {
                    val user = User(null,
                        binding.EditText1.text.toString(),
                        binding.EditText2.text.toString(),
                        binding.EditText3.text.toString()
                    )
                    Thread{
                        db.getDao().insertItem(user)
                    }.start()

                    var toast = Toast.makeText(applicationContext, "Добро пожаловать, ${binding.EditText1.text.toString()} !", Toast.LENGTH_SHORT)
                    toast.show()

                    binding.EditText1.text.clear()
                    binding.EditText2.text.clear()
                    binding.EditText3.text.clear()

                    val myIntent = Intent(this,LatestMessagesActivity::class.java).apply {  }
                    startActivity(myIntent)
                }
            }
        }
    }

    fun Enter(view: View) {
        val myIntent = Intent(this, LoginActivity::class.java).apply {}
        startActivity(myIntent)
    }


}