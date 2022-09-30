package com.example.database.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.database.R
import com.example.database.appDatabase
import com.example.database.databinding.FragmentAllPlayersBinding

class AllPlayersFragment : Fragment() {

    private var _binding: FragmentAllPlayersBinding? = null
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

    private val adapter = PlayerAdapter { item ->
        val myDialogFragment = DeleteDialogFragment(playerDao, item)
        val manager = parentFragmentManager
        val transaction = manager.beginTransaction()
        myDialogFragment.show(transaction, null)
        updatePlayersList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentAllPlayersBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updatePlayersList()

        val players = playerDao.getAll()

        with(binding) {

            recyclerView.adapter = adapter

            toolbar
                .menu
                .findItem(R.id.action_search)
                .actionView
                .let { it as SearchView }
                .setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(query: String): Boolean {
                        val filteredPlayers = players
                            .filter {
                                it.firstName.contains(query) || it.lastName.contains(query)
                            }
                        adapter.submitList(filteredPlayers)
                        return true
                    }

                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updatePlayersList() {
        viewModel.liveData.observe(viewLifecycleOwner) { players ->
            adapter.submitList(players)
        }
    }
}