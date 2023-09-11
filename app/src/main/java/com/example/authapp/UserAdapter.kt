package com.example.authapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.authapp.database.MainDb
import com.example.authapp.databinding.ListUsersBinding
import com.example.authapp.models.TestClass
import com.example.authapp.models.User



class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>(){
    val userList = ArrayList<User>()



    class UserHolder(item : View): RecyclerView.ViewHolder(item){
        val binding = ListUsersBinding.bind(item)
        fun bind (user: User) = with(binding){
            myTextView1.text = user.id.toString()
            myTextView2.text = user.login
            myTextView3.text = user.email
            myTextView4.text = user.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_users, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun addUser(user: User){
        userList.add(user)
        notifyDataSetChanged()
    }
}

