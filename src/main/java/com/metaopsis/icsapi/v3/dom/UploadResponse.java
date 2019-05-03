package com.metaopsis.icsapi.v3.dom;

public class UploadResponse {
    private String jobId;
    private StatusMessage jobStatus;
    private boolean checksumValid;

    public UploadResponse() {
    }


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public StatusMessage getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(StatusMessage jobStatus) {
        this.jobStatus = jobStatus;
    }

    public boolean isChecksumValid() {
        return checksumValid;
    }

    public void setChecksumValid(boolean checksumValid) {
        this.checksumValid = checksumValid;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "jobId='" + jobId + '\'' +
                ", jobStatus=" + jobStatus +
                ", checksumValid=" + checksumValid +
                '}';
    }
}
