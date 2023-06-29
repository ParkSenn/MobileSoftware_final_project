package ddwucom.mobile.finalreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ddwucom.mobile.finalreport.data.MovieDao
import ddwucom.mobile.finalreport.data.MovieDto
import ddwucom.mobile.finalreport.databinding.ActivityAddBinding
import ddwucom.mobile.finalreport.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    lateinit var updateBinding : ActivityUpdateBinding
    lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDao = MovieDao(this)
        updateBinding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(updateBinding.root)

        val dto = intent.getSerializableExtra("dto") as MovieDto

        updateBinding.evUpdateTitle.setText(dto.title)
        updateBinding.evUpdateDirector.setText(dto.director)
        updateBinding.evUpdateActor.setText(dto.actor)
        updateBinding.evUpdateRate.setText(dto.rate.toString())
        updateBinding.evUpdateStory.setText(dto.story)

        updateBinding.btnUpdate.setOnClickListener {
            dto.title = updateBinding.evUpdateTitle.text.toString()
            dto.director = updateBinding.evUpdateDirector.text.toString()
            dto.actor = updateBinding.evUpdateActor.text.toString()
            dto.rate = updateBinding.evUpdateRate.text.toString().toDoubleOrNull()
            dto.story = updateBinding.evUpdateStory.text.toString()

            if(dto.title.equals("") || dto.director.equals("") || dto.actor.equals("") || dto.story.equals("") || dto.rate == null)
                Toast.makeText(this, "입력되지 않은 정보가 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show()
            else {
                if(movieDao.updateMovie(dto) > 0)
                    setResult(RESULT_OK)
                else
                    setResult(RESULT_CANCELED)
                finish()
            }
        }

        updateBinding.btnCnlUpdate.setOnClickListener {
            finish()
        }


    }
}