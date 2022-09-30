package com.example.database.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.database.appDatabase
import com.example.database.databinding.FragmentAddPlayerBinding
import com.example.database.getTextOrSetError

class AddPlayerFragment : Fragment() {

    private var _binding: FragmentAddPlayerBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val playerDao by lazy {
        requireContext().appDatabase.playerDao()
    }

    private val viewModel: PlayerViewModel by viewModels(
        factoryProducer = {
            viewModelFactory {
                initializer {
                    PlayerViewModel(playerDao)
                }
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentAddPlayerBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clearErrors()

        with(binding) {
            buttonAdd.setOnClickListener {
                val firstName = containerFirstName.getTextOrSetError()
                val lastName = containerLastName.getTextOrSetError()
                val nationality = containerNationality.getTextOrSetError()
                if (firstName == null || lastName == null || nationality == null) return@setOnClickListener
                /*val newPlayer =
                    Player(firstName = firstName, lastName = lastName, nationality = nationality)*/
                /*playerDao.insertAll(newPlayer)*/
                viewModel.onButtonClicked(firstName, lastName, nationality)
                Toast.makeText(requireContext(), TOAST_ADD_TEXT, Toast.LENGTH_SHORT).show()
                clearErrors()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clearErrors() {
        with(binding) {
            containerFirstName.error = null
            containerLastName.error = null
            containerNationality.error = null
        }
    }

    companion object {
        private const val TOAST_ADD_TEXT = "Added successfully"
    }
}