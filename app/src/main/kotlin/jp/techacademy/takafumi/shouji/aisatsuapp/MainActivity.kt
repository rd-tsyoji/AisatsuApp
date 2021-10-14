package jp.techacademy.takafumi.shouji.aisatsuapp

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jp.techacademy.takafumi.shouji.aisatsuapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.text = getString(R.string.BUTTON_LABEL)
        binding.button.setOnClickListener(this)
        binding.textView.text = getString(R.string.TEXT_VIEW_INITIAL)
        setContentView(binding.root)
    }

    /**
     * ボタンクリック時動作
     */
    override fun onClick(v: View) {
        showTimePickerDialog()
    }

    /**
     * showTimePickerDialog
     */
    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val tz = TimeZone.getTimeZone("Asia/Tokyo")
        cal.timeZone = tz
        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                updateViewMessage(hour, minute)
            },
            cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true
        )
        timePickerDialog.show()
    }

    /**
     * textViewのメッセージを変更
     */
    private fun updateViewMessage(hour: Int, minute: Int) {
        var newMessage = "";
        newMessage = when (hour) {
            in 2..9 -> {
                // 2:00 ~ 9:59 「おはよう」
                getString(R.string.GREETING_MORNING)
            }
            in 10..17 -> {
                // 10:00 ~ 17:59 「こんにちは」
                getString(R.string.GREETING_DAYTIME)
            }
            else -> {
                // 18:00 ~ 1:59 「こんばんは」
                getString(R.string.GREETING_NIGHT)
            }
        }
        binding.textView.text = newMessage
    }
}