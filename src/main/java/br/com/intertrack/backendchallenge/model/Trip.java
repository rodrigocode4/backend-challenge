package br.com.intertrack.backendchallenge.model;

public class Trip {


    private Integer vehicleId;

    private Long initialDateTime;

    private Long finalDateTime;

    private String initialAddress;

    private String finalAddress;

    private Integer distanceInMeters;

    private Integer totalTimeInMinutes;


    public Trip(Integer vehicleId, Long initialDateTime, Long finalDateTime, String initialAddress, String finalAddress, Integer distanceInMeters, Integer totalTimeInMinutes) {
        this.vehicleId = vehicleId;
        this.initialDateTime = initialDateTime;
        this.finalDateTime = finalDateTime;
        this.initialAddress = initialAddress;
        this.finalAddress = finalAddress;
        this.distanceInMeters = distanceInMeters;
        this.totalTimeInMinutes = totalTimeInMinutes;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public Long getInitialDateTime() {
        return initialDateTime;
    }

}