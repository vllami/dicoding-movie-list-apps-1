package com.dicoding.capstone.core.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.capstone.core.Constant.DATABASE
import com.dicoding.capstone.core.source.local.entity.MoveeEntity
import androidx.room.Room.databaseBuilder as Room

@Database(entities = [MoveeEntity::class], version = 1, exportSchema = false)
abstract class MoveeDatabase : RoomDatabase() {

    abstract fun moveeDAO(): MoveeDAO

    companion object {
        @Volatile
        private var moveeDatabase: MoveeDatabase? = null

        fun getDatabase(context: Context): MoveeDatabase {
            return moveeDatabase ?: synchronized(this) {
                val database = Room(context.applicationContext, MoveeDatabase::class.java, DATABASE).build()

                moveeDatabase = database
                database
            }
        }
    }

}