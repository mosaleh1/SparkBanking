package com.mosaleh.sparkbanking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosaleh.sparkbanking.R
import com.mosaleh.sparkbanking.databinding.FragmentUsersBinding
import com.mosaleh.sparkbanking.model.User
import com.mosaleh.sparkbanking.ui.adapter.UserAdapter
import com.mosaleh.sparkbanking.viewmodels.UsersListViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "UsersFragment"

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users), UserAdapter.Interaction {

    private val viewModel: UsersListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentUsersBinding = FragmentUsersBinding.bind(view)

        binding.RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = UserAdapter(this)
        binding.RecyclerView.adapter = adapter
        viewModel.getUsers()
        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onItemSelected(position: Int, item: User) {
        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            UsersFragmentDirections.actionUsersFragmentToSendMoneyFragment3(item)
        )
    }
}