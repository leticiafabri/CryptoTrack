// ../app/data/AppDatabase.kt
package br.pucpr.cryptotrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Moeda::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moedaDao(): MoedaDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "moedas.db"
                )
                    // Apenas POC — não usar em produção
                    .allowMainThreadQueries()
                    .build().also { INSTANCE = it }
            }
        }
    }
}