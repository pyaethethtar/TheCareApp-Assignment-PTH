package com.example.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shared.data.vos.CheckoutVO

@Dao
interface CheckoutDao {

    @Query("SELECT * FROM checkouts")
    fun getAllCheckouts() : LiveData<List<CheckoutVO>>

    @Query("SELECT * FROM checkouts WHERE checkoutId=:id")
    fun getCheckoutById(id : String) : LiveData<CheckoutVO>

    @Query("DELETE FROM checkouts")
    fun deleteAllCheckouts()

    @Delete
    fun deleteCheckout(checkout: CheckoutVO)

    @Insert
    fun addAllCheckouts(checkouts : List<CheckoutVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewCheckout(checkout : CheckoutVO)
}