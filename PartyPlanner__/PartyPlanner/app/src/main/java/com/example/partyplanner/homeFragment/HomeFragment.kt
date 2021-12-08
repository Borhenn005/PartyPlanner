package com.example.partyplanner.homeFragment

import android.app.AlertDialog
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast

import android.view.ViewGroup
import android.widget.EditText
import com.example.partyplanner.model.UserData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.partyplanner.R
import com.example.partyplanner.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {


    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        /**set List*/
        userList = ArrayList()
        /**set find Id*/
        addsBtn = view.findViewById(R.id.addingBtn)
        recv = view.findViewById(R.id.mRecycler)
        /**set Adapter*/
        userAdapter = UserAdapter(this.requireContext(),userList)
        /**setRecycler view Adapter*/
        recv.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL ,false)
        recv.adapter = userAdapter
        /**set Dialog*/
        addsBtn.setOnClickListener { addInfo() }



        return view
    }



    private fun addInfo() {
       val inflter = LayoutInflater.from(this.context)
        val v = inflter.inflate(R.layout.add_item,null)
        /**set view*/
        val userName = v.findViewById<EditText>(R.id.userName)
       val userNo = v.findViewById<EditText>(R.id.userNo)

       val addDialog = AlertDialog.Builder(this.context)


        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val names = userName.text.toString()
            val number = userNo.text.toString()

            userList.add(UserData("Title Event: $names","Date : $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this.context,"Adding User Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this.context,"Cancel", Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }


}

