package com.example.administrator.sampleasynctask;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final Long RESULT_SUCCESS = Long.parseLong("0");
    private static final Long RESULT_FAIL = Long.parseLong("1");
    EditText edtNumber;
    Button btn1;
    TextView tvResult;
    long time;

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



        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);

            if(time==0){
                tvResult.setText("go!!!");
            }
            else{
                tvResult.setText(""+time);
            }
        }

        @Override
        protected Long doInBackground(Long... params) {
            time = params[0];

            MediaPlayer.create(getApplicationContext(), R.raw.go).start();

            while(time > 0) {
                try {
                    Thread.sleep(900);         // one second sleep
                    time--;                     // decrement time
                    publishProgress();          // trigger onProgressUpdate()
                } catch(InterruptedException e) {
                    Log.i("GUN", Log.getStackTraceString(e));
                    return RESULT_FAIL;
                }
            }
            return RESULT_SUCCESS;

//
////
////            for (long i = 1; i <= num; i++) {
////                result = result * i;
////            }
//
//            Log.d("test", "result:" + result);

        }
    }

}