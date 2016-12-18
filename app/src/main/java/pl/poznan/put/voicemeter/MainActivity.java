package pl.poznan.put.voicemeter;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private boolean isRecording;
    private MediaRecorder mRecorder;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRecordButton();
        isRecording = false;
    }

    private void initializeRecordButton() {
        final Button recordButton = (Button) findViewById(R.id.recordButton);
        recordButton.setText(R.string.record_button_start);
        recordButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                recordButtonClicked();
            }
        });
    }

    public void recordButtonClicked(){
        isRecording = !isRecording;
        changeRecordButtonLabel();
        if(isRecording){
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void stopRecording() {
        stopSampling();
        mRecorder.stop();
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile("/dev/null");
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(String.valueOf(Log.ERROR), "prepare() failed");
        }
        mRecorder.start();
        startSampling();
    }

    private void startSampling() {
        timer = new Timer();
        SampleRecordTask sampleRecordTask = new SampleRecordTask(mRecorder);
        timer.schedule(sampleRecordTask, 0, 100);
    }

    private void stopSampling(){
        if(timer != null)
            timer.cancel();
    }

    private void changeRecordButtonLabel() {
        Button recordButton = (Button) findViewById(R.id.recordButton);
        if(isRecording){
            recordButton.setText(R.string.record_button_stop);
        } else {
            recordButton.setText(R.string.record_button_start);
        }
    }
}
