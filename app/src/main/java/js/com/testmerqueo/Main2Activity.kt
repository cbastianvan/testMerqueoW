package js.com.testmerqueo

import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import js.com.testmerqueo.models.MovieOb

import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    var listCart:List<MovieOb> = ArrayList<MovieOb>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        listCart=this.intent.getParcelableArrayListExtra("extraextra")
        System.out.println("LISTAAAAA SIZE"+ listCart.size)

        for (item in listCart) System.out.println("LISTAAAAA"+ item.getTitle())


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
