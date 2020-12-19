package com.example.thecareapp.delegates

import com.example.shared.data.vos.DoctorVO

interface RecentDoctorDelegate {

    fun onTapDoctor(doctorVO: DoctorVO)
}