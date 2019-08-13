package js.com.testmerqueo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awesome.shorty.AwesomeToast
import js.com.testmerqueo.adapters.shoppingAdapter
import js.com.testmerqueo.models.MovieOb

import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.collections.ArrayList

class CartActivity : AppCompatActivity() {

    var listCart:List<MovieOb> = ArrayList()
    var listCartAm:List<MovieOb> = mutableListOf()
    private lateinit var moviesListCart: RecyclerView
    var adapter: shoppingAdapter = shoppingAdapter(null,null)
    private lateinit var cleanbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        listCart = this.intent.getParcelableArrayListExtra("arrayPass")


        moviesListCart = findViewById(R.id.shopping_cart_recyclerView)
        moviesListCart.layoutManager = LinearLayoutManager(this)
        cleanbtn = findViewById(R.id.btn_clean)

        for (item in listCart) {
                var count = this.getNum(item.getId())
                item.setCant(count)
        }


        listCartAm = listCart.distinctBy { it.getTitle() }

        adapter = shoppingAdapter(listCartAm,applicationContext)
        adapter.notifyDataSetChanged()
        moviesListCart.adapter = adapter

        cleanbtn.setOnClickListener{

            var listClean:List<MovieOb> = mutableListOf()
            adapter = shoppingAdapter(listClean,applicationContext)
            moviesListCart.adapter = adapter
            adapter.notifyDataSetChanged()
            AwesomeToast.info(applicationContext, "Carrito vaciado" ).show()
        }
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
