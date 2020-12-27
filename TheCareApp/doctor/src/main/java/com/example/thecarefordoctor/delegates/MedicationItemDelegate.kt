package com.example.thecarefordoctor.delegates

import com.example.shared.data.vos.MedicationVO

interface MedicationItemDelegate  {

    fun onTapAddMedicine(medicationVO: MedicationVO)
    fun onTapRemoveMedicine(medicationVO: MedicationVO)
}
