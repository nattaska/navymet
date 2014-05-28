package com.ss.sq.adm.domain;

import com.fission.web.view.extjs.grid.PagingGridCriteria;

public class SSQADM002 extends PagingGridCriteria {
	private String startDate;
	private String finishDate;
	private String status;

	private String pmdentcd;
	private String pmdldesc;
	private String station;
	private String awsprm;
	private String awsdattm;
	private String awsval;
	private String time;
	private String startTime;
	private String endTime;
	private String genDate;
	
	public String getGenDate() {
		return genDate;
	}

	public void setGenDate(String genDate) {
		this.genDate = genDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getAwsprm() {
		return awsprm;
	}

	public void setAwsprm(String awsprm) {
		this.awsprm = awsprm;
	}

	public String getAwsdattm() {
		return awsdattm;
	}

	public void setAwsdattm(String awsdattm) {
		this.awsdattm = awsdattm;
	}

	public String getAwsval() {
		return awsval;
	}

	public void setAwsval(String awsval) {
		this.awsval = awsval;
	}
	
	public String getPmdentcd() {
		return pmdentcd;
	}

	public void setPmdentcd(String pmdentcd) {
		this.pmdentcd = pmdentcd;
	}

	public String getPmdldesc() {
		return pmdldesc;
	}

	public void setPmdldesc(String pmdldesc) {
		this.pmdldesc = pmdldesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}


	@Override
	public String toString() {
		System.out.println("SSQADM002 [pmdentcd=" + pmdentcd + ", pmdldesc=" + pmdldesc
				+ ", station=" + station + ", awsprm=" + awsprm
				+ ", awsdattm=" + awsdattm + ", awsval=" + awsval
				+ ", startDate=" + startDate + ", finishDate=" + finishDate
				+ "]");
		return "SSQADM002 [pmdentcd=" + pmdentcd + ", pmdldesc=" + pmdldesc
				+ ", station=" + station + ", awsprm=" + awsprm
				+ ", awsdattm=" + awsdattm + ", awsval=" + awsval
				+ ", startDate=" + startDate + ", finishDate=" + finishDate
				+ "]";
	}
}
