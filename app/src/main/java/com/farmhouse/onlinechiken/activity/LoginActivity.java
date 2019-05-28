package com.farmhouse.onlinechiken.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.farmhouse.onlinechiken.R;

import static java.util.Arrays.sort;

public class LoginActivity extends AppCompatActivity {

    String s = "";
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        final int arr[]={0,3,3,7,5,3,11,1};

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = solution(arr);
                System.out.println(val+"");
                login.setText(""+val);
            }
        });

    }


    public int solution(int[] A) {

        if (A.length == 1){
            return -2;
        }

       // sort(A);


        for(int i = 0 ; i < A.length;i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }




        long minDistance = Long.MAX_VALUE;
       for(int i =1; i<A.length ; i++){
            long distance = (long) A[i] - A[i-1];
            if(distance<minDistance){
                minDistance = distance;
            }
        }


        return minDistance>100000000 ?-1 :(int) minDistance;
        // write your code in Java SE 8
    }


    public int solutions(int[] A) {

        for(int i = 0 ; i < A.length;i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }

        long minDistance = Long.MAX_VALUE;
        for(int i =1; i<A.length ; i++){
            long distance = (long) A[i] - A[i-1];
            if(distance<minDistance){
                minDistance = distance;
            }
        }

        return minDistance>100000000 ? -1 : (int) minDistance;
        // write your code in Java SE 8
    }
}
