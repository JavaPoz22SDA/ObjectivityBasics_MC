package Time;

public class Time {
    private int hour;
    private int minutes;

    public Time(int hour, int minutes) {
        int h = 0;
        if (minutes >= 60){
            h = Math.round(minutes/60);
            minutes = minutes - 60 * h;
        }
        this.hour = hour + h;
        this.minutes = minutes;
    }

    public Time(String hour) {
        String[] strings = hour.trim().split(" ");
        this.hour = Integer.parseInt(strings[0]);
        this.minutes = Integer.parseInt(strings[2]);
    }

    @Override
    public String toString() {
        String hour = String.valueOf(this.hour);
        String minutes = String.valueOf(this.minutes);
        return hour + " h " + minutes + " min";
    }

    public Time add(Time time) {
        int min = time.minutes + this.minutes;
        int h = 0;
        if (min >= 60){
            h = Math.round(min/60);
        }

        Time newTime = new Time(h + this.hour + time.hour,min - (h * 60));
        return newTime;
    }

    public Time subtract(Time time) {
        int minutes1 = this.minutes + (60 * this.hour);
        int minutes2 = time.minutes + (60 * time.hour);
        int minutes = minutes1 - minutes2;
        Time newTime = new Time(Math.round(minutes/60),minutes - (60 * Math.round(minutes/60)));
        return newTime;
    }

    public Time multiply(int howManytimes) {
        int minutes = (this.minutes + 60 * this.hour) * howManytimes;
        Time newTime = new Time(Math.round(minutes/60),minutes - 60 * Math.round(minutes/60));
        return newTime;
    }

    public static Time sum(Time[] tab, int n){
        int minutes = 0;
        for (int i = 0; i < n; i++){
            minutes = minutes + tab[i].minutes + 60 * tab[i].hour;
        }
        Time newTime = new Time(Math.round(minutes/60),minutes - 60 * Math.round(minutes/60));
        return newTime;
    }
}
