package com.example.todo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.update_dialog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), UserAdapter.RowClickListener {
    private lateinit var userViewModel: UserViewModel
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var update_age: EditText
    private lateinit var update_name: EditText
    private lateinit var save: Button
    private lateinit var btn_update: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        userAdapter = UserAdapter(this, ArrayList(), ::onItemLongClick, ::onItemClick)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getAllUserData(this).observe(this, {
            userAdapter.setData(it as ArrayList<User>)
        })
        floatingActionButton.setOnClickListener {
            openSaveDialog()
        }
    }

    private fun onItemLongClick(user: User?) {

        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete Data")
            .setMessage("Are you sure delete?")
        dialog.setPositiveButton("Delete") { _, _ ->
            Toast.makeText(this, "Delete Data", Toast.LENGTH_SHORT).show()
            userViewModel.delete(user!!)
            //userViewModel.delete(this,User(getName,Integer.parseInt(getAge)))

        }
        dialog.setNegativeButton("cancel") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        dialog.show()
    }

    private fun onItemClick(user: User?) {
        builder = AlertDialog.Builder(this)
        val itemView: View = LayoutInflater.from(applicationContext).inflate(R.layout.update_dialog, null)
        dialog = builder.create()
        dialog.setView(itemView)
        update_name = itemView.findViewById(R.id.update_name)
        update_age = itemView.findViewById(R.id.update_age)
        btn_update = itemView.findViewById(R.id.btn_update)

        update_name.setText(user!!.name)
        update_age.setText(user.age)

        btn_update.setOnClickListener {
            val getName = update_name.text.toString().trim()
            val getAge = update_age.text.toString().trim()


            userViewModel.update(User(getName, getAge))

//            Toast.makeText(applicationContext, " Update successfully.. ${userViewModel}", Toast.LENGTH_SHORT)
//                .show()
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onItemClickListener(user: User) {

    }

    private fun openSaveDialog() {
        builder = AlertDialog.Builder(this)
        val itemView: View = LayoutInflater.from(applicationContext).inflate(R.layout.dialog, null)
        dialog = builder.create()
        dialog.setView(itemView)
        name = itemView.findViewById(R.id.name1)
        age = itemView.findViewById(R.id.age1)
        save = itemView.findViewById(R.id.save)
        save.setOnClickListener {
            saveDataIntoDatabase()
        }
        dialog.show()
    }

    private fun saveDataIntoDatabase() {
        val getName = name.text.toString().trim()
        val getAge = age.text.toString().trim()
        if (!TextUtils.isEmpty(getName) && !TextUtils.isEmpty(getAge)) {
            userViewModel.insert(this, User(getName, getAge))
            Toast.makeText(applicationContext, "Data added successfully..", Toast.LENGTH_SHORT)
                .show()
            dialog.dismiss()
        } else {
            Toast.makeText(applicationContext, "Please fill all the fields..", Toast.LENGTH_SHORT)
                .show()
        }
    }


}