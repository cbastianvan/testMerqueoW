package js.com.testmerqueo

import android.os.Bundle
import android.os.Parcelable
import android.text.method.TextKeyListener.clear
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awesome.shorty.AwesomeToast
import com.nex3z.notificationbadge.NotificationBadge
import js.com.testmerqueo.adapters.movieAdapter
import js.com.testmerqueo.adapters.shoppingAdapter
import js.com.testmerqueo.models.MovieOb

import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*
import java.util.Collections.addAll
import kotlin.collections.ArrayList

class Main2Activity : AppCompatActivity() {

   // var listCart = ArrayList<MovieOb>()
   var listCart:List<MovieOb> = ArrayList<MovieOb>()
    var listCartAm:List<MovieOb> = mutableListOf<MovieOb>()
    lateinit var moviesListCart: RecyclerView
    var adapter: shoppingAdapter = shoppingAdapter(null,null)
    lateinit var clean_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        listCart = this.intent.getParcelableArrayListExtra("extraextra")



        moviesListCart = findViewById(R.id.shopping_cart_recyclerView)
        moviesListCart.layoutManager = LinearLayoutManager(this)
        clean_btn = findViewById(R.id.btn_clean)

       for (item in listCart) {

                System.out.println("ingreso ")
                var count = this.getNum(item.getId())
                item.setCant(count)

        }



        System.out.println("LISTAAAAA SIZE SIM"+ listCart.size)
        for (item in listCart)
        {
            System.out.println("ELEMENTI"+ item.getTitle())

            System.out.println("count"+ item.getCant())
        }


        listCartAm = listCart.distinctBy { it.getTitle() }
        for (item in listCartAm) {
            System.out.println("LIST CARD "+item.getTitle())
        }


        adapter = shoppingAdapter(listCartAm,applicationContext)
        adapter.notifyDataSetChanged()
        moviesListCart.adapter = adapter

        clean_btn.setOnClickListener{
            var listClean:List<MovieOb> = mutableListOf<MovieOb>()
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
