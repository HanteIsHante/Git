package googleroom.android.com.google_room

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import googleroom.android.com.google_room.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        into_home.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}
