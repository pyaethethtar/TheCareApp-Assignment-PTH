package com.example.shared.network.auth

import android.app.Activity
import android.net.Uri
import android.util.Log
import com.example.shared.utils.EM_CHECK_INTERNET_CONNECTION
import com.facebook.AccessToken
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.net.URL
import java.util.concurrent.TimeUnit

object FirebaseAuthManager : AuthManagerApi {

    private val mFirebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken


    override fun register(
        username: String,
        email: String,
        password: String,
        onSuccess: (id: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete){
                mFirebaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(username).build()
                )
                val id = mFirebaseAuth.currentUser?.uid ?: ""
                onSuccess(id)
            }
            else{
                onFailure(it.exception?.message ?: "Please check Internet Connection")
            }
        }


    }

    override fun login(
        email: String,
        password: String,
        onSuccess: (id: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete){
                val id = mFirebaseAuth.currentUser?.uid ?: ""
                onSuccess(id)
            }
            else{
                onFailure(it.exception?.message ?: EM_CHECK_INTERNET_CONNECTION)
            }
        }

    }

    override fun loginWithFacebook(
        token: AccessToken,
        onSuccess: (id: String, name: String, email: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener {task ->
            if (task.isSuccessful && task.isComplete){
                val id = mFirebaseAuth.currentUser?.uid ?: ""
                val name = mFirebaseAuth.currentUser?.displayName ?: ""
                val email = mFirebaseAuth.currentUser?.email ?: ""
                onSuccess(id, name, email)

            }
            else{
                onFailure(task.exception?.message ?: EM_CHECK_INTERNET_CONNECTION)
            }
        }

    }

    override fun updateProfile(
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.currentUser?.updatePassword(password)?.addOnSuccessListener {
            onSuccess()
        }?.addOnFailureListener {
            onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
        }
    }



    override fun loginWithPhone(phone: String, activity: Activity) {

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("FirebasePhone", "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w("FirebasePhone", "onVerificationFailed", e)
                if (e is FirebaseAuthInvalidCredentialsException) {

                } else if (e is FirebaseTooManyRequestsException) {

                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("FirebasePhone", "onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token
            }
        }


        val options = PhoneAuthOptions.newBuilder(mFirebaseAuth)
            .setPhoneNumber("+95"+phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        Log.d("FirebasePhone", "FirebasePhone Successful")
//        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener {
//            if (it.isSuccessful && it.isComplete){
//                Log.d("FirebasePhone", mFirebaseAuth.currentUser?.uid?:"")
//            }
//            else{
//                Log.d("FirebasePhone", it.exception?.message?:"")
//            }
//        }

    }


}