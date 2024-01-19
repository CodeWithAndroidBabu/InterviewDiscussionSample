package com.neomocktest.deep.ui.auth.repo

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.StorageReference
import com.neomocktest.deep.data.Users
import com.neomocktest.deep.shared.SharedRepoImp
import com.neomocktest.deep.state.ApiState
import com.neomocktest.utility.AppConstant
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

/**
 * @Author: Deep raj
 * @Date: 01/11/23
 */

class AuthRepoImpl @Inject constructor() : AuthRepo {

    @Inject
    lateinit var db: FirebaseFirestore

    @Inject
    open lateinit var storageRef: StorageReference

    @Inject
    lateinit var sharedPref: SharedRepoImp

    override fun loginSignUpUser(users: Users): Flow<ApiState<String>> = callbackFlow {
        Log.d("nfbajnfka", "loginSignUpUser: ${users}")
        trySend(ApiState.Loading)
        val getDoc = db.collection(AppConstant.FBRoomName.USERS).document(users.empId)
        getDoc.get().addOnCompleteListener {
            Log.d("nfbajnfka", "addOnCompleteListener:${it.result?.exists()} ")
            if (it.isSuccessful && it.result?.exists() == true) {

                val getUsersRawData = it.result.data

                val getUserName = getUsersRawData?.get("name") as String
                val getUserId = getUsersRawData["empId"] as String
                val getUserImg = getUsersRawData["img"] as String

                val getUser = Users(getUserId,getUserName,getUserImg)
                sharedPref.saveUser(AppConstant.FBRoomName.USERS,getUser)
                trySend(ApiState.Info("Welcome Back $getUserName"))
            } else {

                sharedPref.saveUser(AppConstant.FBRoomName.USERS,users)

                Log.d("nfbajnfka", "loginSignUpUser:data  == $users")
                getDoc.set(users).addOnSuccessListener {
                    /**
                     * Image path stored successfully
                     * Handle success case if needed
                     * */
                    trySend(ApiState.Info("User has been registered"))
                }.addOnFailureListener { e ->
                    /**
                     * Failed to store image path
                     * Handle failure case if needed
                     * */
                    trySend(ApiState.Info("Something went wrong with uploading profile image, Please try again"))
                }
            }
        }.addOnFailureListener {
            Log.d("nfbajnfka", "addOnFailureListener: ${it.message}")
        }
        awaitClose {
            /**
             * Must close this flow else will get an run time exception
             * */
            close()
        }
    }

    override fun uploadUserImg(uuid: Uri?): Flow<ApiState<String>> = callbackFlow {
        Log.d("nfbajnfka", "uploadUserImg: $uuid")
        trySend(ApiState.Loading)
        var downloadUrl = ""
        uuid?.path?.let { path ->
            val file = File(path)
            val imageRef = storageRef.child("${file.nameWithoutExtension}.jpg")

            val uploadTask = imageRef.putFile(uuid)
            downloadUrl = imageRef.downloadUrl.await().toString()
            uploadTask.await()
        }
        Log.d("nfbajnfka", "uploadUserImg: end $downloadUrl")
        trySend(ApiState.Success(downloadUrl))
        awaitClose {
            /**
             * Must close this flow else will get an run time exception
             * */
            close()
        }
    }
}