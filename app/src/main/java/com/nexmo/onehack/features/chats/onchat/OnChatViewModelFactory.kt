package com.nexmo.onehack.features.chats.onchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OnChatViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        // TODO
        OnChatViewModel() as T
}
