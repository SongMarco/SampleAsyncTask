package com.example.administrator.sampleasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText edtNumber;
    Button btn1;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumber = (EditText) findViewById(R.id.edtNumber);
        btn1 = (Button) findViewById(R.id.btn1);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                new FactorialTask().execute(Long.parseLong(edtNumber.getText().toString()));
                break;
            default:
                break;
        }
    }

    /**
     *Params, progress, result 순으로 제네릭스 내부 변수 정의
     * params 는 execute 에서 파라미터로 받아 doInBackground 로 전달됩니다.
     * progress 는 백그라운드 처리 도중 publishProgress 로 업데이트 할 때 받는 인자.
     * result 는 백그라운드 처리가 끝난 값을 onPostExecute 가 받을 때 받는 인자.
     *
     */


    class FactorialTask extends AsyncTask<Long, Long, Long> {

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            tvResult.setText(edtNumber.getText().toString() + "! = " + result);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(Long... params) {
            long result = 1;
            long num = params[0];

            for (long i = 1; i <= num; i++) {
                result = result * i;
            }

            Log.d("test", "result:" + result);
            return result;
        }
    }

}