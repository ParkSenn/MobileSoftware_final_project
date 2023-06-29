package ddwucom.mobile.finalreport

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ddwucom.mobile.finalreport.data.MovieDBHelper
import ddwucom.mobile.finalreport.data.MovieDao
import ddwucom.mobile.finalreport.data.MovieDto
import ddwucom.mobile.finalreport.databinding.ActivityAddBinding
import ddwucom.mobile.finalreport.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {
    lateinit var addBinding : ActivityAddBinding
    lateinit var movieDao : MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDao = MovieDao(this)

        addBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(addBinding.root)

        addBinding.btnAdd.setOnClickListener {
            val title = addBinding.evTitle.text.toString()
            val director = addBinding.evDirector.text.toString()
            val actor = addBinding.evActor.text.toString()
            var rate = addBinding.evRate.text.toString().toDoubleOrNull()
            val story = addBinding.evStory.text.toString()

            if(title.equals("") || director.equals("") || actor.equals("") || story.equals("") || rate == null)
                Toast.makeText(this, "입력되지 않은 정보가 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show()
            else {
                val newDto = MovieDto(0, R.mipmap.loveletter, title, director, actor, rate, story)

                if(movieDao.addMovie(newDto) > 0)
                    setResult(RESULT_OK)
                else
                    setResult(RESULT_CANCELED)

                finish()
            }
        }

        addBinding.btnCnlAdd.setOnClickListener {
            finish()
        }
    }

//    fun addMovie(newDto: MovieDto) : Long {
//        val helper = MovieDBHelper(this)
//        val db = helper.writableDatabase
//
//        val newValue = ContentValues()
//        newValue.put(MovieDBHelper.COL_TITLE, newDto.title)
//        newValue.put(MovieDBHelper.COL_DIRECTOR, newDto.director)
//        newValue.put(MovieDBHelper.COL_ACTOR, newDto.actor)
//        newValue.put(MovieDBHelper.COL_RATE, newDto.rate)
//        newValue.put(MovieDBHelper.COL_STORY, newDto.story)
//
//        val result = db.insert(MovieDBHelper.TABLE_NAME, null, newValue)
//
//        helper.close()
//
//        return result
//    }
}