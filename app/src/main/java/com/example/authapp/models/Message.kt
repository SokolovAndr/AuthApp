package com.example.authapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "textMessage")
    var textMessage: String,

    @ColumnInfo(name = "timeMessage")
    var timeMessage: Long,

    @ColumnInfo(name = "chatId")
    var chatId: Chat? = null,

    @ColumnInfo(name = "toId")
    var toId: Int? = null,

    @ColumnInfo(name = "fromId")
    var fromId: Int? = null
)