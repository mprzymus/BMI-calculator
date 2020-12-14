package pl.mprzymus.bmi.history.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.mprzymus.bmi.history.database.model.BmiRecordData

@Database(entities = [BmiRecordData::class], version = 2, exportSchema = false)
@TypeConverters(HeightUnitConverter::class, WeightUnitConverter::class)
abstract class HistoryDatabase : RoomDatabase(){
    abstract val historyDao: HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, HistoryDatabase::class.java, "bmi_history_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}