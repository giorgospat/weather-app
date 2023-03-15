package com.example.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    fun getLocations(): Flow<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLocation(location: LocationEntity)

    @Delete
    fun deleteLocation(location: LocationEntity)

    @Query("SELECT COUNT(id) FROM locations")
    fun getCount(): Int

}