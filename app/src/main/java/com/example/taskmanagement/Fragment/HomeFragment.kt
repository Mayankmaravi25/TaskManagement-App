package com.example.taskmanagement.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.taskmanagement.R
import com.example.taskmanagement.databinding.FragmentHomeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class HomeFragment : DialogFragment(), AddtaskPopupFragment.DialogNextBtnclickListener {

    private lateinit var auth : FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var navController: NavController
    private lateinit var binding : FragmentHomeBinding
    private lateinit var popupFragment: AddtaskPopupFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }
    private fun registerEvents()
    {
        binding.AddTaskbtn.setOnClickListener {
            popupFragment = AddtaskPopupFragment()
            popupFragment.setListener(this)
            popupFragment.show(
                childFragmentManager,
                "AddtodopopupFragment"
            )


        }
    }

    private fun init(view: View)
    {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Tasks").child(auth.currentUser?.uid.toString())
    }

    override fun onsavaTask(todo: String, taskspace: TextInputEditText) {
        databaseRef.push().setValue(todo).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(context,"save",Toast.LENGTH_SHORT).show()
                taskspace.text =null
            }
            else{
                Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
            }
            popupFragment.dismiss()
        }
    }


}