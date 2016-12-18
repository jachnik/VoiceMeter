package pl.poznan.put.voicemeter;

/**
 * Created by jachnika on 15.12.2016.
 */

public class Event {
    private String deviceIdentifier;
    private int voicePower;

    public Event(String deviceIdentifier, int voicePower){
        this.deviceIdentifier = deviceIdentifier;
        this.voicePower = voicePower;
    }

    public Event () { }

    @Override
    public String toString() {
        return "Event{" +
                "deviceIdentifier='" + deviceIdentifier + '\'' +
                ", voicePower=" + voicePower +
                '}';
    }
}
