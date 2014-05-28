main = {};

Ext.onReady(function(){
	main.linkPanel = new Ext.Panel({
		layout:'table',
		applyTo:'linkPanel',
		border:false,
		bodyBorder:false,
		hideBorders:true,
		autoHeight : true,
		html: '<div align=left> <div style="font-size: 20px;height: 30px">ยินดีต้อนรับสู่ระบบตรวจอากาศ</div> <div style="font-size: 15px;">'
			
	});
	main.newPanel = new Ext.Panel({
		layout:'table',
		applyTo:'newPanel',
		border:false,
		bodyBorder:false,
		hideBorders:true,
		autoHeight : true,
		html: '<div align=left><div style="font-size: 16px;font-weight: bold;height: 20px">What\'s New in Version</div><br>'
			
	,
	});
});