package com.jsi_example; // replace your-app-name with your app’s name

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.fbreact.specs.NativeTampereJsCppModuleSpec;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.JSException;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

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

    private int fibonacciSequence(int index) {
        int prev1 = 1, prev2 = 1;
        for (int i = 1; i < index; i++) {
            int b = prev1;
            prev1 = prev2 + prev1;
            prev2 = b;
        }
        return prev1;
    }

    @Override
    public double sequence(double index) {
        if (index < 0) {
            throw new JSApplicationIllegalArgumentException("Index should be greater then 0");
        }
        return fibonacciSequence((int) index);
    }

    private void fetchData(Function<String, Void> callback) {
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    callback.apply(response.body().extract);
                }
            }

            @Override
            public void onFailure(Call<WikipediaResponse> call, Throwable t) {
                Log.d("FibonacciModule", "Failure");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    callback.apply(null);
                }
            }
        });
    }

    @Override
    public void wikiPromise(Promise promise) {
        fetchData(data -> {
            if (data != null) {
                promise.resolve(data);
            } else {
                promise.reject("FibonacciModule", "Fetching error");
            }
            return null;
        });
    }

    @Override
    public String wikiSync() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            CompletableFuture<String> completableFuture = new CompletableFuture<>();
            fetchData(value -> {
                completableFuture.complete(value);
                return null;
            });
        }

        return null;
    }

    @Override
    public void wikiCallback(com.facebook.react.bridge.Callback onResult) {
        fetchData(data -> {
            if (data != null) {
                onResult.invoke(data, null);
            } else {
                onResult.invoke(null, 1);
            }
            return null;
        });
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }
}