package com.codewithandro.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignIn : AppCompatActivity() {

   //bad me value dene ke liye use hota hai late intilixe
    lateinit var database:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        //basic
       val userName=findViewById<EditText>(R.id.userName)
       val userEmail=findViewById<EditText>(R.id.userEmail)
       val userPass=findViewById<EditText>(R.id.userPass)

       val signInBtn =findViewById<Button>(R.id.signUpBtn)
       val logInBtn =findViewById<Button>(R.id.logInBtn)

        //click btn
       signInBtn.setOnClickListener{

           //simple access the value in store in a variable
           val name = userName.text.toString()
           val email = userEmail.text.toString()
           val pass = userPass.text.toString()



           //com.exaple.project -> create the data class User name ki sabko ek class me store kiya
           val user = User(name,email,pass)

           //firebase ka addreass realtime data open project firebase online database User <- schema
           database =FirebaseDatabase.getInstance().getReference("Users")//conection stablish
           //insert data               ! user =varibale store User data Class
                                          //bas store ho gaya
           database.child(name).setValue(user).addOnSuccessListener{

               Toast.makeText(this,"Registration Successfully...",Toast.LENGTH_SHORT).show()
           }.addOnSuccessListener {
               Toast.makeText(this,"Falied Try Again...",Toast.LENGTH_SHORT).show()
           }



       }

        //login Btn
        logInBtn.setOnClickListener{
            intent= Intent(applicationContext,Login::class.java)
            startActivity(intent)
        }


    }
}