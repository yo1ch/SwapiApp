package com.example.feature.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature.data.local.entities.CharacterEntity
import com.example.feature.data.local.entities.FilmEntity
import com.example.feature.data.local.entities.PlanetEntity
import com.example.feature.data.local.entities.StarshipEntity
import com.example.feature.data.local.typeconverters.StringListConverter

@Database(
    entities = [CharacterEntity::class, PlanetEntity::class, StarshipEntity::class, FilmEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class FeatureDatabase : RoomDatabase() {

    abstract fun featureDao(): FeatureDao

    companion object {
        @Volatile
        private var INSTANCE: FeatureDatabase? = null

        fun getInstance(context: Context): FeatureDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeatureDatabase::class.java,
                    "feature_database",
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}