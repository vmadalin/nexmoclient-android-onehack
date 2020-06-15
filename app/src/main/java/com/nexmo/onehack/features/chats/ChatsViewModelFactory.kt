package com.nexmo.onehack.features.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChatsViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ChatsViewModel() as T
}
