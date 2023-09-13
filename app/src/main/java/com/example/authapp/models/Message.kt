package com.example.authapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages", foreignKeys = arrayOf(

        ForeignKey(
            entity = Chat::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("chatId"),
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("toId"),
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("fromId"),
            onDelete = ForeignKey.CASCADE
        )

    )
)

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