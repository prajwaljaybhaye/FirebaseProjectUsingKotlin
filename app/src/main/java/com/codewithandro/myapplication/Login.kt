package com.codewithandro.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {

    lateinit var databaseRefrence :DatabaseReference //always global declerd

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val userName=findViewById<EditText>(R.id.userName)
        val userEmail = findViewById<EditText>(R.id.userEmail)
        val userPass = findViewById<EditText>(R.id.userPass)
        val logInBtn = findViewById<Button>(R.id.logInBtn)

        logInBtn.setOnClickListener{
            //find the firebase node like table head name
            val inputName = userName.text.toString()
            val inputEmail = userEmail.text.toString()
            val inputPass = userPass.text.toString()

            if(inputEmail.isNotEmpty() && inputPass.isNotEmpty() && inputName.isNotEmpty()){
                readData(inputName,inputEmail,inputPass ) //create own function
            }else{
                Toast.makeText(this,"Plase,Enter Email And Password...",Toast.LENGTH_SHORT).show()
            }
        }

    }



   private fun readData(inputEmail :String,inputPass :String ,inputName: String){
       databaseRefrence =FirebaseDatabase.getInstance().getReference("Users")//vahi node dena hai table head in firebase real time
       //read Data
       databaseRefrence.child(inputName).get().addOnSuccessListener {
           //check the data present in database
           if(it.exists()){
               //welcome app
               //featch data
               val storeEmail =it.child("email").value.toString()
               val storePass =it.child("pass").value.toString()
               Toast.makeText(this,"Feath Data Success"
                   + " $storeEmail And $storePass"
                   ,Toast.LENGTH_SHORT).show()

           }else{
               Toast.makeText(this,"Not Find Child...",Toast.LENGTH_SHORT).show()
           }


       }.addOnFailureListener {
           Toast.makeText(this,"Failed...",Toast.LENGTH_SHORT).show()
       }
   }
}