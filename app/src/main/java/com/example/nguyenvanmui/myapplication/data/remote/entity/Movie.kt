package com.example.nguyenvanmui.myapplication.data.remote.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.example.nguyenvanmui.myapplication.data.room.RoomContract
import com.google.gson.annotations.SerializedName

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@Entity(tableName = RoomContract.TABLE_FAVORITES)
data class Movie(
        @PrimaryKey
        var id: String = "",
        var overview: String? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null,
        @SerializedName("poster_path")
        var posterPath: String? = null,
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        var title: String? = null,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.toDouble()
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
        parcel.writeString(title)
        parcel.writeDouble(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}