package com.example.authapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class Chat(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "messages")
    var messages: List<Message>,

    @ColumnInfo(name = "users")
    var users: List<User>
)