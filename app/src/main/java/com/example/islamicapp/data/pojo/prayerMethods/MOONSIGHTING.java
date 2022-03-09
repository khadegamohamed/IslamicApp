package com.example.islamicapp.data.pojo.prayerMethods;

public class MOONSIGHTING implements IToMethod {
    public int id;
    public String name;
    public  Params params;


    @Override
    public PrayerTimingMethod toMethod() {
        return new PrayerTimingMethod(id,name,params,null);
    }

}
