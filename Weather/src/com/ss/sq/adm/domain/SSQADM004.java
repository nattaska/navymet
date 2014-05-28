package com.ss.sq.adm.domain;

import com.fission.web.view.extjs.grid.PagingGridCriteria;

public class SSQADM004 extends PagingGridCriteria {
	private String pmdentcd;
	private String pmdldesc;
	private String station;
	private String time;
	private String startDate;
	private String finishDate;
	private String upLatitide;
	private String lowLatitide;
	private String latitude;
	private String longtitude;
	private String leftLongtitude;
	private String rightLongtitude;
	private String startTime;
	private String endTime;
	private String genDate;
	private String columHdr;
	private String gwsval;
	
	private String gwsdat;
	private String stn;
	private String pp;
	private String tt;
	private String td;
	private String tmax;
	private String tmin;
	private String cc;
	private String vv;
	private String ws;
	private String wd;
	private String rain24;
	private String ww;
	private String country;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGwsval() {
		return gwsval;
	}

	public void setGwsval(String gwsval) {
		this.gwsval = gwsval;
	}

	public String getGwsdat() {
		return gwsdat;
	}

	public void setGwsdat(String gwsdat) {
		this.gwsdat = gwsdat;
	}

	public String getStn() {
		return stn;
	}

	public void setStn(String stn) {
		this.stn = stn;
	}

	public String getPp() {
		return pp;
	}

	public void setPp(String pp) {
		this.pp = pp;
	}

	public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}

	public String getTd() {
		return td;
	}

	public void setTd(String td) {
		this.td = td;
	}

	public String getTmax() {
		return tmax;
	}

	public void setTmax(String tmax) {
		this.tmax = tmax;
	}

	public String getTmin() {
		return tmin;
	}

	public void setTmin(String tmin) {
		this.tmin = tmin;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getVv() {
		return vv;
	}

	public void setVv(String vv) {
		this.vv = vv;
	}

	public String getWs() {
		return ws;
	}

	public void setWs(String ws) {
		this.ws = ws;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getRain24() {
		return rain24;
	}

	public void setRain24(String rain24) {
		this.rain24 = rain24;
	}

	public String getWw() {
		return ww;
	}

	public void setWw(String ww) {
		this.ww = ww;
	}

	public String getColumHdr() {
		return columHdr;
	}

	public void setColumHdr(String columHdr) {
		this.columHdr = columHdr;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getUpLatitide() {
		return upLatitide;
	}

	public void setUpLatitide(String upLatitide) {
		this.upLatitide = upLatitide;
	}

	public String getLowLatitide() {
		return lowLatitide;
	}

	public void setLowLatitide(String lowLatitide) {
		this.lowLatitide = lowLatitide;
	}

	public String getLeftLongtitude() {
		return leftLongtitude;
	}

	public void setLeftLongtitude(String leftLongtitude) {
		this.leftLongtitude = leftLongtitude;
	}

	public String getRightLongtitude() {
		return rightLongtitude;
	}

	public void setRightLongtitude(String rightLongtitude) {
		this.rightLongtitude = rightLongtitude;
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

	public String getGenDate() {
		return genDate;
	}

	public void setGenDate(String genDate) {
		this.genDate = genDate;
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

	@Override
	public String toString() {
		System.out.println("SSQADM002 [pmdentcd=" + pmdentcd + ", pmdldesc=" + pmdldesc
				+ ", station=" + station
				+ ", time=" + time + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", upLatitide=" + upLatitide
				+ ", lowLatitide=" + lowLatitide + ", leftLongtitude=" + leftLongtitude
				+ ", rightLongtitude=" + rightLongtitude
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", latitude=" + latitude + ", longtitude=" + longtitude
				+ ", gwsdat=" + gwsdat + ", stn=" + stn
				+ ", pp=" + pp + ", tt=" + tt
				+ ", td=" + td + ", tmax=" + tmax
				+ ", tmin=" + tmin + ", cc=" + cc
				+ ", wd=" + wd + ", rain24=" + rain24 + ", ww=" + ww 
				+ ", vv=" + vv + ", ws=" + ws + ", gwsval=" + gwsval
				+ "]");
		return "SSQADM002 [pmdentcd=" + pmdentcd + ", pmdldesc=" + pmdldesc
				+ ", station=" + station
				+ ", time=" + time + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", upLatitide=" + upLatitide
				+ ", lowLatitide=" + lowLatitide + ", leftLongtitude=" + leftLongtitude
				+ ", rightLongtitude=" + rightLongtitude
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", latitude=" + latitude + ", longtitude=" + longtitude
				+ ", gwsdat=" + gwsdat + ", stn=" + stn
				+ ", pp=" + pp + ", tt=" + tt
				+ ", td=" + td + ", tmax=" + tmax
				+ ", tmin=" + tmin + ", cc=" + cc + ", country=" + country 
				+ ", wd=" + wd + ", rain24=" + rain24 + ", ww=" + ww 
				+ ", vv=" + vv + ", ws=" + ws + ", gwsval=" + gwsval 
				+ "]";

	}
}
