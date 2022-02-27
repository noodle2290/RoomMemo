package takutaku.app.roommemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
data class Memo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val content: String?
)