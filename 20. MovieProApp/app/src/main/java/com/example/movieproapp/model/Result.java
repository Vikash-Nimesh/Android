package com.example.movieproapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Result implements Parcelable {

    //@SerializedName - serialization is converting object into json string
    //@Expose is used to allow or disallow serialization and deserialization.



    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private List<Movie> results;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_results")
    @Expose
    private int totalResults;



    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel parcel) {
            return new Result(parcel);
        }

        @Override
        public Result[] newArray(int i) {
            return new Result[i];
        }
    };









    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public List<Movie> getResults() {
        return results;
    }


    public void setResults(List<Movie> results) {
        this.results = results;
    }


    public int getTotalPages() {
        return totalPages;
    }


    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public int getTotalResults() {
        return totalResults;
    }


    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeValue(page);
        parcel.writeValue(totalResults);
        parcel.writeValue(totalPages);
        parcel.writeList(results);


    }

    public Result(Parcel in) {
        this.page = (int) in.readValue(Integer.class.getClassLoader());
        in.readList(this.results, (Movie.class.getClassLoader()));
        this.totalPages = (int) in.readValue(Integer.class.getClassLoader());
        this.totalResults = (int) in.readValue(Integer.class.getClassLoader());
    }

    public Result() {

    }
}