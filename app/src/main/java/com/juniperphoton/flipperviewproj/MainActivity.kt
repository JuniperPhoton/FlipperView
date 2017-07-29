package com.juniperphoton.flipperviewproj

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import com.juniperphoton.flipperview.FlipperView

class MainActivity : AppCompatActivity() {
    private lateinit var flipperView: FlipperView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flipperView = findViewById(R.id.flipper_view) as FlipperView
        val prevView = findViewById(R.id.prev_btn)
        val nextView = findViewById(R.id.next_btn)
        val resetView = findViewById(R.id.reset_btn)

        resetView.setOnClickListener {
            flipperView.next(0, false)
        }
        prevView.setOnClickListener {
            flipperView.previous()
        }
        nextView.setOnClickListener {
            flipperView.next()
        }

        (findViewById(R.id.spinner) as Spinner).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                flipperView.next(position)
            }
        }

        (0..flipperView.childCount - 1)
                .map { flipperView.getChildAt(it) }
                .forEach {
                    it.setOnClickListener { v ->
                        flipperView.next()
                        Log.d("main", (v as Button).text.toString())
                    }
                }
    }
}
