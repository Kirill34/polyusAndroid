package APILayer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class APIClient {
    private static APIClient mInstance;
    private static Retrofit mRetrofit;
    private static final String BASE_URL = "http://www.cityflow24.ru:8082";

    private APIClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIClient getInstance() {
        if(mInstance == null) {
            mInstance = new APIClient();
        }
        return mInstance;
    }

    public APIInterface getApi() {
        return mRetrofit.create(APIInterface.class);
    }
}