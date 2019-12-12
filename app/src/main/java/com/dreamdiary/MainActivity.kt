package com.dreamdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dreamdiary.ui.main.AddDreamFragment
import com.dreamdiary.ui.main.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.FragmentToActivityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onAddDreamListener() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, AddDreamFragment.newInstance())
            .commit()
    }

    override fun onClickDreamListener() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, AddDreamFragment.newInstance())
            .commit()
    }

}
