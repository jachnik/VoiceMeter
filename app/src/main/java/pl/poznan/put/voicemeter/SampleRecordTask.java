package pl.poznan.put.voicemeter;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;
import java.util.TimerTask;

import retrofit2.Call;

/**
 * Created by jachnika on 15.12.2016.
 */

public class SampleRecordTask extends TimerTask {

    private MediaRecorder mRecorder;
    private EventService eventService;

    public SampleRecordTask(MediaRecorder mRecorder){
        this.mRecorder = mRecorder;
        eventService = EventService.retrofit.create(EventService.class);
    }

    @Override
    public void run() {
        String deviceIdentifier = ApplicationIdentifier.APPLICATION_IDENTIFIER;
        int voicePower = mRecorder.getMaxAmplitude();
        Event event = new Event(deviceIdentifier, voicePower);
        try {
            Call<Event> call = eventService.publishEvent(event);
            call.execute();
            Log.d(SampleRecordTask.class.getSimpleName(), "Event published");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
