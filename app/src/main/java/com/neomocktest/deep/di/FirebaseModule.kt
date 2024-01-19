package com.neomocktest.deep.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: Deep raj
 * @Date: 01/11/23
 */

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFireStoreDb(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseStorageChild(): StorageReference =
        Firebase.storage.reference.child("profilePictures/")
}

//{
//    ConstraintSets: {
//    start: {
//    profileImg: {
//    with: 50,
//    height: 50,
//    start: ['parent', 'start', 24],
//    top: ['parent', 'top', 24],
//},
//    userName: {
//    top: ['profileImg', 'top'],
//    bottom: ['profileImg', 'bottom'],
//    start: ['profileImg', 'end', 24],
//},
//    container: {
//    width: 'spread',
//    height: 'spread',
//    start: ['parent', 'start'],
//    end: ['parent', 'end'],
//    top: ['parent', 'top'],
//    bottom: ['parent', 'bottom',-24]
//}
//},
//    end: {
//    profileImg: {
//    with: 180,
//    height: 180,
//    start: ['parent', 'start', 24],
//    end: ['parent', 'end', 24],
//    top: ['parent', 'top', 24],
//},
//    userName: {
//    start: ['profileImg', 'start', 24],
//    end: ['profileImg', 'end'],
//    top: ['profileImg', 'bottom']
//},
//    container: {
//    width: 'spread',
//    height: 'spread',
//    start: ['parent', 'start'],
//    end: ['parent', 'end'],
//    top: ['parent', 'top'],
//    bottom: ['parent', 'bottom',-24]
//}
//}
//},
//    Transitions: {
//    default: {
//    from: 'start',
//    to: 'end',
//    pathMotionArc: 'startHorizontal',
//    keyFrames: {
//    keyAttributes: [
//    {
//        target: ['profileImg'],
//        frames: [0,100]
//    }
//    ]
//}
//}
//}
//}