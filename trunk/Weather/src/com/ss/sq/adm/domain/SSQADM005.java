package com.ss.sq.adm.domain;

import com.fission.web.view.extjs.grid.PagingGridCriteria;

public class SSQADM005 extends PagingGridCriteria {
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
	private String wamdat;
	private String wavht;
	private String wavdir;
	private String winspd;
	private String frcvlc;
	private String windir;
	private String wavmean;
	private String wavpeak;
	private String deagcoef;
	private String wavstr;
	private String wavval;

	private String columHdr;
	private String wamval;
	
	
	
	public String getColumHdr() {
		return columHdr;
	}

	public void setColumHdr(String columHdr) {
		this.columHdr = columHdr;
	}

	public String getWamval() {
		return wamval;
	}

	public void setWamval(String wamval) {
		this.wamval = wamval;
	}

	public String getWavval() {
		return wavval;
	}

	public void setWavval(String wavval) {
		this.wavval = wavval;
	}

	public String getWamdat() {
		return wamdat;
	}

	public void setWamdat(String wamdat) {
		this.wamdat = wamdat;
	}

	public String getWavht() {
		return wavht;
	}

	public void setWavht(String wavht) {
		this.wavht = wavht;
	}

	public String getWavdir() {
		return wavdir;
	}

	public void setWavdir(String wavdir) {
		this.wavdir = wavdir;
	}

	public String getWinspd() {
		return winspd;
	}

	public void setWinspd(String winspd) {
		this.winspd = winspd;
	}

	public String getFrcvlc() {
		return frcvlc;
	}

	public void setFrcvlc(String frcvlc) {
		this.frcvlc = frcvlc;
	}

	public String getWindir() {
		return windir;
	}

	public void setWindir(String windir) {
		this.windir = windir;
	}

	public String getWavmean() {
		return wavmean;
	}

	public void setWavmean(String wavmean) {
		this.wavmean = wavmean;
	}

	public String getWavpeak() {
		return wavpeak;
	}

	public void setWavpeak(String wavpeak) {
		this.wavpeak = wavpeak;
	}

	public String getDeagcoef() {
		return deagcoef;
	}

	public void setDeagcoef(String deagcoef) {
		this.deagcoef = deagcoef;
	}

	public String getWavstr() {
		return wavstr;
	}

	public void setWavstr(String wavstr) {
		this.wavstr = wavstr;
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
				+ ", wamdat=" + wamdat + ", wavht=" + wavht
				+ ", wavdir=" + wavdir + ", winspd=" + winspd
				+ ", frcvlc=" + frcvlc + ", windir=" + windir
				+ ", wavmean=" + wavmean + ", wavpeak=" + wavpeak
				+ ", deagcoef=" + deagcoef + ", wavstr=" + wavstr + ", wavval=" + wavval 
				+ "]");
		return "SSQADM002 [pmdentcd=" + pmdentcd + ", pmdldesc=" + pmdldesc
				+ ", station=" + station
				+ ", time=" + time + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", upLatitide=" + upLatitide
				+ ", lowLatitide=" + lowLatitide + ", leftLongtitude=" + leftLongtitude
				+ ", rightLongtitude=" + rightLongtitude
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", latitude=" + latitude + ", longtitude=" + longtitude
				+ ", wamdat=" + wamdat + ", wavht=" + wavht
				+ ", wavdir=" + wavdir + ", winspd=" + winspd
				+ ", frcvlc=" + frcvlc + ", windir=" + windir
				+ ", wavmean=" + wavmean + ", wavpeak=" + wavpeak
				+ ", deagcoef=" + deagcoef + ", wavstr=" + wavstr + ", wavval=" + wavval 
				+ "]";
	}
}
