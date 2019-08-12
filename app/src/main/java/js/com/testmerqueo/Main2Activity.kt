package js.com.testmerqueo

import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView
import js.com.testmerqueo.adapters.movieAdapter
import js.com.testmerqueo.adapters.shoppingAdapter
import js.com.testmerqueo.models.MovieOb

import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    var listCart:List<MovieOb> = ArrayList()
    var listCartAm:List<MovieOb> = ArrayList()
    lateinit var moviesListCart: RecyclerView
    var adapter: shoppingAdapter = shoppingAdapter(null,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        listCart=this.intent.getParcelableArrayListExtra("extraextra")
        moviesListCart = findViewById(R.id.shopping_cart_recyclerView)
        System.out.println("LISTAAAAA SIZE"+ listCart.size)

        var simbolos:MutableList<MovieOb> = ArrayList()

        for (item in listCart) {
            if(!simbolos.contains(item))
            {
                var count = this.getNum(item.getId())
                item.setCant(count)
                simbolos.add(item)

            }
        }

        System.out.println("LISTAAAAA SIZE"+ simbolos.size)
        for (item in simbolos)
        {
            System.out.println("ELEMENTI"+ item.getTitle())
        }



        adapter = shoppingAdapter(listCart,applicationContext)
        moviesListCart.adapter = adapter

    }

    fun getNum(id: Int): Int{
        var count = 0
        for (item in listCart) {
            if(item.getId()== id)
            {
                count++
            }
        }
        return  count
    }

}
