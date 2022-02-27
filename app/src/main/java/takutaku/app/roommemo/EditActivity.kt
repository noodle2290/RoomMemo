package takutaku.app.roommemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.room.Room
import takutaku.app.roommemo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val db = Room.databaseBuilder(
            applicationContext,
            MemoDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        val id:Int = intent.getIntExtra("ID",0)
        val memoDao = db.memoDao()
        val memo = memoDao.getMemo(id)
        val toMainIntent = Intent(this, MainActivity::class.java)

        if(id == 0) {
            binding.deleteButton.isInvisible = true
            binding.saveButton.setOnClickListener {
                val memo = Memo(
                    id,
                    binding.textInput.text.toString()
                )
                memoDao.insert(memo)
                startActivity(toMainIntent)
            }
        }else{
            binding.deleteButton.isVisible = true
            binding.textInput.setText(memo.content)
            binding.saveButton.setOnClickListener {
                val memo = Memo(
                    id,
                    binding.textInput.text.toString()
                )
                memoDao.update(memo)
                startActivity(toMainIntent)
            }
            binding.deleteButton.setOnClickListener {
                memoDao.delete(memo)
                startActivity(toMainIntent)
            }
        }
    }
}