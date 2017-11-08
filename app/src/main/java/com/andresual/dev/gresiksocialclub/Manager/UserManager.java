package com.andresual.dev.gresiksocialclub.Manager;

import android.content.Context;
import android.util.Log;

import com.andresual.dev.gresiksocialclub.Activity.SignupActivity;
import com.andresual.dev.gresiksocialclub.Model.UserModel;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andresual on 11/8/2017.
 */

public class UserManager extends NetworkManager {
    Context context;

    private static UserManager mInstance;
    public static UserManager getInstance() {
        if (mInstance == null) {
            mInstance = new UserManager();
        }
        return mInstance;
    }

    private UserModel userActive;

    public UserModel getUserActive() {
        return userActive;
    }

    public void setUserActive(UserModel userActive) {
        this.userActive = userActive;
    }

    public void SignupApi(final SignupActivity signupActivity) {
        final Map<String, String> params = new HashMap<>();
        params.put("id", userActive.getId());
        params.put("nama", userActive.getNama());
        params.put("email", userActive.getEmail());
        params.put("password", userActive.getPassword());
        params.put("tanggal_lahir", userActive.getTanggal_lahir());
        params.put("alamat", userActive.getAlamat());
        Log.i("parameter sign up", params.toString());

        RequestQueue queue = Volley.newRequestQueue(signupActivity);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, signupUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("responsereg", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                        } catch (Throwable t) {
                            Log.i("gresik", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }

        };
    }
}
