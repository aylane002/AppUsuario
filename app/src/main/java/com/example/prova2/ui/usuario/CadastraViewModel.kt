package com.example.prova2.ui.usuario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prova2.R
import com.example.prova2.repository.UsuarioRepository
import kotlinx.coroutines.launch

class CadastraViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {
    private val _usuarioStateEventData = MutableLiveData<UsuarioState>()
    val usuarioStateEventData: LiveData<UsuarioState>
        get() = _usuarioStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addUsuario(name: String, email: String) = viewModelScope.launch{
        try {
            val id = repository.insertUsuario(name, email)
            if (id > 0) {

              _usuarioStateEventData.value = UsuarioState.Inserted
                _messageEventData.value = R.string.cadastra_inserted_successfully
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.cadastra_error_to_insert
            Log.e(TAG, ex.toString())
        }
    }
    sealed class UsuarioState{
        object Inserted : UsuarioState()
    }

    companion object {
        private val TAG = CadastraViewModel::class.java.simpleName
    }
}