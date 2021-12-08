package com.example.partyplanner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.example.partyplanner.retrofit.Request
import com.example.partyplanner.retrofit.retrofit
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit

class sign_up : AppCompatActivity() {

    private lateinit var tvSignIn : TextView
    private lateinit var registerr : Button
    private lateinit var til_userName : TextInputLayout
    private lateinit var til_email : TextInputLayout
    private lateinit var til_password : TextInputLayout
    private lateinit var til_Repassword : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        til_userName = findViewById(R.id.tilUserName)

        til_email = findViewById(R.id.tilEmail)

        til_password = findViewById(R.id.tilPassword)

        til_Repassword = findViewById(R.id.tilRePassword)
        tvSignIn = findViewById(R.id.tvSignIn)
        tvSignIn.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }
        registerr = findViewById(R.id.btn_register)

        registerr.setOnClickListener{
            if(validate()){
                ServiceSignuP(til_userName.editText!!.text.toString(),til_email.editText!!.text.toString(),til_password.editText!!.text.toString());

            }
        }




    }



    fun ServiceSignuP(name:String,email:String,password:String) {
        // Create Retrofit
        val retrofi: Retrofit = retrofit.getInstance()
        val service: Request = retrofi.create(Request::class.java)
        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("email", email)
        jsonObject.put("password", password)


        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.Signup(requestBody)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(JsonParser.parseString(response.body()?.string()))
                    Log.d("Pretty Printed JSON :", prettyJson)

                    GoToLogin(this@sign_up) //GoTo Page Home

                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }

    fun GoToLogin(context: Context) {
        val intent = Intent(context, login::class.java)
        context.startActivity(intent)
        finish()
    }

    // controle de saisie

    private fun validate(): Boolean {
        var isValidate = true

        til_userName.error = null
        til_email.error = null
        til_password.error = null
        til_Repassword.error = null

        //check userName
        if(til_userName.editText!!.text.toString().isEmpty()){
            til_userName.error = "Must not be Empty"
            isValidate = false
        }
        //check email
        if(til_email.editText!!.text.toString().isEmpty()){
            til_email.error = "Must not be Empty"
            isValidate = false
        }
        else{
            if(!isValidEmail(til_email.editText!!.text.toString())){
                til_email.error = "Check your email"
                isValidate = false
            }
        }
        // check password
        if(til_password.editText!!.text.toString().isEmpty()){
            til_password.error = "Must not be Empty"
            isValidate = false
        }
        if(til_Repassword.editText!!.text.toString().isEmpty()){
            til_Repassword.error = "Must not be Empty"
            isValidate = false
        }
        if(til_password.editText!!.text.toString().isNotEmpty() && til_Repassword.editText!!.text.toString().isNotEmpty()){
            if(til_password.editText!!.text.toString().compareTo(til_Repassword.editText!!.text.toString()) != 0){
                isValidate = false
                til_Repassword.error = "Check your password"
            }
        }
        return isValidate
    }

    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}