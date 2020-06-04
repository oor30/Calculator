package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var numA = 0.0
    private var numB = 0.0
    private var mode = 0
    private var isPushed = false
    private var isEqualed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = "0"
    }

    fun zeroCut(str: String): String {
        if(str.length>11) {
            val num = str.toDouble()
            
        }

        if(str.endsWith(".0")) {
            return str.substring(0, str.length-2)
        }
        return str
    }

    fun btNumber(view: View) {
        val bt = view as Button
        textView.text = if((textView.text=="0" || isPushed) && bt!=btDot) {
            isPushed = false
            if(isEqualed){
                isEqualed = false
                actionBtClear(btClear)
            }
            zeroCut(bt.text.toString())
        }else if(isPushed && bt==btDot){
            isPushed = false
            "0."
        }else{
            zeroCut(textView.text.toString() + bt.text.toString())
        }
        btClear.text = "C"
    }

    fun actionBtClear(view: View) {
        when(btClear.text) {
           "AC" -> {
               numA = 0.0
               mode = 0
           }
            "C" -> {
                btClear.text = "AC"
            }
        }
        textView.text = "0"
    }

    fun actionBtSign(view: View) {
        val num = textView.text.toString().toDouble()
        if(isPushed){
            numA = num*(-1.0)
        }
        textView.text = zeroCut((num*(-1.0)).toString())
    }

    fun actionBtPer(view: View) {
        val num = textView.text.toString().toDouble()
        if(isPushed){
            numA = num*0.01
        }
        textView.text = zeroCut((num*0.01).toString())
    }

    fun actionBtOpe(view: View) {
        val bt = view as Button
        isEqualed = false
        if(!isPushed){
            isPushed = true
            numB = textView.text.toString().toDouble()
            when (mode) {
                0 -> numA += numB
                1 -> numA -= numB
                2 -> numA *= numB
                3 -> numA /= numB
            }
        }
        when(bt) {
            btPlus -> mode = 0
            btMinus -> mode = 1
            btTimes -> mode = 2
            btDiv -> mode = 3
            btEqual -> {
                isEqualed = true
                btClear.text = "AC"
            }
        }
        textView.text = zeroCut(numA.toString())
    }
}
