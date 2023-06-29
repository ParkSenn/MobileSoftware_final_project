package ddwucom.mobile.week12.intenttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ddwucom.mobile.week12.intenttest.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val greeting = intent.getStringExtra("greeting")
//        val food = intent.getSerializableExtra("food") as FoodDto
//
//        binding.tvText.text = food.foodName

        binding.btnOk.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("result_data", "한소희 예쁨")
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}