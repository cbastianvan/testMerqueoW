package js.com.testmerqueo.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieOb() : Parcelable {

    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("poster_path")
    @Expose
    private var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    private var releaseDate: String? = null

    @SerializedName("vote_average")
    @Expose
    private var rating: Float = 0.toFloat()

    @SerializedName("genre_ids")
    @Expose
    private var genreIds: List<Int>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()
        posterPath = parcel.readString()
        releaseDate = parcel.readString()
        rating = parcel.readFloat()
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getPosterPath(): String? {
        return posterPath
    }

    fun setPosterPath(posterPath: String) {
        this.posterPath = posterPath
    }

    fun getReleaseDate(): String? {
        return releaseDate
    }

    fun setReleaseDate(releaseDate: String) {
        this.releaseDate = releaseDate
    }

    fun getRating(): Float {
        return rating
    }

    fun setRating(rating: Float) {
        this.rating = rating
    }

    fun getGenreIds(): List<Int>? {
        return genreIds
    }

    fun setGenreIds(genreIds: List<Int>) {
        this.genreIds = genreIds
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeFloat(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieOb> {
        override fun createFromParcel(parcel: Parcel): MovieOb {
            return MovieOb(parcel)
        }

        override fun newArray(size: Int): Array<MovieOb?> {
            return arrayOfNulls(size)
        }
    }

}
