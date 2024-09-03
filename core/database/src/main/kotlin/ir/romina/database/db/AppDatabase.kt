package ir.romina.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.romina.database.convertor.Converters
import ir.romina.database.dao.StationDao
import ir.romina.database.model.StationEntity

@Database(entities = [StationEntity::class], version = 1)
@TypeConverters(Converters::class) // Add converters for List<String>
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao

    companion object {
        const val NAME = "stations_db"
    }
}
