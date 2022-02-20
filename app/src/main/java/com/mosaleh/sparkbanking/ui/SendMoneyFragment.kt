package com.mosaleh.sparkbanking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mosaleh.sparkbanking.R
import com.mosaleh.sparkbanking.databinding.FragmentSendMoneyBinding
import com.mosaleh.sparkbanking.model.Transaction
import com.mosaleh.sparkbanking.model.User
import com.mosaleh.sparkbanking.viewmodels.SendMoneyViewModel
import com.mosaleh.sparkbanking.viewmodels.UsersListViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "SendMoneyFragment"

@AndroidEntryPoint
class SendMoneyFragment : Fragment(R.layout.fragment_send_money) {

    private val viewModel: SendMoneyViewModel by viewModels()
    private val args: SendMoneyFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lateinit var users: List<User>
        val binding: FragmentSendMoneyBinding =
            FragmentSendMoneyBinding.bind(view)
        viewModel.getUsers()
        displayUserInfo(binding)
        val user = args.user
        viewModel.users.observe(viewLifecycleOwner) { _users ->
            users = _users
            val usersNames: List<String> =
                _users.filter {
                    user.AccountNumber != it.AccountNumber
                }.map {
                    it.userName
                }
            val arrayAdapter =
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    usersNames
                )
            binding.autoCompleteTV.setAdapter(arrayAdapter)
        }

        lateinit var selectedUserID: User

        binding.autoCompleteTV.onItemClickListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                selectedUserID = users[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                selectedUserID = users[position]
            }

        }
        binding.sendMoney.setOnClickListener {
            val amount = binding.amountToTransfer.text.toString().toFloat()
            user.balance = user.balance - amount
            selectedUserID.balance = selectedUserID.balance + amount
            viewModel.updateUser(user)
            viewModel.updateUser(selectedUserID)
            viewModel.addTransaction(Transaction(senderAccountNumber = user.AccountNumber, receiverAccountNumber = selectedUserID.AccountNumber))
            Toast.makeText(requireContext(),"Money sent successfully",Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                SendMoneyFragmentDirections.actionSendMoneyFragmentToUsersFragment()
            )
        }
    }

    private fun displayUserInfo(binding: FragmentSendMoneyBinding) {
        val user = args.user
        binding.tvUserNameTransaction.text = user.userName
        binding.balanceTransaction.text = user.balance.toString()
    }
}


