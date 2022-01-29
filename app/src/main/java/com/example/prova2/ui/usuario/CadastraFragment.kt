package com.example.prova2.ui.usuario

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.prova2.R
import com.example.prova2.data.db.AppDatabase
import com.example.prova2.data.db.dao.UsuarioDao
import com.example.prova2.extension.hideKeyboard
import com.example.prova2.repository.DatabaseDataSource
import com.example.prova2.repository.UsuarioRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.cadastra_fragment.*

class CadastraFragment : Fragment(R.layout.cadastra_fragment) {

    private val viewModel: CadastraViewModel by viewModels {
        object  : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: UsuarioDao =
                    AppDatabase.getInstance(requireContext()).usuarioDao

                val repository: UsuarioRepository = DatabaseDataSource(subscriberDAO)
                return CadastraViewModel(repository) as T
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.usuarioStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is CadastraViewModel.UsuarioState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        input_name.text?.clear()
        input_email.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_subscriber.setOnClickListener {
            val name = input_name.text.toString()
            val email = input_email.text.toString()

            viewModel.addUsuario(name, email)
        }
    }
}
