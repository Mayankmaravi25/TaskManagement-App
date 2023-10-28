package com.example.taskmanagement.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.taskmanagement.R
import com.example.taskmanagement.databinding.FragmentAddtaskPopupBinding
import com.google.android.material.textfield.TextInputEditText


class AddtaskPopupFragment : DialogFragment() {
    private lateinit var binding : FragmentAddtaskPopupBinding
    private lateinit var listener :DialogNextBtnclickListener

    fun setListener(listener: DialogNextBtnclickListener)
    {
        this.listener=listener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddtaskPopupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEvents()
    }
    fun registerEvents()
    {
        binding.Savebtn.setOnClickListener {
            val todoTask = binding.taskspace.text.toString()
            if(todoTask.isNotEmpty())
            {
                listener.onsavaTask(todoTask,binding.taskspace)

            }
            else
            {
                Toast.makeText(context,"your task in empty",Toast.LENGTH_SHORT).show()
            }

        }
        binding.CloseAction.setOnClickListener {
            dismiss()
        }

    }
    interface DialogNextBtnclickListener{
        fun onsavaTask(todo : String, taskspace:TextInputEditText)
    }


}