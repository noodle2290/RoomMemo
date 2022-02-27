package takutaku.app.roommemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import takutaku.app.roommemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        val toEditIntent = Intent(this,EditActivity::class.java)

//        roomのインスタンス生成
        val db = Room.databaseBuilder(
            applicationContext,
            MemoDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

//        Daoのインスタンス生成
        val memoDao = db.memoDao()
//        roomのデータを全て取得
        val memos: List<Memo> = memoDao.getAll()
//        OnClickListenerを引数としてMemoAdapterのインスタンス生成
        val memoAdapter = MemoAdapter(
            OnClickListener { memo ->
//        EditActivityは後で作成します
                toEditIntent.putExtra("ID",memo.id)
                startActivity(toEditIntent)
            }
        )
        memoAdapter.submitList(memos)
        binding.recyclerView.adapter = memoAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.fab.setOnClickListener {
            startActivity(toEditIntent)
        }
    }
}