package takutaku.app.roommemo

import androidx.room.*

@Dao
interface MemoDao {
    @Insert
    fun insert(memo : Memo)

    @Update
    fun update(memo : Memo)

    @Delete
    fun delete(memo : Memo)

    @Query("delete from memos")
    fun deleteAll()

    @Query("select * from memos")
    fun getAll(): List<Memo>

    @Query("select * from memos where id = :id")
    fun getMemo(id: Int): Memo
}