package pl.mprzymus.bmi.history.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.mprzymus.bmi.history.database.model.BmiRecordData

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(night: BmiRecordData)

    @Query("DELETE FROM bmi_records")
    suspend fun clear()

    @Query("SELECT * FROM bmi_records")
    suspend fun getAll() : List<BmiRecordData>
}