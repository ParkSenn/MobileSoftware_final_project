package ddwucom.mobile.finalreport

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ddwucom.mobile.finalreport.data.MovieDBHelper
import ddwucom.mobile.finalreport.data.MovieDao
import ddwucom.mobile.finalreport.data.MovieDto
import ddwucom.mobile.finalreport.databinding.ActivityMainBinding

// 과제명: 영화 정보 관리 앱
// 분반: 01분반
// 학번: 20210786 성명: 박세은
// 제출일: 2023년 6월 23일

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var movieDao: MovieDao
    lateinit var movies : ArrayList<MovieDto>
    lateinit var adapter: MovieAdapter
    val REQ_ADD = 100
    val REQ_UPDATE = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieDao = MovieDao(this)
        movies = movieDao.getAllMovies()

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        adapter = MovieAdapter(movies)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // 아이템 클릭하면 UpdateActivity로 넘어가도록
        adapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position : Int) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra("dto", movies.get(position))
                startActivityForResult(intent, REQ_UPDATE)
            }
        })

        // 아이템 롱클릭하면 삭제되도록
        adapter.setOnItemLongClickListener(object : MovieAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int) {
                val builder = AlertDialog.Builder(this@MainActivity)
                val dialog = builder.create()
                builder.setTitle("영화 삭제")
                builder.setMessage("${movies.get(position).title} 영화를 정말 삭제하시겠습니까?")
                builder.setPositiveButton("네") { dialog, which ->
                    if(movieDao.deleteMovie(movies.get(position).id) > 0) {
                        refreshList(RESULT_OK)
                        Toast.makeText(this@MainActivity, "삭제 완료", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("아니요") { dialog, which ->
                    null
                }
                builder.show()
            }
        })
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQ_ADD -> refreshList(resultCode)
            REQ_UPDATE -> refreshList(resultCode)
        }
    }

    private fun refreshList(resultCode: Int) {
        if(resultCode == RESULT_OK) {
            movies.clear()
            movies.addAll(movieDao.getAllMovies())
            adapter.notifyDataSetChanged()
        }
        else
            Toast.makeText(this@MainActivity, "취소됨", Toast.LENGTH_SHORT).show()
    }

    // 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 메뉴 선택시 활성화
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item01 -> {
                val intent = Intent(this, AddActivity::class.java)
                startActivityForResult(intent, REQ_ADD)
            }

            R.id.item02 -> {
                val intent = Intent(this, IntroduceActivity::class.java)
                startActivity(intent)
            }
            R.id.item03 -> {
                val builder = AlertDialog.Builder(this@MainActivity)
                val dialog = builder.create()
                builder.setTitle("앱 종료")
                builder.setMessage("정말 종료하시겠습니까?")
                builder.setPositiveButton("네") { dialog, which ->
                    finish()
                }
                builder.setNegativeButton("아니요") { dialog, which ->
                    null
                }
                builder.show()
            }

            R.id.item04 -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

