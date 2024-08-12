package telran.time;


import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class PastTemporalDateProximity implements TemporalAdjuster{
    Temporal [] temporal;

    public PastTemporalDateProximity(Temporal[] temporal){
        this.temporal = Arrays.copyOf(temporal, temporal.length);
        Arrays.sort(this.temporal);
    }
    @Override
    public Temporal adjustInto(Temporal temporal) {
        int index = Arrays.binarySearch(this.temporal, temporal);
        if (index >= 0) {
            index --;
        } else {
            index = -index - 2;
        }
        return index >= 0 ? this.temporal[index] : null;
    }


}