package com.example.shared.network

import android.graphics.Bitmap
import android.util.Log
import com.example.shared.data.vos.*
import com.example.shared.utils.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList

object CloudFirestoreDataAgentImpl : FirebaseApi {

    private val firestoreDb = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference

    var specialityList: MutableList<SpecialityVO> = arrayListOf()

    override fun addNewDoctor(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val doctorMap = doctorVO.toDoctorMap()
        firestoreDb.collection(GET_DOCTORS).document(doctorVO.doctorId).set(doctorMap)
            .addOnSuccessListener {
                Log.d("Success", "Successfully added to doctors")
                onSuccess()
            }.addOnFailureListener {
                Log.d("Failure", "Failed to add doctor")
            }
//        uploadImage(image, DIRECTORY_DOCTOR_PROFILE_IMAGES, onComplete = {imageUrl->
//            doctorVO.doctorProfileImage = imageUrl
//            val doctorMap = doctorVO.toDoctorMap()
//            firestoreDb.collection(GET_DOCTORS).document(doctorVO.doctorId.toString()).set(doctorMap)
//                .addOnSuccessListener { Log.d("Success", "Successfully added to doctors") }
//                .addOnFailureListener { Log.d("Failure", "Failed to add doctor") }
//        })
    }

    override fun addNewPatient(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val patientMap = patientVO.toPatientMap()
        val patientDoc = firestoreDb.collection(GET_PATIENTS).document(patientVO.patientId)

        patientDoc.set(patientMap).addOnSuccessListener {
            patientDoc.collection(GET_CASE_SUMMARY)
            Log.d("Success", "Successfully added to patients")
            onSuccess()
        }.addOnFailureListener {
            Log.d("Failure", "Failed to add patient")
            onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
        }
//        uploadImage(image, DIRECTORY_PATIENT_PROFILE_IMAGES, onComplete = {imageUrl->
//            patientVO.patientProfileImage = imageUrl
//            val patientMap = patientVO.toPatientMap()
//            val patientDoc = firestoreDb.collection(GET_PATIENTS).document(patientVO.patientId)
//
//            patientDoc.set(patientMap).addOnSuccessListener {
//                patientDoc.collection(GET_CASE_SUMMARY)
//                Log.d("Success", "Successfully added to patients")
//            }.addOnFailureListener {
//                Log.d("Failure", "Failed to add patient")
//            }
//        })
    }

    override fun getDoctorInfo(
        doctorId: String,
        onSuccess: (DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firestoreDb.collection(GET_DOCTORS).document(doctorId).get()
            .addOnSuccessListener {result->
                val doctor =result.data?.toDoctorVO() ?: DoctorVO()
                onSuccess(doctor)
            }
            .addOnFailureListener {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }
    }

    override fun getPatientInfo(
        patientId: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val documentRef = firestoreDb.collection(GET_PATIENTS).document(patientId)
        documentRef.addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }.run {
                val patientVO = value?.data?.toPatientVO() ?: PatientVO()

                var isCompleteCaseSummary = false
                val caseSummaryRef= documentRef.collection(GET_CASE_SUMMARY)
                fetchCaseSummarySubCollection(caseSummaryRef, onSuccess = {
                    patientVO.patientCaseSummary = it
                    isCompleteCaseSummary = true

                    if (isCompleteCaseSummary)  onSuccess(patientVO)
                }, onFailure = {
                    onFailure(it)
                })
            }
        }

    }

    override fun getSpecialityList(
        onSuccess: (List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val collectionRef = firestoreDb.collection(GET_SPECIALITIES)

        collectionRef.get().addOnSuccessListener {result ->
            for (document in result){
                var speciality = document.data.toSpecialityVO()

                var isCompleteMedication = false
                val medicationSubCollectionRef = collectionRef.document(document.id)
                    .collection(GET_MEDICATION)
                fetchMedicationSubcollection(medicationSubCollectionRef, onSuccess = {
                    speciality.medication = it
                    isCompleteMedication = true
                }, onFailure = {
                    onFailure(it)
                })


                var isCompleteSpecialQuestion = false
                val specialQuestionSubCollectionRef = collectionRef.document(document.id)
                    .collection(GET_SPECIAL_QUESTIONS)
                fetchSpecialQuestioinsSubCollection(specialQuestionSubCollectionRef, onSuccess = {
                    speciality.specialQuestions = it
                    isCompleteSpecialQuestion = true

                    specialityList.add(speciality)
                    if (document==result.last() && isCompleteSpecialQuestion && isCompleteMedication)  {
                        onSuccess(specialityList)
                    }
                }, onFailure = {
                    onFailure(it)
                })

            }
        }.addOnFailureListener {
            onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
        }
    }

    override fun getSpecialityById(speciality: String, onSuccess: (SpecialityVO) -> Unit, onFailure: (String) -> Unit) {
        val documentQuery = firestoreDb.collection(GET_SPECIALITIES).whereEqualTo("speciality", speciality)
        documentQuery.get()
            .addOnSuccessListener {
                val result = it.documents.first()
                var specialityVO = result.data?.toSpecialityVO() ?: SpecialityVO()
                var isCompleteSpecialQuestions = false
                var isCompleteMedications = false

                val documentRef = firestoreDb.collection(GET_SPECIALITIES).document(result.id)
                val specialQuesSubColRef = documentRef.collection(GET_SPECIAL_QUESTIONS)
                fetchSpecialQuestioinsSubCollection(specialQuesSubColRef, onSuccess = {
                    specialityVO.specialQuestions = it
                    isCompleteSpecialQuestions = true
                }, onFailure = {
                    onFailure(it)
                })

                val medicationSubColRef = documentRef.collection(GET_MEDICATION)
                fetchMedicationSubcollection(medicationSubColRef, onSuccess = {
                    specialityVO.medication = it
                    isCompleteMedications = true

                    if (isCompleteSpecialQuestions && isCompleteMedications){
                        onSuccess(specialityVO)
                    }
                }, onFailure = {
                    onFailure(it)
                })


            }.addOnFailureListener {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }
    }

    override fun getGeneralQuestions(
        onSuccess: (List<GeneralQuestionVO>) -> Unit, onFailure: (String) -> Unit
    ) {
        firestoreDb.collection(GET_GENERAL_QUESTIONS).get()
            .addOnSuccessListener {result->
                val generalQuestionList = arrayListOf<GeneralQuestionVO>()
                for (document in result){
                    val generalQuestion = document.data.toGeneralQuestionVO()
                    generalQuestionList.add(generalQuestion)
                }
                onSuccess(generalQuestionList)
            }.addOnFailureListener {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }
    }

    override fun getNewConsultationRequest(
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val collectionRef = firestoreDb.collection(GET_CONSULTATION_REQUESTS)

        collectionRef.whereEqualTo("status", REQUEST_STATUS_REQUEST).addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }.run {
                val consultationRequestList = arrayListOf<ConsultationRequestVO>()
                val result = value?.documents ?: arrayListOf()
                for (document in result){
                    var consultationRequestVO = document.data?.toConsultationRequestVO() ?: ConsultationRequestVO()

                    var isCompleteCaseSummary = false
                    val caseSummaryRef= collectionRef.document(document.id).collection(GET_CASE_SUMMARY)
                    fetchCaseSummarySubCollection(caseSummaryRef, onSuccess = {
                        consultationRequestVO.caseSummary = it
                        isCompleteCaseSummary = true

                        consultationRequestList.add(consultationRequestVO)
                        if (document==result.last() && isCompleteCaseSummary)  {
                            onSuccess(consultationRequestList)
                        }
                    }, onFailure = {
                        onFailure(it)
                    })
                }
            }
        }
    }

    override fun getChatMessages(
        consultationId: String,
        onSuccess: (List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val chatRef = firestoreDb.collection(GET_CONSULTATIONS)
            .document(consultationId).collection(GET_CHATS)

        fetchChatSubCollection(chatRef, onSuccess = {
            onSuccess(it)
        }, onFailure = {
            onFailure(it)
        })
    }


    override fun getConsultationList(
        onSuccess: (List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val consultationCollectionRef = firestoreDb.collection(GET_CONSULTATIONS)

        consultationCollectionRef.addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }.run {
                val consultationList = arrayListOf<ConsultationVO>()
                val documents = value?.documents ?: arrayListOf()
                for (document in documents){
                    val consultationVO = document.data?.toConsultationVO() ?: ConsultationVO()

                    val caseSummaryRef = consultationCollectionRef.document(document.id)
                        .collection(GET_CASE_SUMMARY)
                    val chatRef = consultationCollectionRef.document(document.id)
                        .collection(GET_CHATS)
                    val prescriptionRef = consultationCollectionRef.document(document.id)
                        .collection(GET_PRESCRIPTIONS)

                    var isCompleteCaseSummary = false
                    var isCompleteChats = false
                    var isCompletePrescription = false

                    fetchCaseSummarySubCollection(caseSummaryRef, onSuccess = {
                        consultationVO.caseSummary = it
                        isCompleteCaseSummary=true
                    }, onFailure = {
                        onFailure(it)
                    })

                    fetchChatSubCollection(chatRef, onSuccess = {
                        consultationVO.chats = it
                        isCompleteChats = true
                    }, onFailure = {
                        onFailure(it)
                    })

                    fetchMedicationSubcollection(prescriptionRef, onSuccess = {
                        consultationVO.prescriptions = it
                        isCompletePrescription = true

                        consultationList.add(consultationVO)
                        if (document==documents.last() && isCompleteCaseSummary &&
                            isCompleteChats && isCompletePrescription){
                            onSuccess(consultationList)
                        }
                    }, onFailure = {
                        onFailure(it)
                    })
                }
            }
        }
    }

    override fun getCheckoutInfo(
        checkoutId: String,
        onSuccess: (CheckoutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val documentRef = firestoreDb.collection(GET_CHECKOUTS).document(checkoutId)
        documentRef.addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }.run {
                var checkoutVO = value?.data?.toCheckoutVO() ?: CheckoutVO()

                var isCompletePrescription = false
                val prescriptionRef = documentRef.collection(GET_PRESCRIPTIONS)
                fetchMedicationSubcollection(prescriptionRef, onSuccess = {
                    checkoutVO.prescriptions = it
                    isCompletePrescription = true

                    if (isCompletePrescription){
                        onSuccess(checkoutVO)
                    }
                }, onFailure = {
                    onFailure(it)
                })


            }
        }
    }

    override fun getDoctorListByIds(
        ids: ArrayList<String>,
        onSuccess: (List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val doctorList = arrayListOf<DoctorVO>()

        for (id in ids){
            firestoreDb.collection(GET_DOCTORS).document(id).addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
                }.run {
                    val doctorVO = value?.data?.toDoctorVO() ?: DoctorVO()
                    doctorList.add(doctorVO)

                    if (id==ids.last())     onSuccess(doctorList)
                }
            }
        }

    }

    override fun addConsultationRequest(
        consultationRequestVO: ConsultationRequestVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val documentRef = if(consultationRequestVO.requestId=="") firestoreDb.collection(GET_CONSULTATION_REQUESTS).document()
        else firestoreDb.collection(GET_CONSULTATION_REQUESTS).document(consultationRequestVO.requestId)


        consultationRequestVO.requestId = documentRef.id
        Log.d("TheCareMM", consultationRequestVO.requestId)
        val consultationRequestMap = consultationRequestVO.toConsultationRequestMap()

        documentRef.set(consultationRequestMap).addOnSuccessListener {
            for (cs in consultationRequestVO.caseSummary){
                val caseSummary = CaseSummaryVO()
                caseSummary.question = cs.question
                caseSummary.answer = cs.answer
                documentRef.collection(GET_CASE_SUMMARY).document().set(caseSummary.toCaseSummaryMap())
            }
            onSuccess()
        }.addOnFailureListener {
            onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
        }
    }



    override fun addChatTextMessage(
        consultationId: String, sender:String, message: String,
        onSuccess: () -> Unit, onFailure: (String) -> Unit
    ) {
        val chatSubCollectionRef = firestoreDb.collection(GET_CONSULTATIONS)
            .document(consultationId).collection(GET_CHATS)
        val chatVO = ChatVO()

        if (sender=="doctor"){
            chatVO.doctor = message
            chatSubCollectionRef.document().set(chatVO.toChatMap())
        }
        else{
            chatVO.patient = message
            chatSubCollectionRef.document().set(chatVO.toChatMap())
        }
    }

    override fun addChatImageMessage(
        consultationId: String,sender: String, image: Bitmap,
        onSuccess: () -> Unit, onFailure: (String) -> Unit
    ) {
        val chatSubCollectionRef = firestoreDb.collection(GET_CONSULTATIONS)
            .document(consultationId).collection(GET_CHATS)
        val chatVO = ChatVO()

        if (sender=="doctor"){
            uploadImage(image, DIRECTORY_DOCTOR_CHAT_IMAGES, onComplete = {imageUrl->
                chatVO.doctorImage = imageUrl
                chatSubCollectionRef.document().set(chatVO.toChatMap())
            })
        }
        else{
            uploadImage(image, DIRECTORY_PATIENT_CHAT_IMAGES, onComplete = {imageUrl->
                chatVO.patientImage = imageUrl
                chatSubCollectionRef.document().set(chatVO.toChatMap())
            })
        }

    }

    override fun addCheckout(
        checkoutVO: CheckoutVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val checkoutDoc =  firestoreDb.collection(GET_CHECKOUTS).document()
        checkoutVO.checkoutId = checkoutDoc.id

        checkoutDoc.set(checkoutVO.toCheckoutMap()).addOnSuccessListener {

            checkoutVO.prescriptions?.let {
                for (prescription in it){
                    val prescriptionMap = prescription.toMedicationMap()

                    checkoutDoc.collection(GET_PRESCRIPTIONS).document().set(prescriptionMap)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener {
                            onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
                        }
                }
            }
        }.addOnFailureListener {
            onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
        }

    }

    override fun addNewConsultation(
        consultationVO: ConsultationVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val documentRef = firestoreDb.collection(GET_CONSULTATIONS).document()
        consultationVO.consultationId = documentRef.id

        documentRef.set(consultationVO.toConsultationMap())
            .addOnSuccessListener {
                for (cs in consultationVO.caseSummary){
                    val caseSummary = CaseSummaryVO()
                    caseSummary.question = cs.question
                    caseSummary.answer = cs.answer
                    documentRef.collection(GET_CASE_SUMMARY).document().set(caseSummary.toCaseSummaryMap())
                }
                onSuccess()
            }.addOnFailureListener {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }
    }

    override fun addRecentlyConsultedDoctor(
        doctorId: String,
        patientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firestoreDb.collection(GET_PATIENTS).document(patientId)
            .update(GET_RECENT_DOCTORS, FieldValue.arrayUnion(doctorId))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION) }
    }



    private fun uploadImage(image: Bitmap, directory:String, onComplete:(String)->Unit){
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val imageRef = storageReference.child("${directory}/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            val imageUrl = task.result.toString()
            onComplete(imageUrl)
        }
    }


    private fun fetchSpecialQuestioinsSubCollection(
        collectionRef: CollectionReference,
        onSuccess: (ArrayList<SpecialQuestionVO>) -> Unit,
        onFailure: (String) -> Unit){

        collectionRef.get().addOnSuccessListener {result->
            val specialQuestionList = arrayListOf<SpecialQuestionVO>()
            for (document in result){
                val specialQuestionVO = document.data.toSpecialQuestionVO()
                specialQuestionList.add(specialQuestionVO)
            }
            onSuccess(specialQuestionList)
        }.addOnFailureListener {
            onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
        }
    }

    private fun fetchMedicationSubcollection(
        collectionRef: CollectionReference,
        onSuccess: (ArrayList<MedicationVO>) -> Unit,
        onFailure: (String) -> Unit
    ){
        collectionRef.get().addOnSuccessListener {result->
            val medicationList = arrayListOf<MedicationVO>()
            for (document in result){
                val medicationVO = document.data.toMedicationVO()
                medicationList.add(medicationVO)
            }
            onSuccess(medicationList)
        }.addOnFailureListener {
            onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
        }
    }

    private fun fetchCaseSummarySubCollection(
        collectionRef: CollectionReference,
        onSuccess: (ArrayList<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ){
        collectionRef.addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }.run {
                val casesummaryList = arrayListOf<CaseSummaryVO>()
                val result = value?.documents ?: arrayListOf()
                for (document in result){
                    val caseSummaryVO = document.data?.toCaseSummaryVO() ?: CaseSummaryVO()
                    casesummaryList.add(caseSummaryVO)
                }
                onSuccess(casesummaryList)
            }
        }
    }


    private fun fetchChatSubCollection(collectionRef: CollectionReference,
                                       onSuccess: (ArrayList<ChatVO>) -> Unit,
                                       onFailure: (String) -> Unit
    ){
        collectionRef.addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: EM_CHECK_INTERNET_CONNECTION)
            }.run {
                val chatList = arrayListOf<ChatVO>()
                val result = value?.documents ?: arrayListOf()
                for (document in result){
                    val chatVO = document.data?.toChatVO() ?: ChatVO()
                    chatList.add(chatVO)
                }
                onSuccess(chatList)
            }
        }
    }




}