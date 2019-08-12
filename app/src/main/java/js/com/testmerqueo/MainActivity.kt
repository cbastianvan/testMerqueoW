package js.com.testmerqueo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import js.com.testmerqueo.consuming.MoviesRepository
import androidx.recyclerview.widget.RecyclerView
import js.com.testmerqueo.adapters.movieAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.awesome.dialog.AwesomeCustomDialog
import com.awesome.shorty.AwesomeToast
import com.bumptech.glide.Glide
import com.nex3z.notificationbadge.NotificationBadge
import js.com.testmerqueo.consuming.OnGetMoviesCallback
import js.com.testmerqueo.models.MovieOb
import kotlinx.android.synthetic.main.activity_main2.*



class MainActivity : AppCompatActivity() {

    private val URL_IMAGE = "https://image.tmdb.org/t/p/w500"
    private var badgeCount = 0
    lateinit var moviesList: RecyclerView
    lateinit var iconNot: NotificationBadge
    var adapter: movieAdapter = movieAdapter(null,null)
    var moviesRepository: MoviesRepository = MoviesRepository.getInstance()
    lateinit var moviesListArray: List<MovieOb>
    val cartList = ArrayList<MovieOb>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        iconNot = findViewById(R.id.badge)
        moviesRepository = MoviesRepository.getInstance()
        moviesList = findViewById(R.id.movies_list)

        moviesList.apply {
            layoutManager = GridLayoutManager(context , 2)
        }

        moviesRepository.getMovies(object : OnGetMoviesCallback {


            override fun onSuccess(movies: List<MovieOb>) {
                adapter = movieAdapter(movies,applicationContext)
                moviesListArray = movies
                moviesList.adapter = adapter
                adapter.setOnItemClickListener(object : movieAdapter.OnItemClickListener {
                    override fun onCardClick(position: Int) {
                        viewDetail(position)
                    }

                    override fun onItemClick(position: Int) {
                        addCart(position)
                    }

                })

            }

            override fun onError() {
                Toast.makeText(this@MainActivity, "Please check your internet connection.", Toast.LENGTH_SHORT).show()
            }
        })

            iconNot.setOnClickListener{
            val i = Intent(this, Main2Activity::class.java)

            i.putParcelableArrayListExtra("extraextra", cartList)
            startActivity(i)

        }

    }

    fun addCart(position: Int )
    {
        addNum()
        var mCart: MovieOb = moviesListArray[position]
        cartList.add(mCart)
    }

    fun viewDetail(position: Int )
    {
        var name = moviesListArray[position].getTitle()
        var year = moviesListArray[position].getReleaseDate()
        var urlPath = moviesListArray[position].getPosterPath()
        var rating = moviesListArray[position].getRating()

        AwesomeCustomDialog(this)
            .setView(R.layout.detail_movie)
            .configureView(object: AwesomeCustomDialog.ViewConfigurator{
                override fun configureView(v: View) {

                    var imgDet: ImageView = v.findViewById(R.id.image1)
                    var nameDet: TextView = v.findViewById(R.id.det_name)
                    var yearDet: TextView = v.findViewById(R.id.det_year)
                    var ratingDet: TextView = v.findViewById(R.id.det_ratings)
                    var buttonAdd: TextView = v.findViewById(R.id.text_action_bottom2)
                    var buttonClose: TextView = v.findViewById(R.id.text_action_bottom1)

                    nameDet.text = name
                    yearDet.text = year
                    ratingDet.text = rating.toString()

                    buttonAdd.setOnClickListener{
                            addCart(position)
                    }

                    buttonClose.setOnClickListener {

                    //    AwesomeToast.success(this@Dashboard, "Ok clicked").show()

                    }
                    Glide
                        .with(applicationContext)
                        .load(URL_IMAGE + urlPath)
                        .centerInside()
                        .placeholder(R.drawable.cart_123)
                        .into(imgDet)
                }
            })
            .show()
    }

    fun addNum()
    {
        badgeCount += 1
        iconNot.setNumber(badgeCount)
    }




}
