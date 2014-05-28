package com.ss.sq.adm.domain;

import com.fission.web.view.extjs.grid.PagingGridCriteria;

public class SSQADM003 extends PagingGridCriteria {
	private String pmdentcd;
	private String pmdldesc;
	private String station;
	private String swsprm;
	private String swsdattm;
	private String swsval;
	private String time;
	private String startDate;
	private String finishDate;
	private String upLatitide;
	private String lowLatitide;
	private String latitude;
	private String longtitude;
	private String leftLongtitude;
	private String rightLongtitude;
	private String pp;
	private String tt;
	private String tw;
	private String td;
	private String uu;
	private String cc;
	private String sr;
	private String vv;
	private String rain24;
	private String startTime;
	private String endTime;
	private String genDate;
	private String tMax;
	private String tMin;
	private String columHdr;
	
	
	
	public String getColumHdr() {
		return columHdr;
	}

	public void setColumHdr(String columHdr) {
		this.columHdr = columHdr;
	}

	public String gettMax() {
		return tMax;
	}

	public void settMax(String tMax) {
		this.tMax = tMax;
	}

	public String gettMin() {
		return tMin;
	}

	public void settMin(String tMin) {
		this.tMin = tMin;
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

	public String getTw() {
		return tw;
	}

	public void setTw(String tw) {
		this.tw = tw;
	}

	public String getTd() {
		return td;
	}

	public void setTd(String td) {
		this.td = td;
	}

	public String getUu() {
		return uu;
	}

	public void setUu(String uu) {
		this.uu = uu;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSr() {
		return sr;
	}

	public void setSr(String sr) {
		this.sr = sr;
	}

	public String getVv() {
		return vv;
	}

	public void setVv(String vv) {
		this.vv = vv;
	}

	public String getRain24() {
		return rain24;
	}

	public void setRain24(String rain24) {
		this.rain24 = rain24;
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

	
	public String getSwsprm() {
		return swsprm;
	}

	public void setSwsprm(String swsprm) {
		this.swsprm = swsprm;
	}

	public String getSwsdattm() {
		return swsdattm;
	}

	public void setSwsdattm(String swsdattm) {
		this.swsdattm = swsdattm;
	}

	public String getSwsval() {
		return swsval;
	}

	public void setSwsval(String swsval) {
		this.swsval = swsval;
	}

	@Override
	public String toString() {
		System.out.println("SSQADM002 [pmdentcd=" + pmdentcd + ", pmdldesc=" + pmdldesc
				+ ", station=" + station + ", swsprm=" + swsprm
				+ ", swsdattm=" + swsdattm + ", swsval=" + swsval
				+ ", time=" + time + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", upLatitide=" + upLatitide
				+ ", lowLatitide=" + lowLatitide + ", leftLongtitude=" + leftLongtitude
				+ ", rightLongtitude=" + rightLongtitude + ", pp=" + pp
				+ ", tt=" + tw + ", td=" + uu
				+ ", cc=" + cc + ", sr=" + sr
				+ ", vv=" + vv + ", rain24=" + rain24
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", tmax=" + tMax + ", tmin=" + tMin
				+ "]");
		return "SSQADM002 [pmdentcd=" + pmdentcd + ", pmdldesc=" + pmdldesc
				+ ", station=" + station + ", swsprm=" + swsprm
				+ ", swsdattm=" + swsdattm + ", swsval=" + swsval
				+ ", time=" + time + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", upLatitide=" + upLatitide
				+ ", lowLatitide=" + lowLatitide + ", leftLongtitude=" + leftLongtitude
				+ ", rightLongtitude=" + rightLongtitude + ", pp=" + pp
				+ ", tt=" + tw + ", td=" + uu
				+ ", cc=" + cc + ", sr=" + sr
				+ ", vv=" + vv + ", rain24=" + rain24
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", latitude=" + latitude + ", longtitude=" + longtitude
				+ "]";
	}
}
