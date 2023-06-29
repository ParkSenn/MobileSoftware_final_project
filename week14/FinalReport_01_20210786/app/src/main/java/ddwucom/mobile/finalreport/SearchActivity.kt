package ddwucom.mobile.finalreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ddwucom.mobile.finalreport.data.MovieDao
import ddwucom.mobile.finalreport.databinding.ActivityMainBinding
import ddwucom.mobile.finalreport.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    lateinit var searchBinding: ActivitySearchBinding
    lateinit var movieDao : MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)

        movieDao = MovieDao(this)

        searchBinding.btnSearchOk.setOnClickListener {
            val kw = searchBinding.evSearch.text.toString()

            if(kw.equals(""))
                Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            else {
               val result = movieDao.searchMovie(kw)
                searchBinding.tvResult.text = result
            }
        }

        searchBinding.btnSearchCnl.setOnClickListener {
            finish()
        }

    }
}