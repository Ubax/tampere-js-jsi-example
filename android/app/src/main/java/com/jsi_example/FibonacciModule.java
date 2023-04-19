package com.jsi_example; // replace your-app-name with your app’s name
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.fbreact.specs.NativeTampereJsCppModuleSpec;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FibonacciModule extends NativeTampereJsCppModuleSpec {
    public static String NAME = "NativeTampereJsCppModule";

    public FibonacciModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public double sequence(double index) {
        return 0;
    }

    @Override
    public void wiki(Promise promise) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/api/rest_v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WikipediaService apiService = retrofit.create(WikipediaService.class);
        Call<WikipediaResponse> call = apiService.getPageSummary("Fibonacci_sequence");
        call.enqueue(new Callback<WikipediaResponse>() {
            @Override
            public void onResponse(Call<WikipediaResponse> call, Response<WikipediaResponse> response) {
                Log.d("FibonacciModule", "Got response");
                promise.resolve(response.body().extract);
            }

            @Override
            public void onFailure(Call<WikipediaResponse> call, Throwable t) {
                Log.d("FibonacciModule", "Failure");
                promise.reject("FibonacciModule","Fetching error");
            }
        });
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }
}