package kharansyah.mobileumn.week11;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholderService {
    @GET("posts")
    Call<ArrayList<Data>> getAllData();
}
