package takutaku.app.roommemo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}