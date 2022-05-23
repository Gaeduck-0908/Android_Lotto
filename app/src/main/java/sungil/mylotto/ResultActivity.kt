package sungil.mylotto

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text
import sungil.mylotto.databinding.ActivityMainBinding
import sungil.mylotto.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val strListLotto = intent.getStringExtra("strListLotto")
        val arrListLotto : List<String>? = strListLotto?.split(",")
        if(arrListLotto != null)
        {
            var cnt = 0
            for(s in arrListLotto)
            {
                cnt++
                if(cnt % 6 == 1) {
                    val tvIndex = TextView(this)
                    tvIndex.text = "\n${(cnt / 6 + 1).toString()}번쨰"
                    binding.gridLayout.addView(tvIndex)
                }
                binding.gridLayout.addView(
                    getTextViewWithNumber(s)
                )
            }
        }
    }

    private fun getTextViewWithNumber(s : String): TextView {
        val tv = TextView(this)
        tv.text = s
        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.gravity = Gravity.CENTER
        when(s.toInt()) {
            in 1..10 -> tv.setBackgroundResource(R.drawable.circle_red)
            in 11..20 -> tv.setBackgroundResource(R.drawable.circle_green)
            in 21..30 -> tv.setBackgroundResource(R.drawable.circle_blue)
            in 31..40 -> tv.setBackgroundResource(R.drawable.circle_mint)
            else -> tv.setBackgroundResource(R.drawable.circle_purple)
        }
        val lp = LinearLayout.LayoutParams (
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(2,90,2,2)
        tv.layoutParams = lp
        return tv
    }
}