/*!
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

//	 SecurityToolbar.progbar = new Ext.Toolbar({
//	        allowDomMove: 'false',
//	        renderTo: 'program-info-bar',
//	        cls: 'program-info-bar',
//	        height: 25,
//	        //autoHeight: 'true',
//	        autoWidth: 'true'
//	    });
//		
//		
//	 	SecurityToolbar.progbar.add(
//	    {
//	        xtype: 'tbtext',
//	        text: " [empcode]"
//	    });
//		
	
	Ext.Ajax.request({
		url : document.getElementById('context').value + '/menu.htm',
		params: {method : 'getRole'},
		success: function(response, opts){
			var responseOject = Ext.util.JSON.decode(response.responseText);
		    var leftmenuList = [];
		    var rightmenuList = [];
		    for(var i=0 ; i < responseOject.menu.length ; i++){
		    	var MenuObject = new Object();
		    	MenuObject.text = "<b>"+responseOject.menu[i].text+"<b>";
			    	MenuObject.link = responseOject.menu[i].link;
			    	MenuObject.handler = function(){
			    		window.location = this.link;
			        };
		    	if(i<responseOject.menu.length-1){
			    	leftmenuList.push(MenuObject);
		    	}else{
		    		rightmenuList.push(MenuObject);		    		
		    	}
		    }
			
		
		    new Ext.Panel({
		        width:'100%',
		        height:0,
		        tbar: [
		               leftmenuList
			    	, {
				        xtype: 'tbfill'
				    },{
			            xtype: 'tbtext',
			            text: '<b>User</b> '+responseOject.userMenu[0].text
			        },
			        rightmenuList
		        ],
		        
		        renderTo: 'menubar'
		    });
		    
		    new Ext.Panel({
		        width:'100%',
		        height:0,
		        tbar: [{
			        	xtype: 'tbtext',
				        text: '<b>' + document.title+ '</b>'
				    },{
				        xtype: 'tbfill'
				    },{
			            xtype: 'tbtext',
			            text: '<b>ระบบตรวจสภาพอากาศ</b> '
			        }
		        ],
		        
		        renderTo: 'menubar2'
		    });

		},
		failure: function(response, opts){
			var responseOject = Ext.util.JSON.decode(response.responseText);
			Ext.Msg.alert(responseOject.messageHeader, responseOject.message);
		}
	});	

});
//postScript = function() {
//	Ext.onReady(function () {
//		if(isAdmin=='2' || isAdmin=='3'){
//			Ext.getCmp('employeeId').setDisabled(true);
//			Ext.getCmp('employeeName').setDisabled(true);
//		}
//	});
//};
