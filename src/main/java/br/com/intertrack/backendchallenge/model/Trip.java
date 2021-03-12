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

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getInitialDateTime() {
        return initialDateTime;
    }

    public void setInitialDateTime(Long initialDateTime) {
        this.initialDateTime = initialDateTime;
    }

    public Long getFinalDateTime() {
        return finalDateTime;
    }

    public void setFinalDateTime(Long finalDateTime) {
        this.finalDateTime = finalDateTime;
    }

    public String getInitialAddress() {
        return initialAddress;
    }

    public void setInitialAddress(String initialAddress) {
        this.initialAddress = initialAddress;
    }

    public String getFinalAddress() {
        return finalAddress;
    }

    public void setFinalAddress(String finalAddress) {
        this.finalAddress = finalAddress;
    }

    public Integer getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Integer distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Integer getTotalTimeInMinutes() {
        return totalTimeInMinutes;
    }

    public void setTotalTimeInMinutes(Integer totalTimeInMinutes) {
        this.totalTimeInMinutes = totalTimeInMinutes;
    }
}
